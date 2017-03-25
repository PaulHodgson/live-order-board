package com.sbm.liveorderboard;

import java.util.List;

// It is anticipated that public getter methods will be used by client code
public class OrderSummary {
    private final List<QuantityAtPrice> buyOrders;
    private final List<QuantityAtPrice> sellOrders;

    OrderSummary(List<QuantityAtPrice> buyOrders, List<QuantityAtPrice> sellOrders) {
        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;
    }

    public List<QuantityAtPrice> getBuyOrders() {
        return buyOrders;
    }

    public List<QuantityAtPrice> getSellOrders() {
        return sellOrders;
    }

    // was useful for tests
    @Override
    public String toString() {
        return "OrderSummary{" + "buyOrders=" + buyOrders + ", sellOrders=" + sellOrders + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderSummary that = (OrderSummary) o;

        if (buyOrders != null ? !buyOrders.equals(that.buyOrders) : that.buyOrders != null) return false;
        return sellOrders != null ? sellOrders.equals(that.sellOrders) : that.sellOrders == null;
    }

    @Override
    public int hashCode() {
        int result = buyOrders != null ? buyOrders.hashCode() : 0;
        result = 31 * result + (sellOrders != null ? sellOrders.hashCode() : 0);
        return result;
    }
}
