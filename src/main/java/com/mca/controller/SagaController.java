package com.mca.controller;

import com.mca.controller.dto.RelatedGameSagas;
import com.mca.service.SagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game-saga")
public class SagaController {

    private final SagaService sagaService;

    public SagaController(SagaService sagaService) {
        this.sagaService = sagaService;
    }

    @GetMapping("/{gameId}/related-sagas")
    public ResponseEntity<RelatedGameSagas> getRelatedSagas(@PathVariable Long gameId) {
        RelatedGameSagas relatedSagas = new RelatedGameSagas();
        List<Long> sagasId = sagaService.getVideoGameRelatedSagas(gameId);
        relatedSagas.addAll(sagasId);
        return ResponseEntity.ok(relatedSagas);
    }
}
