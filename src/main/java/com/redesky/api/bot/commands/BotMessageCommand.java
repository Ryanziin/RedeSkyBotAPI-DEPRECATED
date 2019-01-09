package com.redesky.api.bot.commands;

import com.redesky.api.bot.RedeSkyBotAPI;
import com.redesky.api.bot.punishments.PunishmentsManager;
import com.redesky.api.bot.punishments.data.Solicitation;
import com.redesky.api.bot.utils.CommandCreator;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class BotMessageCommand extends CommandCreator {
    
    public BotMessageCommand(IDiscordClient discordClient) {
        super("/", "botmessage", false, discordClient);
    }
    
    @Override
    public void execute(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args){
        if(args.length < 2){
            channel.sendMessage(user.mention() + ", utilize: /message <mensagem>.");
            return;
        }
        
        String text = "";
        for(int i = 1; i < args.length; i++){
            if(i == (args.length - 1)){
                text = text + args[i] + "";
            } else {
                text = text + args[i] + " ";
            }
        }
        
        message.delete();
        channel.sendMessage(text);
    }
    
}
