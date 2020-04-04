package com.app.barber.other.builder;

import com.app.barber.model.OAuthUser;
import com.app.barber.other.enums.AuthProvider;

public class OAuthUserBuilder {

    private OAuthUser oAuthUser = new OAuthUser();

    public static OAuthUserBuilder builder(){
        return new OAuthUserBuilder();
    }

    public OAuthUserBuilder email(String email){
        oAuthUser.setEmail(email);
        return this;
    }

    public OAuthUserBuilder name(String name){
        oAuthUser.setName(name);
        return this;
    }

    public OAuthUserBuilder providerId(String providerId){
        oAuthUser.setProviderId(providerId);
        return this;
    }

    public OAuthUserBuilder imageUrl(String imageUrl){
        oAuthUser.setImageUrl(imageUrl);
        return this;
    }

    public OAuthUserBuilder provider(AuthProvider provider){
        oAuthUser.setAuthProvider(provider);
        return this;
    }

    public OAuthUser build(){
        return oAuthUser;
    }
}
