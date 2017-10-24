package com.service.interfaces;

import java.util.List;

import com.model.vip.VipMember;

public interface VipMemberServiceInterface
{
    public int createVipMember(VipMember vipCard);
    public int updateVipBlance(String uuid,double price);
    public VipMember queryVipMember(String uuid);
    public double queryBlance(String uuid);
    public List<VipMember> queryApply(String token,String keyID);
    public List<VipMember> queryAllMember(String token,String keyID);
    public int aggreeApply(String token,String id,String keyID);
    public int refuseApply(String token,String id,String keyID);
    public double queryDiscount(String userID,String keyID);
    public int updateVipLevel(String token,String memberID,String cardID,String keyID);
    
}
