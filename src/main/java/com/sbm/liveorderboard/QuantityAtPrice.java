package com.sbm.liveorderboard;

import java.math.BigDecimal;

// It is anticipated that public getter methods will be used by client code
public class QuantityAtPrice {
    private final BigDecimal price;
    private final BigDecimal quantity;

    QuantityAtPrice(BigDecimal price, BigDecimal quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    // was useful for tests
    @Override
    public String toString() {
        return "QuantityAtPrice{ price=" + price +", quantity=" + quantity + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuantityAtPrice that = (QuantityAtPrice) o;

        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
