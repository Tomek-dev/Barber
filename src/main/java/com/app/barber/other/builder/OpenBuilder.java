package com.app.barber.other.builder;

import com.app.barber.model.Barber;
import com.app.barber.model.Open;

import java.time.LocalTime;

public class OpenBuilder {

    private Open open = new Open();

    public static OpenBuilder builder(){
        return new OpenBuilder();
    }

    public OpenBuilder open(LocalTime open){
        this.open.setOpen(open);
        return this;
    }

    public OpenBuilder close(LocalTime close){
        open.setClose(close);
        return this;
    }

    public OpenBuilder barber(Barber barber){
        open.setBarber(barber);
        return this;
    }

    public Open build(){
        return open;
    }
}
