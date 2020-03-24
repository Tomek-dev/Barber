package com.app.barber.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    private UUID token;

    private LocalDateTime date;

    @OneToOne
    private User user;

    private Token() {
    }

    public UUID getToken() {
        return token;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public static final class Builder{

        private UUID token;
        private LocalDateTime date = LocalDateTime.now().plusDays(1);
        private User user;

        public Builder token(UUID token){
            this.token = token;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder date(LocalDateTime date){
            this.date = date;
            return this;
        }

        public Token build(){
            Token token = new Token();
            token.date = this.date;
            token.user = this.user;
            token.token = this.token;
            return token;
        }
    }
}
