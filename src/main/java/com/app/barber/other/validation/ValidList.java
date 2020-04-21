package com.app.barber.other.validation;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.*;

@Data
public class ValidList<E> implements List<E>{

    @Delegate
    @Valid
    private List<E> list;
}
