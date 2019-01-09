package com.redesky.api.bot;

import com.redesky.api.bot.commands.APICommand;
import com.redesky.api.bot.commands.BotLogCommand;
import com.redesky.api.bot.commands.BotMessageCommand;
import com.redesky.api.bot.commands.InfoCommand;
import com.redesky.api.bot.commands.PunishmentsCommand;
import com.redesky.api.bot.commands.PunishmentsLastsCommand;
import com.redesky.api.bot.commands.StopSolicitation;
import com.redesky.api.bot.commands.VersionCommand;
import com.redesky.api.bot.punishments.PunishmentsManager;
import com.redesky.api.bot.runnables.SolicitationsRunnable;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class BotManager {
    
    private IDiscordClient iDiscordClient;
    
    private PunishmentsManager punishmentsManager;
    
    public BotManager(){
        try {
            this.iDiscordClient = createClient("NTIxNDc2MTc0MTk1NTg5MTIw.Du8_KA.jaEW3IGxRAkIZOBekRFHSpVLQTM");
            System.out.println("[RedeSkyBotAPI] Bot inicializado!");
            registerCommands(iDiscordClient);
            
            this.punishmentsManager = new PunishmentsManager();
        } catch(DiscordException e){
            e.printStackTrace();
            System.out.println("[RedeSkyBotAPI] Não foi possível inicializar o bot.");
        }
        
        /*
            RUNNABLES
        */
        new SolicitationsRunnable();
    }
    
    private void registerCommands(IDiscordClient discordClient){
        new PunishmentsCommand(discordClient);
        new VersionCommand(discordClient);
        new APICommand(discordClient);
        new PunishmentsLastsCommand(discordClient);
        new InfoCommand(discordClient);
        new BotLogCommand(discordClient);
        new BotMessageCommand(discordClient);
        new StopSolicitation(discordClient);
        System.out.println("[RedeSkyBotAPI] Comandos habilitados.");
    }
    
    private IDiscordClient createClient(String token) throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        
        return clientBuilder.login();
    }
    
    public IDiscordClient getDiscordClient(){
        return this.iDiscordClient;
    }
    
    public PunishmentsManager getPunishmentsManager(){
        return this.punishmentsManager;
    }
    
}
