package com.mca.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "VIDEOGAME")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "videoGame")
    private Set<Stock> stocks;

    @ManyToMany
    @JoinTable(
            name = "VIDEOGAME_SAGA",
            joinColumns = @JoinColumn(name = "VIDEOGAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "SAGA_ID")
    )
    private Set<Saga> sagas;

}
