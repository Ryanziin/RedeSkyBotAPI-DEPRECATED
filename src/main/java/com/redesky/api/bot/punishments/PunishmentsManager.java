package com.redesky.api.bot.punishments;

import com.redesky.api.bot.punishments.data.Solicitation;

public class PunishmentsManager {
    
    private Solicitation solicitation;
    
    public void createSolicitation(Solicitation solicitation){
        this.solicitation = solicitation;
    }
    
    public Solicitation getSolicitation(){
        return this.solicitation;
    }
    
}
