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

@Service
public class VideoGameService {

    private final VideoGameRepository videoGameRepository;

    public VideoGameService(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    public GameDTO getVideoGameById(long videoGameId) {
        return videoGameRepository.findById(videoGameId)
                .map(videoGame -> GameDTO.builder()
                        .id(videoGame.getId().toString())
                        .title(videoGame.getTitle())
                        .availability(getLatestAvailability(videoGame))
                        .price(getMostRecentAndCheapPrice(videoGame))
                        .build())
                .orElse(null);
    }

    public List<GameDTO> getRelatedVideoGamesByItsSagas(long videoGameId) {
        return videoGameRepository.findRelatedVideoGamesByGameIdSortedByRelevance(videoGameId).stream()
                .map(videoGame -> GameDTO.builder()
                        .id(videoGame.getId().toString())
                        .title(videoGame.getTitle())
                        .availability(getLatestAvailability(videoGame))
                        .price(getMostRecentAndCheapPrice(videoGame))
                        .build())
                .toList();
    }

    private BigDecimal getMostRecentAndCheapPrice(VideoGame videoGame) {
        return videoGame.getPromotions().stream()
                .filter(promotion -> promotion.getValidFrom().toInstant().isBefore(Instant.now()))
                .max(Comparator.comparing(Promotion::getValidFrom)
                        .thenComparing(Promotion::getPrice, Comparator.reverseOrder()))
                .map(Promotion::getPrice)
                .orElse(null);
    }

    private boolean getLatestAvailability(VideoGame videoGame) {
        return videoGame.getStocks().stream()
                .min(Comparator.comparing(Stock::getLastUpdated))
                .map(stock -> Boolean.TRUE.equals(stock.getAvailability()))
                .orElse(false);
    }
}
