package Spellcards.repository;

import Spellcards.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query ("SELECT c FROM Card c WHERE c.name LIKE %:name%")
    List<Card> findByName (@Param("name") String name);

    @Query ("SELECT c FROM Card WHERE c.components LIKE %:components%")
    List<Card> findByLevel (@Param("level") String level);

    @Query ("SELECT c FROM Card c WHERE c.school = :school")
    List<Card> findBySchool (@Param("school") String school);

    @Query ("SELECT c FROM Card c WHERE c.castingTime = :castingTime")
    Card findByCastingTime (@Param("castingTime") String castingTime);

    @Query("SELECT c FROM Card WHERE c.range = :range")
    Card findByRange (@Param("range") String range);

    @Query ("SELECT c FROM Card WHERE c.components LIKE %:components%")
    Card findByComponents (@Param("components") String components);

    @Query ("SELECT c FROM Card WHERE c.duration = :duration")
    Card findByDuration (@Param("duration") String duration);

    @Query ("SELECT c FROM Card WHERE c.classes LIKE %:classes%")
    Card findByClasses (@Param("classes") String classes);

    @Query ("SELECT c FROM Card WHERE c.description LIKE %:description%")
    Card findByDescription (@Param("description") String description);

    @Query ("SELECT c FROM Card WHERE c.domain LIKE %:domain%")
    Card findByDomain (@Param("domain") String domain);

    @Query ("SELECT c FROM Card WHERE c.source LIKE %:source%")
    Card findBySource (@Param("source") String source);

    @Query ("SELECT c FROM Card WHERE c.tags LIKE %:tags%")
    Card findByTags (@Param("tags") String tags);

}
