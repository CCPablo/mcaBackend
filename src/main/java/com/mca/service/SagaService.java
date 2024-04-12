package com.mca.service;

import com.mca.infrastructure.model.Saga;
import com.mca.infrastructure.repository.SagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaService {

    private final SagaRepository sagaRepository;

    public SagaService(SagaRepository sagaRepository) {
        this.sagaRepository = sagaRepository;
    }

    public List<Saga> getVideoGameRelatedSagas(Long videoGameId) {
        return sagaRepository.findRelatedSagasByGameIdSortedByRelevance(videoGameId);
    }

    public List<Long> getVideoGameRelatedSagasId(Long videoGameId) {
        return getVideoGameRelatedSagas(videoGameId).stream().map(Saga::getId).toList();
    }
}
