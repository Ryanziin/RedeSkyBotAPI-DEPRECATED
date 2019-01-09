package com.redesky.api.bot.runnables;

import com.redesky.api.bot.RedeSkyBotAPI;
import com.redesky.api.bot.punishments.PunishmentsManager;
import com.redesky.api.bot.punishments.data.Solicitation;
import java.util.Timer;
import java.util.TimerTask;
import sx.blah.discord.handle.obj.IChannel;

public class SolicitationsRunnable {
    
    private Timer timer;
    
    public SolicitationsRunnable(){
        this.timer = new Timer("timer_solicitations");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run(){
                PunishmentsManager punishmentsManager = RedeSkyBotAPI.getManager().getPunishmentsManager();
                if(punishmentsManager != null){
                    Solicitation solicitation = punishmentsManager.getSolicitation();
                    if(solicitation != null){
                        if(solicitation.getPage() > solicitation.getLastPage()){
                            solicitation.finish();
                        } else {
                            solicitation.count();
                        }
                    }
                }
            }
        };
        this.timer.scheduleAtFixedRate(timerTask, 10 * 60, 10 * 60);
    }
    
    public Timer getTimer(){
        return this.timer;
    }
    
}
