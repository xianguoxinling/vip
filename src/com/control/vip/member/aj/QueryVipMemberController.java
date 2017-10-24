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
import com.platform.check.CheckParameter;
import com.service.interfaces.VipMemberServiceInterface;
import com.service.vipcard.VipMemberService;
import com.until.errorcode.MAGICCODE;

public class QueryVipMemberController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String uuid = request.getParameter("uuid");
        
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        
        if(MAGICCODE.OK != CheckParameter.checkParameter(uuid))
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        VipMemberServiceInterface service = new VipMemberService();
        VipMember vipCard = service.queryVipMember(uuid);
        if(null != vipCard)
        {
            json = JSONObject.fromObject(vipCard);
            stream.write(json.toString().getBytes("UTF-8"));
        }
        else
        {
            map.put("code", MAGICCODE.MAGIC_VIP_NOT_FOUND);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }
        
        return null;
        
    }

}
