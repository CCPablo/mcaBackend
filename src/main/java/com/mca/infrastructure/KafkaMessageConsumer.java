package com.mca.infrastructure;

import com.mca.infrastructure.model.VideoGameEvent;
import com.mca.service.StockService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {

    private final StockService stockService;

    public KafkaMessageConsumer(StockService stockService) {
        this.stockService = stockService;
    }

    @KafkaListener(topics = "${topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload VideoGameEvent stock) {
        stockService.updateStock(1, true, stock.getTime_update());
    }
}