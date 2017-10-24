package com.control.vip.member.aj;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.vip.VipMember;
import com.platform.auth.TokenManager;
import com.service.interfaces.VipMemberServiceInterface;
import com.service.vipcard.VipMemberService;
import com.until.errorcode.MAGICCODE;

public class CreateVipMemberController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String token = request.getParameter("token");
        String keyID = request.getParameter("keyID");
        String phone = request.getParameter("phone");
        String customerRealName = request.getParameter("realName");
        
        System.out.println("TOKEN:"+token);
        System.out.println("KEYID:" + keyID);
        
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }

        if (null == token)
        {
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        TokenManager tokenManager = new TokenManager();
        String user = tokenManager.queryUser(token, keyID);
        if(null == user)
        {
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        VipMember vipMember = new VipMember();
        vipMember.setCustomerRealName(customerRealName);
        vipMember.setPhone(phone);
        vipMember.setMagicKey(keyID);
        vipMember.setCustomer(user);
        
        VipMemberServiceInterface service = new VipMemberService();
        int result = service.createVipMember(vipMember);
        if(MAGICCODE.OK == result)
        {
            map.put("code", MAGICCODE.MAGIC_OK);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }else
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }
        
        return null;
    }

}
