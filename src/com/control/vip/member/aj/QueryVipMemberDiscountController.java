package com.control.vip.member.aj;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.platform.check.CheckParameter;
import com.service.interfaces.VipMemberServiceInterface;
import com.service.vipcard.VipMemberService;
import com.until.errorcode.MAGICCODE;

public class QueryVipMemberDiscountController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        
        String userID = request.getParameter("userID");
        String keyID = request.getParameter("keyID");
        
        if(MAGICCODE.OK != CheckParameter.checkParameter(userID,keyID))
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        VipMemberServiceInterface service = new VipMemberService();
        double discount = service.queryDiscount(userID, keyID);
        if(discount >= 0)
        {
            map.put("code", MAGICCODE.MAGIC_OK);
            map.put("discount",new Double(discount).toString());
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }
        else
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }
        
        return null;
    }

}
