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
import com.platform.base.UserCookieManager;
import com.service.coin.CoinService;
import com.service.interfaces.CoinServiceInterface;
import com.until.errorcode.MAGICCODE;

public class CheckSignTodayController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        String token = UserCookieManager.getUserID(request, response);
        String userID = null;
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }

        if (null == token)
        {
            token = request.getParameter("token");
            if (null == token)
            {
                map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
                json = JSONObject.fromObject(map);
                stream.write(json.toString().getBytes("UTF-8"));
                return null;
            }
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
        userID = (String) jsonObject.get("userID");
        CoinServiceInterface coinService = new CoinService();

        int result = coinService.hasSignToday(userID, keyID);
        if (MAGICCODE.COIN_SIGN_TODAY == result)
        {
            map.put("sign", "true");
        } else
        {
            map.put("sign", "false");
        }

        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));

        return null;
    }

}
