package com.api.mygym.domain.history;

import com.api.mygym.domain.exercise.Exercise;
import com.api.mygym.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "training_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class History {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public History(Exercise exercise, BigDecimal weight, User user) {
        this.exercise = exercise;
        this.createdAt = LocalDateTime.now();
        this.weight = weight;
        this.user = user;
    }
}
