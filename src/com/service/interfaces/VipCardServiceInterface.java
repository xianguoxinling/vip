package com.service.interfaces;

import java.util.List;

import com.model.vip.VipCard;

public interface VipCardServiceInterface
{
    public int createVipCard(String token,VipCard vipCard);
    public VipCard queryVipCard(String token,String id,String magicKey);
    public List<VipCard> queryVipCard(String token,String magicKey);
    public int updateVipCard(String token,VipCard vipCard);
    public int updatePic(String token,String id,String pic,String magicKey);
}
