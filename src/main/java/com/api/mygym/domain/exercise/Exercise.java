package com.api.mygym.domain.exercise;

import com.api.mygym.domain.serie.Serie;
import com.api.mygym.domain.training.Training;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercises")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Exercise {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Serie> series;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

}
