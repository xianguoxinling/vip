package com.service.interfaces;

import java.util.List;

import com.model.coupon.Coupon;

public interface CouponServiceInterface
{
    public int createCoupon(String token,Coupon coupon);
    public int applyCoupon(String token,String couponID,String magicKey);
    public List<Coupon> queryStoreCoupon(String token,String magicKey);
    public List<Coupon> queryStoreCanUseCoupon(String token,String magicKey);
    
    public Coupon queryPersonCoupon(String token,String couponUUID,String magicKey);
    public List<Coupon> queryAllPersonCoupon(String token,String magicKey);
    
    public int usePersonCoupon(String token,String uuid,String magicKey);
    public int unUsePersonCoupn(String token,String uuid,String magicKey);
    public int payPersonCoupon(String token,String uuid,String magicKey);
    
    public int updateCoupon(String token,Coupon coupon);
    public int deleteCoupn(String token,String couponID,String magicKey);
    
    
}
