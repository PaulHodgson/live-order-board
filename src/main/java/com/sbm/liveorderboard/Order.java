package com.sbm.liveorderboard;

import java.math.BigDecimal;

/*
 * Order CRUD operations could be implemented without an id, e.g. by passing the order around.
 * However using an id seems cleaner and more extensible
 */
public class Order{
    private final String userId;
    private final BigDecimal quantityInKG;
    private final BigDecimal pricePerKG;
    private final OrderType type;

    public Order(String userId, BigDecimal quantityInKG, BigDecimal pricePerKG, OrderType type) {
        this.userId = userId;
        this.quantityInKG = quantityInKG;
        this.pricePerKG = pricePerKG;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getQuantityInKG() {
        return quantityInKG;
    }

    public BigDecimal getPricePerKG() {
        return pricePerKG;
    }

    public OrderType getType() {
        return type;
    }
}
