package com.redesky.api.bot.utils;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public abstract class CommandCreator implements IListener<MessageReceivedEvent> {
    
    private String prefix;
    private String command;
    private boolean private_works;
    
    public CommandCreator(String prefix, String command, boolean private_works, IDiscordClient discordClient){
        this.prefix = prefix;
        this.command = command;
        this.private_works = private_works;
        
        discordClient.getDispatcher().registerListener(this);
    }
    
    public abstract void execute(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args);
    
    @EventSubscriber
    public void handle(MessageReceivedEvent event){
        if(event.getMessage().getContent().startsWith(this.prefix)){
            String[] args = event.getMessage().getContent().split(" ");
            
            if(args[0].equalsIgnoreCase(this.prefix + this.command)){
                IUser iUser = event.getAuthor();
                IChannel iChannel = event.getChannel();
                IGuild iGuild = event.getGuild();
                IMessage iMessage = event.getMessage();

                execute(iUser, iChannel, iGuild, iMessage, args);
            }
        }
    }
 
}