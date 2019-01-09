package com.redesky.api.bot.web;

import com.redesky.api.bot.utils.WebUtils;
import com.redesky.api.bot.web.data.PunishmentsLastsData;
import org.json.JSONArray;
import org.json.JSONObject;

public class APIWebManager {
    
    public static String getVersionAPI() throws Exception {
        JSONObject jsonObject = null;
        
        jsonObject = new JSONObject(WebUtils.getTextURL(APIURLs.WEBSITE_API));
        
        if(jsonObject != null){
            return jsonObject.getString("Sky API version");
        }
                
        return null;
    }
    
    public static PunishmentsLastsData getPunishmentsLasts() throws Exception {
        PunishmentsLastsData punishmentsLasts = null;
        JSONArray jsonArray = null;
        
        jsonArray = new JSONArray(WebUtils.getTextURL(APIURLs.API_PUNISHMENTS + "lasts"));
        
        if(jsonArray != null){
            punishmentsLasts = new PunishmentsLastsData();
            
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                
                if(jsonObject != null){
                    switch(i){
                        case 0:
                            punishmentsLasts.setFirst(jsonObject.getString("title"), jsonObject.getJSONObject("data").getInt("total"));
                            break;
                        case 1:
                            punishmentsLasts.setSecond(jsonObject.getString("title"), jsonObject.getJSONObject("data").getInt("total"));
                            break;
                        case 2:
                            punishmentsLasts.setThird(jsonObject.getString("title"), jsonObject.getJSONObject("data").getInt("total"));
                            break;
                        case 3:
                            punishmentsLasts.setFourth(jsonObject.getString("title"), jsonObject.getJSONObject("data").getInt("total"));
                            break;
                        case 4:
                            punishmentsLasts.setFifth(jsonObject.getString("title"), jsonObject.getJSONObject("data").getInt("total"));
                            break;
                        case 5:
                            punishmentsLasts.setSixth(jsonObject.getString("title"), jsonObject.getJSONObject("data").getInt("total"));
                            break;
                        case 6:
                            punishmentsLasts.setSeventh(jsonObject.getString("title"), jsonObject.getJSONObject("data").getInt("total"));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        
        return punishmentsLasts;
    }
    
}
