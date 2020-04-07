package com.app.barber.model;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    private Barber barber;

    private Image() {
    }

    public static class Builder{
        private String url;
        private Barber barber;

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder barber(Barber barber){
            this.barber = barber;
            return this;
        }

        public Image build(){
            Image image = new Image();
            image.barber = this.barber;
            image.url = this.url;
            return image;
        }
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Barber getBarber() {
        return barber;
    }
}
