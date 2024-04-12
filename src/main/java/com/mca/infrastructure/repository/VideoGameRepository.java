package com.mca.infrastructure.repository;

import com.mca.infrastructure.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

    @Query("SELECT vg FROM VideoGame vg " +
            "JOIN VideoGameSaga vgs ON vg.id = vgs.videoGame.id " +
            "JOIN VideoGameSaga vgs2 ON vgs.saga.id = vgs2.saga.id " +
            "WHERE vgs2.videoGame.id = :videoGameId " +
            "AND vg.id != :videoGameId " +
            "GROUP BY vg " +
            "ORDER BY COUNT(vgs2.videoGame.id) DESC") // Order by the number of shared sagas
    List<VideoGame> findRelatedVideoGamesByGameIdSortedByRelevance(@Param("videoGameId") Long videoGameId);
}
