package com.app.barber.other.builder;

import com.app.barber.model.Barber;
import com.app.barber.model.Social;
import com.app.barber.other.enums.SocialType;

public class SocialBuilder {

    private Social social = new Social();

    public static SocialBuilder builder(){
        return new SocialBuilder();
    }

    public SocialBuilder url(String url){
        social.setUrl(url);
        return this;
    }

    public SocialBuilder barber(Barber barber){
        social.setBarber(barber);
        return this;
    }

    public SocialBuilder type(SocialType type){
        social.setSocialType(type);
        return this;
    }

    public Social build(){
        return social;
    }
}
