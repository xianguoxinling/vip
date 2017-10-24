package com.model.coupon;

import java.util.UUID;

public class Coupon
{
    private String id = null;
    private String name = "优惠券";
    private double faceValue = 0.0;
    //0代表无门凯优惠券，100代表最低消费
    private double minimumLimit = 0.0;
    private String magic_key = null;
    private String beginTime = null;
    private String endTime = null;
    private String createTime = null;
    private String state = null;
    
    public Coupon()
    {
        
    }
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public double getFaceValue()
    {
        return faceValue;
    }
    public void setFaceValue(double faceValue)
    {
        this.faceValue = faceValue;
    }
    public double getMinimumLimit()
    {
        return minimumLimit;
    }
    public void setMinimumLimit(double minimumLimit)
    {
        this.minimumLimit = minimumLimit;
    }
    
    public String getMagic_key()
    {
        return magic_key;
    }
    public void setMagic_key(String magic_key)
    {
        this.magic_key = magic_key;
    }
    public String getBeginTime()
    {
        return beginTime;
    }
    public void setBeginTime(String beginTime)
    {
        this.beginTime = beginTime;
    }
    public String getEndTime()
    {
        return endTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}
