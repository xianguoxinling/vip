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

public class SignController implements Controller
{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

        String token = request.getParameter("token");
        String userID = null;
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        
//        System.out.println("token:"+token +"  keyID:"+keyID);
        
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
		int result = coinService.sign(userID,keyID);
		if(MAGICCODE.OK == result)
		{
			map.put("code", MAGICCODE.MAGIC_OK);
		}
		else
		{
			map.put("code", MAGICCODE.MAGIC_ERROR);
		}
		
		json = JSONObject.fromObject(map);
		stream.write(json.toString().getBytes("UTF-8"));
		
		return null;
	}

}
