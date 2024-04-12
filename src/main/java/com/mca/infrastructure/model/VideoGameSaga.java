package com.mca.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VIDEOGAME_SAGA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoGameSaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VIDEOGAME_ID")
    private VideoGame videoGame;

    @ManyToOne
    @JoinColumn(name = "SAGA_ID")
    private Saga saga;

}
