package com.mca.controller;

import com.mca.controller.dto.GameDTO;
import com.mca.controller.dto.RelatedGames;
import com.mca.service.VideoGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class VideoGameController {

    private final VideoGameService videoGameService;

    public VideoGameController (VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long gameId) {
        GameDTO gameDTO = videoGameService.getVideoGameById(gameId);
        if(gameDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameDTO);
    }

    @GetMapping("/{gameId}/saga")
    public ResponseEntity<RelatedGames> getRelatedGames(@PathVariable Long gameId) {
        RelatedGames relatedGames = new RelatedGames();
        List<GameDTO> gameDTOS = videoGameService.getRelatedVideoGamesByItsSagas(gameId);
        relatedGames.addAll(gameDTOS);
        return ResponseEntity.ok(relatedGames);
    }
}
