package com.api.mygym.domain.serie;

import com.api.mygym.domain.exercise.Exercise;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "series")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Serie {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private int repetitions;

    @Column(nullable = false)
    private int order;

    @ManyToOne
    @JoinColumn(name = "id_exercise", nullable = false)
    private Exercise exercise;
}
