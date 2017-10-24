package com.model.vip;

import java.util.UUID;

public class VipMember
{
    protected String id = null;
    protected String cardID = null;
    protected String cardName = null;
    protected String customer = null;
    protected String customerRealName = null;
    protected String phone = null;
    protected String uuid = null;
    protected String magicKey = null;
    protected double blance = 0;
    protected double discount = 1;
    protected long coinNum = 0;
    protected String createTime = null;
    protected String state = null;
    
    public VipMember()
    {
        setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
    }    
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getCustomer()
    {
        return customer;
    }
    public void setCustomer(String customer)
    {
        this.customer = customer;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getCustomerRealName()
    {
        return customerRealName;
    }
    public void setCustomerRealName(String customerRealName)
    {
        this.customerRealName = customerRealName;
    }
    public String getUuid()
    {
        return uuid;
    }
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    public double getBlance()
    {
        return blance;
    }
    public void setBlance(double blance)
    {
        this.blance = blance;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public long getCoinNum()
    {
        return coinNum;
    }

    public void setCoinNum(long coinNum)
    {
        this.coinNum = coinNum;
    }

    public String getCardID()
    {
        return cardID;
    }

    public void setCardID(String cardID)
    {
        this.cardID = cardID;
    }

    public String getCardName()
    {
        return cardName;
    }

    public void setCardName(String cardName)
    {
        this.cardName = cardName;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
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
