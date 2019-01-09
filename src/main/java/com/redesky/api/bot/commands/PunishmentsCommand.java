package com.redesky.api.bot.commands;

import com.redesky.api.bot.RedeSkyBotAPI;
import com.redesky.api.bot.punishments.PunishmentsManager;
import com.redesky.api.bot.punishments.data.Solicitation;
import com.redesky.api.bot.utils.CommandCreator;
import com.vdurmont.emoji.EmojiManager;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class PunishmentsCommand extends CommandCreator {
    
    public PunishmentsCommand(IDiscordClient discordClient) {
        super("/", "punishments", false, discordClient);
    }
    
    @Override
    public void execute(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {
        if(args.length != 3){
            channel.sendMessage(user.mention() + ", utilize: /punishments <dia> <mês>.");
            return;
        }
        
        PunishmentsManager punishmentsManager = RedeSkyBotAPI.getManager().getPunishmentsManager();
        
        if(punishmentsManager.getSolicitation() != null){
            channel.sendMessage(user.mention() + ", uma solicitação já está em progresso. Aguarde terminar para que você consiga iniciar outro requerimento.");
            return;
        }
        
        channel.sendMessage(user.mention() + ", estou solicitando, aguarde...");
        
        Solicitation solicitation = new Solicitation(user, channel, Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        
        RedeSkyBotAPI.getManager().getPunishmentsManager().createSolicitation(solicitation);
        /*
        EmbedBuilder embedBuilder = new EmbedBuilder();
        
        embedBuilder.withTitle("Ranking de Punições | " + args[1] + "/" + args[2]);
        embedBuilder.withColor(255, 204, 0);
        embedBuilder.withDescription("1º Lugar - nick - ? punições.\n"
                + "2º Lugar - nick - ? punições.\n"
                + "3º Lugar - nick - ? punições.\n"
                + "4º Lugar - nick - ? punições.\n"
                + "5º Lugar - nick - ? punições.\n");
        
        channel.sendMessage(embedBuilder.build()); */
    }
    
}
