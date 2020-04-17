package com.app.barber.model;

import com.app.barber.other.enums.AuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class OAuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String imageUrl;

    private String name;

    private String providerId;

    @OneToMany(mappedBy = "customer")
    private Set<Visit> visits = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Review> reviews = new HashSet<>();

    private AuthProvider authProvider;


}
