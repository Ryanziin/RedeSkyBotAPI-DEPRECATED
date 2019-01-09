package com.redesky.api.bot.commands;

import com.redesky.api.bot.RedeSkyBotAPI;
import com.redesky.api.bot.punishments.PunishmentsManager;
import com.redesky.api.bot.punishments.data.Solicitation;
import com.redesky.api.bot.utils.CommandCreator;
import com.redesky.api.bot.web.APIWebManager;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class InfoCommand extends CommandCreator {
    
    public InfoCommand(IDiscordClient discordClient) {
        super("/", "info", false, discordClient);
    }
    
    @Override
    public void execute(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args){
        PunishmentsManager punishmentsManager = RedeSkyBotAPI.getManager().getPunishmentsManager();
        Solicitation solicitation = punishmentsManager.getSolicitation();
        
        if(solicitation == null){
            channel.sendMessage(user.mention() + ", não há nenhuma solicitação no momento.");
            return;
        }
        
        channel.sendMessage(user.mention() + ", o atual request é de " + solicitation.getTitle() + ".\n"
                + "Progresso: " + solicitation.getPage() + "/" + solicitation.getLastPage() + ".");
    }
    
}
