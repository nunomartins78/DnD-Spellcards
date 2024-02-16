package SpellCards.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "cards")


public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private String school;

    @Column(nullable = false)
    private String castingTime;

    @Column(nullable = false)
    private String range;

    @Column(nullable = false)
    private String components;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String classes;

    @Column(nullable = false)
    private String description;

    @Column
    private String atHigherLevels;

    //VERIFY IF DOMAIN CAN BE NULL
    @Column(nullable = false)
    private String domain;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String tags;
}
