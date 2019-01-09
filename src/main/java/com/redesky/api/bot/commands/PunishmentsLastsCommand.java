package com.redesky.api.bot.commands;

import com.redesky.api.bot.utils.CommandCreator;
import com.redesky.api.bot.web.APIWebManager;
import com.redesky.api.bot.web.data.PunishmentsLastsData;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class PunishmentsLastsCommand extends CommandCreator {
    
    public PunishmentsLastsCommand(IDiscordClient discordClient) {
        super("/", "punishments-lasts", false, discordClient);
    }
    
    @Override
    public void execute(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {
        PunishmentsLastsData punishmentsLasts = null;
        
        channel.sendMessage(user.mention() + ", estou solicitando, aguarde...");
        try {
            punishmentsLasts = APIWebManager.getPunishmentsLasts();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        if(punishmentsLasts == null){
            channel.sendMessage(user.mention() + ", ocorreu um erro no sistema.\nTente novamente mais tarde.");
            return;
        }
        
        int total_punishments = punishmentsLasts.getFirstTotal() +
                punishmentsLasts.getSecondTotal() +
                punishmentsLasts.getThirdTotal() +
                punishmentsLasts.getFourthTotal() +
                punishmentsLasts.getFifthTotal() +
                punishmentsLasts.getSixthTotal() +
                punishmentsLasts.getSeventhTotal();
        
        EmbedBuilder embedBuilder = new EmbedBuilder();
        
        embedBuilder.withTitle("👑 Estatísticas dos últimos 7 dias 👑");
        embedBuilder.withColor(255, 204, 0);
        embedBuilder.withDescription(
                "▪ " + punishmentsLasts.getFirstTitle() + " - Foi realizado " + punishmentsLasts.getFirstTotal() + " punições.\n"
                + "▪ " + punishmentsLasts.getSecondTitle() + " - Foi realizado " + punishmentsLasts.getSecondTotal() + " punições.\n"
                + "▪ " + punishmentsLasts.getThirdTitle() + " - Foi realizado " + punishmentsLasts.getThirdTotal() + " punições.\n"
                + "▪ " + punishmentsLasts.getFourthTitle() + " - Foi realizado " + punishmentsLasts.getFourthTotal() + " punições.\n"
                + "▪ " + punishmentsLasts.getFifthTitle() + " - Foi realizado " + punishmentsLasts.getFifthTotal() + " punições.\n"
                + "▪ " + punishmentsLasts.getSixthTitle() + " - Foi realizado " + punishmentsLasts.getSixthTotal() + " punições.\n"
                + "▪ " + punishmentsLasts.getSeventhTitle() + " - Foi realizado " + punishmentsLasts.getSeventhTotal() + " punições.\n"
                + " \n "
                + "Total de punições: " + total_punishments + ".");
        
        channel.sendMessage(embedBuilder.build());
    }
    
}
