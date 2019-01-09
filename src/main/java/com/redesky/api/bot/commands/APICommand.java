package com.redesky.api.bot.commands;

import com.redesky.api.bot.utils.CommandCreator;
import com.redesky.api.bot.web.APIWebManager;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class APICommand extends CommandCreator {
    
    public APICommand(IDiscordClient discordClient) {
        super("/", "api", false, discordClient);
    }
    
    @Override
    public void execute(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args){
        String API_VERSION = null;
        
        try {
            API_VERSION = APIWebManager.getVersionAPI();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        if(API_VERSION == null){
            channel.sendMessage(user.mention() + ", ocorreu um erro no sistema.\nTente novamente mais tarde.");
        }
        
        channel.sendMessage(user.mention() + ", a versão da API da Rede Sky atualmente é " + API_VERSION + ".");
    }
    
}
