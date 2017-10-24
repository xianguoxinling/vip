package com.control.vip.card.aj;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.vip.VipCard;
import com.platform.base.UserCookieManager;
import com.service.interfaces.VipCardServiceInterface;
import com.service.vipcard.VipCardService;
import com.until.errorcode.MAGICCODE;
import com.until.replace.ReplaceSrvToHttp;

public class QueryCardController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        JSONArray cardJsonList = null;
        OutputStream stream = response.getOutputStream();
        
        HttpSession session = request.getSession(); 
        String token = (String)session.getAttribute("token");
        if (null == token)
        {
            token =  UserCookieManager.getCookieValueByName(request, "token");
            if(null ==token)
            {
                map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
                json = JSONObject.fromObject(map);
                stream.write(json.toString().getBytes("UTF-8"));
                return null;
            }

        }
        
        String keyID = (String)session.getAttribute("keyID");
        if (null == keyID)
        {
            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
            if(null == keyID)
            {
                map.put("code", MAGICCODE.MAGIC_KEY_NULL);
                json = JSONObject.fromObject(map);
                stream.write(json.toString().getBytes("UTF-8"));
                return null;
            }

        }
        VipCardServiceInterface service = new VipCardService();
        List<VipCard> cardList = service.queryVipCard(token,keyID);
        if(null != cardList)
        {
            Iterator<VipCard> it = cardList.iterator();
            while(it.hasNext())
            {
                VipCard card = it.next();
                card.setPic(ReplaceSrvToHttp.replace(card.getPic()));
            }
            cardJsonList = JSONArray.fromObject(cardList);
            stream.write(cardJsonList.toString().getBytes("UTF-8"));
            return null;
        }else
        {
            token =  UserCookieManager.getCookieValueByName(request, "token");
            map.put("code", MAGICCODE.MAGIC_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
    }
}
