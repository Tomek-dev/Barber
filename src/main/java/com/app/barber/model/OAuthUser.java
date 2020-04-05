package com.app.barber.model;

import com.app.barber.other.enums.AuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@Entity
public class OAuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String imageUrl;

    private String name;

    private String providerId;

    @OneToMany(mappedBy = "customer")
    private List<Visit> visits = new LinkedList<>();

    @OneToMany(mappedBy = "owner")
    private List<Review> reviews = new LinkedList<>();

    private AuthProvider authProvider;

    public OAuthUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
