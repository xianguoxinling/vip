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
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;

public class UpdateCouponController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        if (null == token)
        {
            token = UserCookieManager.getCookieValueByName(request, "token");
            if (null == token)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }

        String keyID = (String) session.getAttribute("keyID");
        if (null == keyID)
        {
            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
            if (null == keyID)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }

        String name = request.getParameter("name");
        String faceValue = request.getParameter("face_value");
        String limit = request.getParameter("limit");
        if ((!UntilNum.isDouble(faceValue)) || (!UntilNum.isDouble(limit)))
        {
            return new ModelAndView("/store/error.html");
        }
        
        String beginTime = request.getParameter("begin_time");
        String endTime = request.getParameter("end_time");
        
        Coupon coupon = new Coupon();
        coupon.setName(name);
        coupon.setCreateTime(beginTime);
        coupon.setEndTime(endTime);
        coupon.setMagic_key(keyID);
        coupon.setFaceValue(Double.parseDouble(faceValue));
        coupon.setMinimumLimit(Double.parseDouble(limit));

        CouponServiceInterface couponService = new CouponService();
        int result = couponService.updateCoupon(token, coupon);
        if(MAGICCODE.OK != result)
        {
            return new ModelAndView("/store/error.html");
        }
        
        List<Coupon> couponList = couponService.queryStoreCoupon(token, keyID);
        return new ModelAndView("/coupon/couponlist.jsp","couponList",couponList);
    }
}
