package com.control.coin;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.platform.auth.TokenManager;
import com.service.coin.CoinService;
import com.service.interfaces.CoinServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;

public class UpdateCoinController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String token = request.getParameter("token");
        String reason = request.getParameter("reason");
        String userID = request.getParameter("userID");
        String keyID = request.getParameter("keyID");
        String num = request.getParameter("num");
        
//        System.out.println("TOKEN:"+token);
//        System.out.println("KEYID:"+keyID);
//        System.out.println("num:"+num);
//        System.out.println("USERID"+userID);
//        System.out.println("REASON:"+reason);
        
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }

        if (null == token)
        {
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        if(!UntilNum.isNumber(num))
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        TokenManager authManager = new TokenManager();
        String jsonResult = authManager.queryUser(token, keyID);
        JSONObject jsonObject = JSONObject.fromObject(jsonResult);
        String code = (String) jsonObject.get("code");
        if (!MAGICCODE.MAGIC_OK.equals(code))
        {
            map.put("code", code);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        CoinServiceInterface coinService = new CoinService();
        int result = coinService.updateCoin(userID, reason, Long.parseLong(num), keyID);
        if(MAGICCODE.COIN_NOT_ENOUGH == result)
        {
            map.put("code", MAGICCODE.MAGIC_COIN_NOT_ENOUGH);
        }
        else if(MAGICCODE.OK == result)
        {
            map.put("code", MAGICCODE.MAGIC_OK);
        }else
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
        }
        
        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
    }

}
