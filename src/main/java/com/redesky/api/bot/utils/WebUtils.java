package com.redesky.api.bot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class WebUtils {
    
    public static String getTextURL(String url) throws Exception {
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));
        
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        
        while((inputLine = bufferedReader.readLine()) != null){
            stringBuilder.append(inputLine);
        }
        bufferedReader.close();
        
        return stringBuilder.toString();
    }
    
}
