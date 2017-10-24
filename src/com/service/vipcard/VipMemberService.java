package com.service.vipcard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.db.manager.VipMemberDBManager;
import com.model.vip.VipCard;
import com.model.vip.VipMember;
import com.service.coin.CoinService;
import com.service.interfaces.CoinServiceInterface;
import com.service.interfaces.VipCardServiceInterface;
import com.service.interfaces.VipMemberServiceInterface;
import com.until.errorcode.MAGICCODE;

public class VipMemberService implements VipMemberServiceInterface
{

    private VipMemberDBManager dbManager = null;

    public VipMemberService()
    {
        dbManager = new VipMemberDBManager();
    }

    @Override
    public int createVipMember(VipMember vipCard)
    {
        if (null == vipCard || null == vipCard.getMagicKey())
        {
            return MAGICCODE.PARAMETER_ERROR;
        }
        int result = MAGICCODE.OK;
        VipMember queryMember = dbManager.queryVipMemberByUserID(vipCard.getCustomer());
        if(null != queryMember)
        {
            String oldState = queryMember.getState();
            if("ok".equals(oldState)||"apply".equals(oldState))
            {
                return MAGICCODE.OK;
            }else
            {
                result = dbManager.updateState(queryMember.getId(), "apply");
                return result;
            }
        }
        
        result = dbManager.createVipMember(vipCard);
        if (MAGICCODE.OK != result)
        {
            return result;
        }
        
        CoinServiceInterface coinService = new CoinService();
        result = coinService.createCoinInfo(vipCard.getCustomer(), vipCard.getMagicKey());
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int updateVipBlance(String uuid, double price)
    {
        double blance = queryBlance(uuid);
        if (blance < 0)
        {
            return MAGICCODE.ERROR;
        }
        if (price >= 0)
        {
            blance = blance + price;
        } else
        {
            blance = blance + price;
            if (blance < 0)
            {
                return MAGICCODE.ERROR;
            }
        }
        int result = dbManager.updateBlance(uuid, blance);
        if (MAGICCODE.OK != result)
        {
            return MAGICCODE.ERROR;
        }
        return result;
    }

    @Override
    public VipMember queryVipMember(String uuid)
    {
        VipMember vipCard = dbManager.queryVipMember(uuid);
        return vipCard;
    }

    @Override
    public double queryBlance(String uuid)
    {
        double blance = dbManager.queryBlance(uuid);
        if (blance < 0)
        {

        }
        return blance;
    }

    @Override
    public List<VipMember> queryApply(String token, String keyID)
    {
        List<VipMember> vipMemberList = new ArrayList<VipMember>();
        int result = dbManager.queryApply(keyID, vipMemberList);
        if (MAGICCODE.OK != result)
        {

        }

        return vipMemberList;
    }
    
    public List<VipMember> queryAllMember(String token,String keyID)
    {
        List<VipMember> vipMemberList = new ArrayList<VipMember>();
        int result = dbManager.queryAllMember(keyID, vipMemberList);
        if (MAGICCODE.OK != result)
        {

        }

        return vipMemberList;
    }

    @Override
    public int aggreeApply(String token, String id, String keyID)
    {
        int result = MAGICCODE.OK;
        
        result = upateState("ok", id);
        if (MAGICCODE.OK != result)
        {
            return result;
        }

        VipCardServiceInterface vipCardService = new VipCardService();
        List<VipCard> vipCardList = vipCardService.queryVipCard(token, keyID);
        int level = 1000;
        String cardID = null;
        Iterator<VipCard> it = vipCardList.iterator();
        while (it.hasNext())
        {
            VipCard card = it.next();
            int cardLevel = card.getLevel();
            if (cardLevel <= level)
            {
                level = cardLevel;
                cardID = card.getId();
            }
        }
        if (null != cardID)
        {
            result = dbManager.updateCard(id, cardID);
            if (MAGICCODE.OK != result)
            {

            }
        }

        return result;
    }

    @Override
    public int refuseApply(String token, String id, String keyID)
    {
        int result = MAGICCODE.OK;
        result = upateState("refuse", id);
        if (MAGICCODE.OK != result)
        {

        }
        return result;
    }

    public int upateState(String state, String id)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateState(id, state);
        if (MAGICCODE.OK != result)
        {

        }
        return result;
    }

    @Override
    public double queryDiscount(String userID, String keyID)
    {
        double discount = 1;
        discount = dbManager.queryDiscount(userID, keyID);
        
        return discount;
    }

    @Override
    public int updateVipLevel(String token, String memberID, String cardID, String keyID)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateCard(memberID, cardID);
        if(MAGICCODE.OK!= result)
        {
            
        }
        return 0;
    }

}
