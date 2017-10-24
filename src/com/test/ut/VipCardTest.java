package com.test.ut;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.model.vip.VipCard;
import com.service.interfaces.VipCardServiceInterface;
import com.service.vipcard.VipCardService;
import com.until.errorcode.MAGICCODE;

public class VipCardTest
{
    VipCardServiceInterface service = null; 
    @Before
    public void setUp() throws Exception
    {
        service = new VipCardService();
    }

//    @Test
    public void testCreateVipCard()
    {
        VipCard vipCard = new VipCard();
        vipCard.setName("青铜会员");
        vipCard.setContactAddress("山东省潍坊市健康东街文化创意产业园");
        vipCard.setContactNumber("15557193876");
        vipCard.setDiscount(0.98);
        vipCard.setLevel(1);
        vipCard.setIntroduction("青铜会员卡");
        vipCard.setMagicKey("eb07c48a400a4288a0ee8322250cff04");
        vipCard.setPic("/srv/test");
        int result = MAGICCODE.OK ;
        result = service.createVipCard("test",vipCard);
        assertEquals(MAGICCODE.OK,result);
        
    }
    
    @Test
    public void queryVipCard()
    {
        List<VipCard> vipCardList = service.queryVipCard("test","eb07c48a400a4288a0ee8322250cff04");
        Iterator<VipCard> it = vipCardList.iterator();
        while(it.hasNext())
        {
            VipCard vipCard = it.next();
            System.out.println(vipCard.getContactAddress());
            System.out.println(vipCard.getContactNumber());
            System.out.println(vipCard.getCreateTime());
            System.out.println(vipCard.getDiscount());
            System.out.println(vipCard.getIntroduction());
            System.out.println(vipCard.getMagicKey());
            System.out.println(vipCard.getName());
            System.out.println(vipCard.getPic());
            System.out.println(vipCard.getLevel());
        }
        
        VipCard queryCard = service.queryVipCard("test","1", "eb07c48a400a4288a0ee8322250cff04");
        System.out.println(queryCard.getContactAddress());
        System.out.println(queryCard.getContactNumber());
        System.out.println(queryCard.getCreateTime());
        System.out.println(queryCard.getDiscount());
        System.out.println(queryCard.getIntroduction());
        System.out.println(queryCard.getMagicKey());
        System.out.println(queryCard.getName());
        System.out.println(queryCard.getPic());
    }

}
