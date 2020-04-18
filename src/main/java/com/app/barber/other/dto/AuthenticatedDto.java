package com.app.barber.other.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedDto {

    private Long id;
    private String name;
    private String email;
    private String type;
    private Long barberId;
    private String imageUrl;
}
