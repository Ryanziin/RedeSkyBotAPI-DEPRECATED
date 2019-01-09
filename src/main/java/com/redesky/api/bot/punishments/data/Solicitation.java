package com.redesky.api.bot.punishments.data;

import com.redesky.api.bot.RedeSkyBotAPI;
import com.redesky.api.bot.utils.WebUtils;
import com.redesky.api.bot.web.APIURLs;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class Solicitation {
    
    private IUser iUser;
    private IChannel iChannel;
    private int day;
    private int month;
    
    private int page;
    private int last_page;
    
    private String title;
    private int total;
    private int total_revoke;
    
    private HashMap<String, Integer> punishments;
    private HashMap<String, Integer> punishments_revoke;
    
    public Solicitation(IUser iUser, IChannel iChannel, int day, int month){
        this.iUser = iUser;
        this.iChannel = iChannel;
        this.day = day;
        this.month = month;
        
        this.page = 1;
        this.last_page = 9999;
        
        this.total = -1;
        
        this.punishments = new HashMap<>();
        this.punishments_revoke = new HashMap<>();
    }
    
    public void count(){
        try {
            JSONArray jsonArray = null;
        
            jsonArray = new JSONArray(WebUtils.getTextURL(APIURLs.API_PUNISHMENTS + "lasts?day=" + day + "&month=" + month + "&page=" + page));
            
            if(jsonArray != null){
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                
                this.title = jsonObject.getString("title");
                
                JSONObject data = jsonObject.getJSONObject("data");
                
                this.total = data.getInt("total");
                
                JSONArray data_data = data.getJSONArray("data");

                for(int i = 0; i < data_data.length(); i++){
                    JSONObject jsonObject2 = data_data.getJSONObject(i);

                    JSONObject staffer_obj = jsonObject2.getJSONObject("staffer_obj");
                    
                    if(!jsonObject2.isNull("unban_staffer_obj")){
                        if(this.punishments_revoke.containsKey(staffer_obj.getString("nick"))){
                            int now_punishments = this.punishments_revoke.get(staffer_obj.getString("nick"));

                            this.punishments_revoke.put(staffer_obj.getString("nick"), now_punishments + 1);
                        } else {
                            this.punishments_revoke.put(staffer_obj.getString("nick"), 1);
                        }
                        this.total_revoke += 1;
                    }

                    if(this.punishments.containsKey(staffer_obj.getString("nick"))){
                        int now_punishments = this.punishments.get(staffer_obj.getString("nick"));

                        this.punishments.put(staffer_obj.getString("nick"), now_punishments + 1);
                    } else {
                        this.punishments.put(staffer_obj.getString("nick"), 1);
                    }
                }
                
                this.page = data.getInt("current_page") + 1;
                this.last_page = data.getInt("last_page");
                System.out.println(data.getInt("current_page") + "/" + this.last_page);
            }
        } catch(Exception e){
            
        }
    }
    
    public void finish(){
        IChannel CHANNEL_SOLICITATIONS = RedeSkyBotAPI.getManager().getDiscordClient().getChannelByID(521495560705015822L);
        
        //CHANNEL_SOLICITATIONS.sendMessage(iUser.mention() + ", conforme solicitado, segue abaixo as punições aplicadas " + this.getTitle() + ".");
        
        String message = "";
        for(Map.Entry<String, Integer> punishment : this.punishments.entrySet()) {
            String staffer = punishment.getKey();
            int punishments = punishment.getValue();
            int punishments_revoke2 = this.punishments_revoke.containsKey(staffer) ? this.punishments_revoke.get(staffer) : 0;
            message = message + "▪ `" + staffer + "` aplicou " + punishments + " punições. " + (punishments_revoke2 > 0 ? "(-" + punishments_revoke2 + " punições revogadas)" : "") + "\n";
        }
        
        //EmbedBuilder embedBuilder = new EmbedBuilder();
        
        /*embedBuilder.withTitle("Punições | " + this.getTitle());
        embedBuilder.withColor(255, 204, 0);
        embedBuilder.withDescription(message + 
                " \n" +
                "Total de punições: " + this.total + ".\n" +
                "Total de punições revogadas: " + this.total_revoke + ".");
        embedBuilder.withFooterIcon(iUser.getAvatarURL());
        embedBuilder.withFooterText("Solicitado por " + iUser.getName() + "."); */
        
        CHANNEL_SOLICITATIONS.sendMessage("Punições | " + this.getTitle());
        CHANNEL_SOLICITATIONS.sendMessage(message + 
                " \n" +
                "Total de punições: " + this.total + ".\n" +
                "Total de punições revogadas: " + this.total_revoke + ".");
        CHANNEL_SOLICITATIONS.sendMessage("Solicitado por: " + iUser.mention());
        
        //CHANNEL_SOLICITATIONS.sendMessage(embedBuilder.build());
        
        RedeSkyBotAPI.getManager().getPunishmentsManager().createSolicitation(null);
    }
    
    public IUser getUser(){
        return iUser;
    }
    
    public IChannel getChannel(){
        return iChannel;
    }
    
    public int getPage(){
        return this.page;
    }
    
    public int getLastPage(){
        return this.last_page;
    }
    
    public String getTitle(){
        return this.title;
    }
    
}
