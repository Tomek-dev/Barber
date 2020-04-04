package com.app.barber.security.oauth;

import com.app.barber.dao.OAuthUserRepository;
import com.app.barber.model.OAuthUser;
import com.app.barber.model.User;
import com.app.barber.other.builder.OAuthUserBuilder;
import com.app.barber.other.enums.AuthProvider;
import com.app.barber.other.validation.OAuth2AuthenticationProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private OAuthUserRepository repository;

    @Autowired
    public CustomOAuth2UserService(OAuthUserRepository repository) {
        this.repository = repository;
    }



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try{
            return processOAuth2User(userRequest, oAuth2User);
        }catch (AuthenticationException e) {
            throw e;
        }catch (Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User){
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())){
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<OAuthUser> optional = repository.findByEmail(oAuth2UserInfo.getEmail());
        OAuthUser oAuthUser;
        if(optional.isPresent()){
            oAuthUser = optional.get();
            if(!oAuthUser.getAuthProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()))){
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        oAuthUser.getProviderId() + "account. Please use your " + oAuthUser.getProviderId() +" account to login.");
            }
            oAuthUser = update(oAuthUser, oAuth2UserInfo);
        }else{
            oAuthUser = register(oAuth2UserRequest, oAuth2UserInfo);
        }
        return OAuthPrincipal.create(oAuthUser, oAuth2User.getAttributes());
    }

    private OAuthUser register(OAuth2UserRequest request, OAuth2UserInfo oAuth2UserInfo){
        OAuthUser oAuthUser = OAuthUserBuilder.builder()
                .email(oAuth2UserInfo.getEmail())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .name(oAuth2UserInfo.getName())
                .providerId(oAuth2UserInfo.getId())
                .provider(AuthProvider.valueOf(request.getClientRegistration().getRegistrationId().toUpperCase()))
                .build();
        repository.save(oAuthUser);
        return oAuthUser;
    }

    private OAuthUser update(OAuthUser user, OAuth2UserInfo oAuth2UserInfo){
        user.setName(oAuth2UserInfo.getName());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        repository.save(user);
        return user;
    }
}
