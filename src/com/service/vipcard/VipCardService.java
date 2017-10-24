package com.service.vipcard;

import java.util.ArrayList;
import java.util.List;

import com.db.manager.VipCardDBManager;
import com.model.vip.VipCard;
import com.service.interfaces.VipCardServiceInterface;
import com.until.errorcode.MAGICCODE;

public class VipCardService implements VipCardServiceInterface
{

    private VipCardDBManager dbManager = null;
    public VipCardService()
    {
        dbManager = new VipCardDBManager();
    }
    
    @Override
    public int createVipCard(String token,VipCard vipCard)
    {
        if(null == vipCard || null == vipCard.getMagicKey())
        {
            return MAGICCODE.PLATFORM_ERROR;
        }
        
        int result = dbManager.createVipCard(vipCard);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public VipCard queryVipCard(String token,String id, String magicKey)
    {
        return dbManager.queryVipCard(id, magicKey);
    }

    @Override
    public List<VipCard> queryVipCard(String token,String magicKey)
    {
        List<VipCard> vipCardList = new ArrayList<VipCard>();
        int result = dbManager.queryVipCard(vipCardList, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return vipCardList;
    }

    @Override
    public int updateVipCard(String token,VipCard vipCard)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateVipCard(vipCard);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        return result;
    }

    @Override
    public int updatePic(String token, String id, String pic, String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateVipCardPic(id, magicKey, pic);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        return result;
    }

}
