package com.app.barber.service;

import com.app.barber.dao.OAuthUserDao;
import com.app.barber.model.OAuthUser;
import com.app.barber.other.dto.AuthenticatedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthUserService {

    private OAuthUserDao oAuthUserDao;

    @Autowired
    public OAuthUserService(OAuthUserDao oAuthUserDao) {
        this.oAuthUserDao = oAuthUserDao;
    }

    public AuthenticatedDto authenticated(OAuthUser oAuthUser) {
        AuthenticatedDto authenticatedDto = new AuthenticatedDto();
        authenticatedDto.setName(oAuthUser.getName());
        authenticatedDto.setEmail(oAuthUser.getEmail());
        authenticatedDto.setType("oauth");
        authenticatedDto.setImageUrl(oAuthUser.getImageUrl());
        authenticatedDto.setId(oAuthUser.getId());
        return authenticatedDto;
    }
}
