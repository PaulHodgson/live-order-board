package com.sbm.liveorderboard;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.sbm.liveorderboard.OrderType.BUY;
import static com.sbm.liveorderboard.OrderType.SELL;
import static java.math.BigDecimal.ZERO;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.*;

public class LiveOrderBoard {
    // Non-functional requirements not discussed in the specification so assumed concurrent usage.
    // A map allows a simple and fast implementation of cancel by id.
    private Map<String,Order> orders = new ConcurrentHashMap<>();

    public String register(Order order){
        String orderId = randomUUID().toString();
        orders.put(orderId,order);
        return orderId;
    }

    public void cancel(String orderId) {
        orders.remove(orderId);
    }

    public OrderSummary getOrderSummary() {
        return new OrderSummary(summarise(BUY), summarise(SELL));
    }

    private List<QuantityAtPrice> summarise(OrderType type){
        Map<BigDecimal, BigDecimal> summation =
            orders.values().stream().filter(
                o -> o.getType().equals(type)).collect(
                    groupingBy(Order::getPricePerKG, mapping(Order::getQuantityInKG, reducing(ZERO, BigDecimal::add))) );

        return summation.entrySet().stream().map(
            e -> new QuantityAtPrice(e.getKey(),e.getValue())).sorted(type.comparator()).collect(toList());
    }

    // Added for testability
    Order find(String orderId){
        return orders.get(orderId);
    }
}
