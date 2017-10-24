package com.control.vip.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.vip.VipMember;
import com.platform.base.UserCookieManager;
import com.service.interfaces.VipMemberServiceInterface;
import com.service.vipcard.VipMemberService;
import com.until.errorcode.MAGICCODE;

public class AgreeVipMemberController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        
        String id = request.getParameter("id");
        System.out.println("INTERFACE:"+id);
        
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
        
        VipMemberServiceInterface service = new VipMemberService();
        int result = service.aggreeApply(token, id, keyID);
        if(MAGICCODE.OK != result)
        {
            return new ModelAndView("/store/error.html");
        }
        
        List<VipMember> memberList = service.queryApply(token, keyID);
        return new ModelAndView("/member/applylist.jsp","memberList",memberList);
        
    }

}
