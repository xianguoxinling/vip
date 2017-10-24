package com.test.ft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VipMemberFtTest
{

    public String server = "122.4.241.3";
    
    public String apply(String token, String keyID,String realName,String phone)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/vip/member/apply.action?token=" + token + "&keyID=" + keyID+ "&realName=" + realName+ "&phone=" + phone;
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            reader.close();
            httpConn.disconnect();

            result = buffer.toString();

        } catch (Exception e)
        {
            
        } finally
        {
            
        }

        return result;
    }
    
    public String queryDiscount(String userID, String keyID)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/vip/member/querydiscount.action?userID=" + userID + "&keyID=" + keyID;
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            reader.close();
            httpConn.disconnect();

            result = buffer.toString();

        } catch (Exception e)
        {
            
        } finally
        {
            
        }

        return result;
    }
    
    public static void main(String args[])
    {
        VipMemberFtTest ft = new VipMemberFtTest();
        String token="fjlf5hp4zfuq49kbicp45xaic6gfftuq25";
        String keyID = "eb07c48a400a4288a0ee8322250cff04";
        String realName = "苗蓬";
        String phone = "15557193876";
        String result = ft.apply(token, keyID, realName, phone);
        System.out.println(result);
        
        result = ft.queryDiscount("25", keyID);
        System.out.println(result);
        
    }
}
