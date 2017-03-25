package com.sbm.liveorderboard;

import java.util.Comparator;

import static java.util.Comparator.comparing;

public enum OrderType {
    BUY(comparing(QuantityAtPrice::getPrice).reversed()),
    SELL(comparing(QuantityAtPrice::getPrice));

    private final Comparator<QuantityAtPrice> comparator;

    OrderType( Comparator<QuantityAtPrice> comparator ){
        this.comparator = comparator;
    }

    // order type determines summary sort order so it lives here
    Comparator<QuantityAtPrice> comparator(){
        return comparator;
    }
}
