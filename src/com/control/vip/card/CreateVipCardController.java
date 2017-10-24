package com.control.vip.card;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.vip.VipCard;
import com.platform.base.UserCookieManager;
import com.service.interfaces.VipCardServiceInterface;
import com.service.vipcard.VipCardService;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;
import com.until.replace.ReplaceSrvToHttp;

public class CreateVipCardController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String filePath = "/srv/www/htdocs/ty";
        HttpSession session = request.getSession(); 
        String token = (String)session.getAttribute("token");
        if (null == token)
        {
            token =  UserCookieManager.getCookieValueByName(request, "token");
            if (null == token)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }
        
        String keyID = (String)session.getAttribute("keyID");
        if (null == keyID)
        {
            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
            if(null == keyID)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String introduction = request.getParameter("introduction");
        String discount = request.getParameter("discount");
        String level = request.getParameter("level");
        
        if((!UntilNum.isNumber(discount))||!UntilNum.isNumber(level))
        {
            return new ModelAndView("/store/error.html");
        }
        
        VipCard vipCard = new VipCard();
        vipCard.setName(name);
        vipCard.setContactAddress(address);        
        vipCard.setContactNumber(phone);
        vipCard.setIntroduction(introduction);
        vipCard.setMagicKey(keyID);
        vipCard.setLevel(Integer.parseInt(level));
        vipCard.setDiscount(Double.parseDouble(discount));
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        if(null != multipartFile)
        {
            filePath += "/vip/";
            filePath += keyID;
            filePath += "/";
            filePath += UUID.randomUUID().toString();
            filePath += "/";

            File folder = new File(filePath);
            if (!folder.exists())
            {
                folder.mkdirs();
            }

            String attacheFile = filePath + multipartFile.getOriginalFilename();
            File source = new File(attacheFile);
            multipartFile.transferTo(source);
            vipCard.setPic(attacheFile);
        }

        VipCardServiceInterface service = new VipCardService();
        int result = service.createVipCard(token,vipCard);
        if(MAGICCODE.OK == result)
        {
            List<VipCard> cardList = service.queryVipCard(token,keyID);
            if(null != cardList)
            {
                Iterator<VipCard> it = cardList.iterator();
                while(it.hasNext())
                {
                    VipCard card = it.next();
                    card.setPic(ReplaceSrvToHttp.replace(card.getPic()));
                }
                return new ModelAndView("/card/viplist.jsp","cardList",cardList);
            }else
            {
                return new ModelAndView("/store/error.html");
            }
        }else
        {
            return new ModelAndView("/store/error.html");
        }
    }

}
