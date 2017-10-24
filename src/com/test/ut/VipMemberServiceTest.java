package com.test.ut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.model.vip.VipMember;
import com.service.interfaces.VipMemberServiceInterface;
import com.service.vipcard.VipMemberService;
import com.until.errorcode.MAGICCODE;

public class VipMemberServiceTest
{

    VipMemberServiceInterface service = null;
    String magicKey = "eb07c48a400a4288a0ee8322250cff04";
    
    @Before
    public void setUp() throws Exception
    {
        service = new VipMemberService();
    }

    @Test
    public void testCreateVipCard()
    {
        VipMember vipCard = new VipMember();
        vipCard.setBlance(100);
        vipCard.setCustomer("2");
        vipCard.setCustomerRealName("Tooko");
        vipCard.setMagicKey(magicKey);
        vipCard.setPhone("12345");
        
        int result = service.createVipMember(vipCard);
        assertEquals(MAGICCODE.OK,result);
        
    }

//    @Test
    public void testUpdateVipBlance()
    {
        String uuid = "9ac973ff342b4453aed2e1b8f374ab65";
        double blance = service.queryBlance("9ac973ff342b4453aed2e1b8f374ab65");
        System.out.println("BLANCE:"+blance);
        
        int result = service.updateVipBlance("9ac973ff342b4453aed2e1b8f374ab65", 100);
        assertEquals(MAGICCODE.OK,result);
        
        blance = service.queryBlance("9ac973ff342b4453aed2e1b8f374ab65");
        System.out.println("BLANCE:"+blance);
        
        result = service.updateVipBlance(uuid, -1000);
        assertEquals(MAGICCODE.ERROR,result);
        
        blance = service.queryBlance("9ac973ff342b4453aed2e1b8f374ab65");
        System.out.println("BLANCE:"+blance);
        
    }
//    @Test
    public void testQueryVipCard()
    {
        VipMember vipCard = service.queryVipMember("9ac973ff342b4453aed2e1b8f374ab65");
        assertEquals(vipCard.getCustomer(),"1");
        assertEquals(vipCard.getCustomerRealName(),"devpuck");
        assertEquals(vipCard.getMagicKey(),magicKey);
        System.out.println(vipCard.getBlance());
    }

//    @Test
    public void testQueryBlance()
    {
        double blance = service.queryBlance("9ac973ff342b4453aed2e1b8f374ab65");

        System.out.println("BLANCE:"+blance);
        
    }

}
