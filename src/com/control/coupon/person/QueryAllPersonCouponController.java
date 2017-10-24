package com.control.coupon.person;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.coupon.Coupon;
import com.service.coupon.CouponService;
import com.service.interfaces.CouponServiceInterface;
import com.until.errorcode.MAGICCODE;

public class QueryAllPersonCouponController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String token = request.getParameter("token");
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        JSONArray couponJsonList = null;
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
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        CouponServiceInterface couponService = new CouponService();
        List<Coupon> couponList = couponService.queryAllPersonCoupon(token, keyID);
        couponJsonList = JSONArray.fromObject(couponList);
        stream.write(couponJsonList.toString().getBytes("UTF-8"));
        return null;
        
    }
}
