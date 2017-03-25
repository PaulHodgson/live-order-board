package com.sbm.liveorderboard

import static com.sbm.liveorderboard.OrderType.SELL
import static com.sbm.liveorderboard.OrderType.BUY

class LiveOrderBoardSpec extends spock.lang.Specification {
    def board = new LiveOrderBoard()
    def userId = 'user1'
    def quantity = 3.5
    def price = 308.0

    def order = new Order(userId, quantity, price, SELL)

    def "register an order should create an order and return an order id"(){
        when:
        def orderId = board.register(order)
        def found = board.find(orderId)

        then:
        found == order
        found.userId == userId
        found.quantityInKG == quantity
        found.pricePerKG == price
        found.type == SELL
    }

    def "register an order returns a unique order id"(){
        when:
        def orderId1 = board.register(order)
        def orderId2 = board.register(order)

        then:
        orderId1 != orderId2
    }

    def "cancel an order should remove the order from the board"(){
        setup:
        def orderId = board.register(order)

        when:
        board.cancel(orderId)

        then:
        board.find(orderId) == null
    }

    def "cancel an order should only cancel the requested order"(){
        setup:
        def orderId1 = board.register(order)
        def orderId2 = board.register(order)

        when:
        board.cancel(orderId1)

        then:
        board.find(orderId1) == null
        board.find(orderId2) != null
    }

    def "get summary should return sell and buy orders grouped and summed by price and ordered by quantity"(){
        setup:
        board.register(new Order("user1", 3.5, 306, BUY))
        board.register(new Order("user2", 1.2, 310, BUY))
        board.register(new Order("user3", 1.5, 307, BUY))
        board.register(new Order("user4", 2.0, 306, BUY))

        board.register(new Order("user1", 3.5, 306, SELL))
        board.register(new Order("user2", 1.2, 310, SELL))
        board.register(new Order("user3", 1.5, 307, SELL))
        board.register(new Order("user4", 2.0, 306, SELL))

        def expectedBuyOrders =
            [new QuantityAtPrice( 310, 1.2), new QuantityAtPrice( 307, 1.5), new QuantityAtPrice( 306, 5.5) ]
        def expectedSellOrders =
            [new QuantityAtPrice( 306, 5.5), new QuantityAtPrice( 307, 1.5), new QuantityAtPrice( 310, 1.2) ]
        def expectedSummary = new OrderSummary( expectedBuyOrders, expectedSellOrders )

        when:
        def summary = board.getOrderSummary()

        then:
        summary == expectedSummary
    }

    def "get summary should return an empty summary if the live order board is empty"(){
        when:
        def summary = board.getOrderSummary()

        then:
        summary == new OrderSummary([],[])
    }
}
