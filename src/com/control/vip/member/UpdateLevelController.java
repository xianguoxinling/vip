package com.control.vip.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.vip.VipMember;
import com.platform.base.UserCookieManager;
import com.platform.check.CheckParameter;
import com.service.interfaces.VipMemberServiceInterface;
import com.service.vipcard.VipMemberService;
import com.until.errorcode.MAGICCODE;

public class UpdateLevelController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        
        String level = request.getParameter("level");
        String memberID = request.getParameter("memberid");
        if(MAGICCODE.OK != CheckParameter.checkParameter(level,memberID))
        {
            return new ModelAndView("/store/error.html");
        }
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
        int result = service.updateVipLevel(token, memberID, level, keyID);
        if(MAGICCODE.OK!=result)
        {
            return new ModelAndView("/store/error.html");
        }
        List<VipMember> memberList = service.queryAllMember(token, keyID);
        
        return new ModelAndView("/member/memberlist.jsp","memberList",memberList);
    }

}
