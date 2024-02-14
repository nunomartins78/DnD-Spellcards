package Spellcards.service;

import Spellcards.dto.CardDTO;

import java.util.List;

public interface CardService {

    CardDTO createCard (CardDTO CardDTO);

    CardDTO getCardById(Long cardId);

    List<CardDTO> getCardByName(String name);

    List<CardDTO> getCardsByLevel(String level);

    List<CardDTO> getCardBySchool(String school);

    CardDTO getCardByCastingTime(String castingTime);

    List<CardDTO> getAllCards();

    CardDTO updateCard(Long id, CardDTO cardDTO);

    void deleteCard(Long cardID);

}
