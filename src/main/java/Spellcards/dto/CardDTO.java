package Spellcards.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CardDTO {

    @NotBlank(message = "Please include the spell's name")
    private String name;

    @NotBlank(message = "Please include the spell's level")
    private String level;

    @NotBlank(message = "Please include the spell's school")
    private String school;

    @NotBlank(message = "Please include the spell's casting time")
    private String castingTime;

    @NotBlank(message = "Please include the spell's range")
    private String range;

    @NotBlank(message = "Please include the spell's components")
    private String components;

    @NotBlank(message = "Please include the spell's duration")
    private String duration;

    @NotBlank(message = "Please include the classes that can use this spell")
    private String classes;

    @NotBlank(message = "Please include the spell's description")
    private String description;

    private String atHigherLevels;

    private String domain;

    @NotBlank(message = "Please include the spell's source (book or homebrew)")
    private String source;

    @NotBlank(message = "Please include the spell's tags")
    private String tags;
}
