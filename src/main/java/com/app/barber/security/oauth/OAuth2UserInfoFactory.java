package com.app.barber.security.oauth;

import com.app.barber.other.enums.AuthProvider;
import com.app.barber.other.exception.OAuth2AuthenticationProcessingException;
import com.app.barber.security.oauth.providers.FaceBookOAuth2UserInfo;
import com.app.barber.security.oauth.providers.GithubOAuth2UserInfo;
import com.app.barber.security.oauth.providers.GoogleOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes){
        if(registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.getValue())){
            return new GoogleOAuth2UserInfo(attributes);
        }else if(registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.getValue())){
            return new FaceBookOAuth2UserInfo(attributes);
        }else if(registrationId.equalsIgnoreCase(AuthProvider.GITHUB.getValue())){
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login  with " + registrationId + " is ont supported");
        }
    }
}
