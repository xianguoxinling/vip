package com.control.vip.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.vip.VipCard;
import com.platform.base.UserCookieManager;
import com.service.interfaces.VipCardServiceInterface;
import com.service.vipcard.VipCardService;
import com.until.num.UntilNum;
import com.until.replace.ReplaceSrvToHttp;

public class QueryVipCardByIDController implements Controller
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
        
        String id = request.getParameter("id");
        if((!UntilNum.isNumber(id)))
        {
            return new ModelAndView("/store/error.html");
        }
        VipCardServiceInterface service = new VipCardService();
        VipCard vipCard = service.queryVipCard(token,id, keyID);
        if(null != vipCard)
        {
            vipCard.setPic(ReplaceSrvToHttp.replace(vipCard.getPic()));
            return new ModelAndView("/card/vipcard.jsp","card",vipCard);
        }else
        {
            return new ModelAndView("/store/error.html");
        }
    }
}
