package com.mca.infrastructure.repository;

import com.mca.infrastructure.model.Saga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaRepository extends JpaRepository<Saga, Long> {

    @Query("SELECT s " +
            "FROM Saga s " +
            "JOIN VideoGameSaga vs ON s.id = vs.saga.id " +
            "WHERE vs.videoGame.id = :videoGameId " +
            "GROUP BY s.id " +
            "ORDER BY COUNT(vs.saga.id) DESC") // Order by the number of times the saga is related to the game
    List<Saga> findRelatedSagasByGameIdSortedByRelevance(@Param("videoGameId") Long videoGameId);

}
