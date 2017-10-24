package com.test.ut;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.model.coupon.Coupon;
import com.service.coupon.CouponService;
import com.service.interfaces.CouponServiceInterface;

public class CouponServiceTest
{
    private String token = "0hv8bc4kfafhw4a0r60jxaxh5t5ff74y20";
    private String magicKey = "ccc65483";
    private CouponServiceInterface couponService = null;

    @Before
    public void setUp() throws Exception
    {
        couponService = new CouponService();
    }

    @Test
    public void testQueryCoupon()
    {
        List<Coupon> couponList = couponService.queryStoreCoupon(token, magicKey);
        Iterator<Coupon> it = couponList.iterator();
        while(it.hasNext())
        {
            Coupon coupon = it.next();
            System.out.println("TEST:"+coupon.getId());
        }
    }
    
    @Test
    public void testQueryStoreCanUseCoupon()
    {
        List<Coupon> couponList = couponService.queryStoreCanUseCoupon(token, magicKey);
        Iterator<Coupon> it = couponList.iterator();
        while(it.hasNext())
        {
            Coupon coupon = it.next();
            System.out.println("TEST CAN USED:"+coupon.getId());
        }
    }

}
