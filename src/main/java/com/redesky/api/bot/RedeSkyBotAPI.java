package com.redesky.api.bot;

public class RedeSkyBotAPI {
    
    private static BotManager botManager;
    
    public static void main(String args[]){
        System.out.println("[RedeSkyBotAPI] Inicializando...");
        
        botManager = new BotManager();
    }
    
    public static BotManager getManager(){
        return botManager;
    }
    
}
