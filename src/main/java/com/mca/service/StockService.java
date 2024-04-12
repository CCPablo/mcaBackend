package com.mca.service;

import com.mca.infrastructure.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void updateStock(long stockId, boolean availability, Timestamp timeUpdate) {
        stockRepository.findById(stockId).ifPresent(stock -> {
            stock.setAvailability(availability);
            stock.setLastUpdated(timeUpdate);
            stockRepository.save(stock);
        });
    }

}
