package com.control.vip.card;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.vip.VipCard;
import com.platform.base.UserCookieManager;
import com.service.interfaces.VipCardServiceInterface;
import com.service.vipcard.VipCardService;
import com.until.replace.ReplaceSrvToHttp;

public class QueryVipCardController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HttpSession session = request.getSession(); 
        String token = (String)session.getAttribute("token");
        if (null == token)
        {
            token =  UserCookieManager.getCookieValueByName(request, "token");
            if (null == token)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }
        
        String keyID = (String)session.getAttribute("keyID");
        if (null == keyID)
        {
            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
            if(null == keyID)
            {
                return new ModelAndView("/store/storelogin.jsp");
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
            return new ModelAndView("/card/viplist.jsp","cardList",cardList);
        }else
        {
            return new ModelAndView("/store/error.html");
        }
    }
}
