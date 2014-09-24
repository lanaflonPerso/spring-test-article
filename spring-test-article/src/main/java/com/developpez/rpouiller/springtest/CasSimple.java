package com.developpez.rpouiller.springtest;

import org.springframework.stereotype.Component;

@Component
public class CasSimple implements ICasSimple {

    @Override
    public int additionner(final int param1, final int param2) {
        return param1 + param2;
    }
}