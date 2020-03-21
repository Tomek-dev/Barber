package com.app.barber.other.builder;

import com.app.barber.model.Barber;
import com.app.barber.model.User;
import com.app.barber.other.enums.Role;

import java.util.Set;

public class UserBuilder {

    private User user = new User();

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder username(String username){
        user.setUsername(username);
        return this;
    }

    public UserBuilder password(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder email(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder barber(Barber barber){
        user.setBarber(barber);
        return this;
    }

    public UserBuilder roles(Set<Role> roles){
        user.setRoles(roles);
        return this;
    }

    public User build(){
        return user;
    }
}
