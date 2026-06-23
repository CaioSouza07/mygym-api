package com.api.mygym.domain.user.preferences;

import com.api.mygym.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_preferences")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Preferences {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Integer defaultRestTime = 60;

    public Preferences(User user){
        this.user = user;
        this.defaultRestTime = 60;
    }
}
