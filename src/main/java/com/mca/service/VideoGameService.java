package com.mca.service;

import com.mca.controller.dto.GameDTO;
import com.mca.infrastructure.model.Promotion;
import com.mca.infrastructure.model.Stock;
import com.mca.infrastructure.model.VideoGame;
import com.mca.infrastructure.repository.VideoGameRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VideoGameService {

    private final VideoGameRepository videoGameRepository;

    public VideoGameService(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    public List<GameDTO> getRelatedVideoGamesByItsSagas(long videoGameId) {
        return videoGameRepository.findRelatedVideoGamesByGameIdSortedByRelevance(videoGameId).stream()
                .map(videoGame -> GameDTO.builder()
                        .id(videoGame.getId().toString())
                        .title(videoGame.getTitle())
                        .availability(getAvailability(videoGame))
                        .price(getVideoGamePrice(videoGame))
                        .build())
                .toList();
    }

    private BigDecimal getVideoGamePrice(VideoGame videoGame) {
        Optional<Promotion> currentPromotion = videoGame.getPromotions().stream()
                .filter(promotion -> promotion.getValidFrom().before(Timestamp.from(Instant.now())))
                .min(Comparator.comparing(Promotion::getValidFrom)
                        .thenComparing(Promotion::getPrice));
        return currentPromotion.map(Promotion::getPrice).orElse(null);
    }

    private boolean getAvailability(VideoGame videoGame) {
        Optional<Stock> availableStock = videoGame.getStocks().stream()
                .filter(stock -> Boolean.TRUE.equals(stock.getAvailability()))
                .findFirst();
        return availableStock.isPresent();
    }
}
