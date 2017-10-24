package com.control.coupon;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.coupon.Coupon;
import com.platform.base.UserCookieManager;
import com.service.coupon.CouponService;
import com.service.interfaces.CouponServiceInterface;

public class QueryStoreCouponController implements Controller
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
        
        CouponServiceInterface couponService = new CouponService();
        List<Coupon> couponList = couponService.queryStoreCoupon(token, keyID);
        
        return new ModelAndView("/coupon/couponlist.jsp","couponList",couponList);
    }

}
