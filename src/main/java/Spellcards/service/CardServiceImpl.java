package Spellcards.service;

import Spellcards.converter.CardConverter;
import Spellcards.dto.CardDTO;
import Spellcards.model.Card;
import Spellcards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CardServiceImpl implements CardService{

    private CardRepository cardRepository;
    private CardConverter cardConverter;

    @Autowired
    private CardServiceImpl(CardRepository cardRepository, CardConverter cardConverter){
        this.cardRepository = cardRepository;
        this.cardConverter = cardConverter;
    }

    @Override
    public CardDTO createCard (CardDTO cardCreateDTO){
        Card card = cardConverter.fromCardDTOToCardEntity(cardCreateDTO);
        card = cardRepository.save(card);
        return cardConverter.fromCardEntityToCardDTO(card);
    }

    @Override
    public CardDTO getCardById (Long cardId){
        Card card = cardRepository.getReferenceById(cardId);
        return cardConverter.fromCardEntityToCardDTO(card);
    }

    @Override
    public List<CardDTO> getCardByName (String name){
        List<Card> cards = cardRepository.findByName(name);
        return cards.stream()
                .map(cardConverter::fromCardEntityToCardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> getCardsByLevel(String level) {
        List<Card> cards = cardRepository.findByLevel(level);
        return cards.stream()
                .map(cardConverter::fromCardEntityToCardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> getCardBySchool (String school){
        List<Card> cards = cardRepository.findBySchool(school);
        return cards.stream()
                .map(cardConverter::fromCardEntityToCardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CardDTO getCardByCastingTime (String castingTime){
        Card card = cardRepository.findByCastingTime(castingTime);
        return cardConverter.fromCardEntityToCardDTO(card);
    }



    @Override
    public List<CardDTO> getAllCards(){
        List<Card> cards = cardRepository.findAll();
        return cards.parallelStream()
                .map(cardConverter :: fromCardEntityToCardDTO)
                .toList();
    }

    @Override
    public CardDTO updateCard (Long id, CardDTO cardDTO){
        Card existingCard = cardRepository.getReferenceById(id);
        if (existingCard == null){
            throw new IllegalArgumentException("Spell not found.");
        }
        existingCard.setName(cardDTO.getName());
        existingCard.setLevel(cardDTO.getLevel());
        existingCard.setSchool(cardDTO.getSchool());
        existingCard.setCastingTime(cardDTO.getCastingTime());
        existingCard.setRange(cardDTO.getRange());
        existingCard.setComponents(cardDTO.getComponents());
        existingCard.setDuration(cardDTO.getDuration());
        existingCard.setClasses(cardDTO.getClasses());
        existingCard.setDescription(cardDTO.getDescription());
        existingCard.setAtHigherLevels(cardDTO.getAtHigherLevels());
        existingCard.setDomain(cardDTO.getDomain());
        existingCard.setTags(cardDTO.getTags());
        existingCard.setSource(cardDTO.getSource());
        Card updatedCard = cardRepository.save(existingCard);
        return cardConverter.fromCardEntityToCardDTO(updatedCard);
    }

    @Override
    public void deleteCard (Long cardId){
        Card card = cardRepository.getReferenceById(cardId);
        if (card == null){
            throw new IllegalArgumentException("Spell not found");
        }
        cardRepository.delete(card);
    }
}
