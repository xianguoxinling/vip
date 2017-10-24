package com.platform.auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class AuthManager
{
    private String mainAuthServer = null;
    private String salveAuthServer = null;
    private String server = null;

    public AuthManager()
    {
        // mainAuthServer = "101.37.81.165";
//        mainAuthServer = "auth.puckart.com";
//        salveAuthServer = "122.4.241.3";
        // server = mainAuthServer;
        mainAuthServer = "127.0.0.1";
        salveAuthServer = "127.0.0.1";
        server = salveAuthServer;
    }

    public String createAuth(String name, String explain, String target, String keyID)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/auth/create_auth.action?name=" + name + "&keyID=" + keyID + "&target=" + target + "&explain=" + explain;
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
            return createAuth(name, explain, target, keyID);
        } finally
        {
            // 阿里云内主要还是这个鉴权
            server = mainAuthServer;
        }

        return result;
    }

    public String addAuthToPerson(String authID, String userID, String keyID)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/auth/add_auth_person.action?authID=" + authID + "&userID=" + userID + "&keyID=" + keyID;
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

    public String checkAuth(String authName, String token, String target, String keyID)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/auth/check_auth.action?authName=" + authName + "&token=" + token + "&target=" + target + "&keyID=" + keyID;
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

    public static void main(String args[])
    {
        AuthManager authManager = new AuthManager();
        String authName = "update_coin";
        String keyID = "nhatrgog4yqq4b";
        String target = "all";
        String explain = "update coin nei bu";

        // String result = authManager.createAuth(authName, explain, target,
        // keyID);
        // JSONObject jsonObject = JSONObject.fromObject(result);
        // String code = (String) jsonObject.get("code");
        // System.out.println(code);

        String authID = "1";
        String userID = "puckart";
        //
        // String result = authManager.addAuthToPerson(authID, userID,keyID);
        // JSONObject jsonObject = JSONObject.fromObject(result);
        // String code = (String) jsonObject.get("code");
        // System.out.println(code);

        // String token = "30snwlp81plj9t4ekgnwm3mimx26lu0ypuckart";
        String token = "onlrebvpafdn5h3v3syasoptsov54e2bpuckart";
        String result = authManager.checkAuth(authName, token, target, keyID);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String code = (String) jsonObject.get("code");
        System.out.println(code);
    }

}
