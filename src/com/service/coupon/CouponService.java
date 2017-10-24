package com.service.coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.db.manager.CouponDBManager;
import com.model.coupon.Coupon;
import com.model.coupon.CouponState;
import com.platform.auth.TokenManager;
import com.service.interfaces.CouponServiceInterface;
import com.until.errorcode.MAGICCODE;

public class CouponService implements CouponServiceInterface
{
    private TokenManager tokenManager = null;
    private CouponDBManager dbManager = null;
    
    public CouponService()
    {
        tokenManager = new TokenManager();
        dbManager = new CouponDBManager();
    }
    
    @Override
    public int createCoupon(String token, Coupon coupon)
    {
        if(null == coupon || null==coupon.getMagic_key())
        {
            return MAGICCODE.ERROR;
        }
        
        String magicKey = coupon.getMagic_key();
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return MAGICCODE.USER_NOT_EXIST;
        }
        
        int result = MAGICCODE.OK;
        result = dbManager.createCouponInfo(coupon);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public int applyCoupon(String token, String couponID, String magicKey)
    {
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return MAGICCODE.USER_NOT_EXIST;
        }
        int result = MAGICCODE.OK;
        
        result = dbManager.queryPersonHasApplyCoupon(userID, couponID, magicKey);
        if(MAGICCODE.COUPON_HAS_APPLY == result)
        {
            return result;
        }
        
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        result = dbManager.applyCoupon(userID, couponID, uuid, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public List<Coupon> queryStoreCoupon(String token, String magicKey)
    {
        List<Coupon> couponList = new ArrayList<Coupon>();
//        String userID = tokenManager.queryUser(token, magicKey);
//        if(null == userID)
//        {
//            return couponList;
//        }
        
        int result = MAGICCODE.OK;
        result = dbManager.queryStoreCoupon(couponList, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return couponList;
    }

    @Override
    public List<Coupon> queryStoreCanUseCoupon(String token, String magicKey)
    {
        List<Coupon> couponList = new ArrayList<Coupon>();
//        String userID = tokenManager.queryUser(token, magicKey);
//        if(null == userID)
//        {
//            return couponList;
//        }
        
        int result = MAGICCODE.OK;
        result = dbManager.queryStoreCanUseCoupon(couponList, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return couponList;
    }

    @Override
    public Coupon queryPersonCoupon(String token, String couponUUID, String magicKey)
    {
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return null;
        }
        
        Coupon coupon = dbManager.queryCoupon(userID, couponUUID, magicKey);
        if(null == coupon)
        {
            
        }
        return coupon;
    }

    @Override
    public List<Coupon> queryAllPersonCoupon(String token, String magicKey)
    {
        List<Coupon> couponList = new ArrayList<Coupon>();
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return couponList;
        }
        
        int result = MAGICCODE.OK;
        result = dbManager.queryPersonCoupon(couponList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return couponList;
    }

    @Override
    public int usePersonCoupon(String token, String uuid, String magicKey)
    {
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return MAGICCODE.USER_NOT_EXIST;
        }
        
        int result = MAGICCODE.OK;
        result = dbManager.updatePersonCouponState(userID, CouponState.USED, uuid, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public int unUsePersonCoupn(String token, String uuid, String magicKey)
    {
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return MAGICCODE.USER_NOT_EXIST;
        }
        
        int result = MAGICCODE.OK;
        result = dbManager.updatePersonCouponState(userID, CouponState.UNUSED, uuid, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public int payPersonCoupon(String token, String uuid, String magicKey)
    {
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return MAGICCODE.USER_NOT_EXIST;
        }
        
        int result = MAGICCODE.OK;
        result = dbManager.updatePersonCouponState(userID, CouponState.PAYED, uuid, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public int updateCoupon(String token, Coupon coupon)
    {
        String userID = tokenManager.queryUser(token, coupon.getMagic_key());
        if(null == userID)
        {
            return MAGICCODE.USER_NOT_EXIST;
        }
        
        int result = MAGICCODE.OK;
        result = dbManager.updateCoupon(coupon);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public int deleteCoupn(String token, String couponID, String magicKey)
    {
        String userID = tokenManager.queryUser(token, magicKey);
        if(null == userID)
        {
            return MAGICCODE.USER_NOT_EXIST;
        }
        
        int result = MAGICCODE.OK;
        result = dbManager.deleteCoupon(couponID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

}
