package com.redesky.api.bot.commands;

import com.redesky.api.bot.utils.CommandCreator;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class VersionCommand extends CommandCreator {
    
    public VersionCommand(IDiscordClient discordClient) {
        super("/", "version", false, discordClient);
    }
    
    @Override
    public void execute(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {
        channel.sendMessage(user.mention() + ", a minha versão é 1.3 adaptada para a API 2.0 da Rede Sky.");
    }
    
}
