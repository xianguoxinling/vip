package com.platform.coin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoinManager
{

    private String mainAuthServer = null;
    private String salveAuthServer = null;
    private String server = null;
    
    public CoinManager()
    {
//        mainAuthServer = "127.0.0.1";
//        salveAuthServer = "127.0.0.1";
      mainAuthServer = "magic.puckart.com";
      salveAuthServer = "magic.puckart.com";
        server = salveAuthServer;
    }
    
    public String sign(String token, String keyID)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/coin/sign.action?token=" + token + "&keyID=" + keyID;
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
            server = salveAuthServer;
        } finally
        {
            // 阿里云内主要还是这个鉴权
            server = mainAuthServer;
        }

        return result;
    }
    
    public String queryCoin(String token, String keyID)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/coin/query.action?token=" + token + "&keyID=" + keyID;
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
            server = salveAuthServer;
        } finally
        {
            // 阿里云内主要还是这个鉴权
            server = mainAuthServer;
        }

        return result;
    }
    
    public String updateCoin(String userID,String token,String reason,String num,String keyID)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/coin/update.action?token=" + token + "&keyID=" + keyID+"&userID="+userID
                            +"&reason="+reason + "&num="+num;
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
            server = salveAuthServer;
        } finally
        {
            // 阿里云内主要还是这个鉴权
            server = mainAuthServer;
        }

        return result;
    }
    
    public static void main(String[] args)
    {
        CoinManager coinManager = new CoinManager();
        String keyID = "ccc65483";
        String token = "5403g2sgf15r6rcap022zhbgg482ys824";
        String result = null;
        result = coinManager.sign(token, keyID);
        System.out.println("SIGN:"+result);
        
        result = coinManager.queryCoin(token, keyID);
        System.out.println("QUERY:"+result);
        
        String reason = "订单送积分";
        String userID = "4";
        String num = "-100";
        result = coinManager.updateCoin(userID, token, reason, num, keyID);
        
        System.out.println(result);
        
        result = coinManager.queryCoin(token, keyID);
        System.out.println("QUERY:"+result);
        
    }

}
