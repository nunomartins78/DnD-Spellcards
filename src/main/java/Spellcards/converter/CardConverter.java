package Spellcards.converter;

import Spellcards.dto.CardDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import Spellcards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    @Autowired
    ObjectMapper objectMapper;

    public CardDTO fromCardEntityToCardDTO(Card card){
        return CardDTO.builder()
                .name(card.getName())
                .level(card.getLevel())
                .school(card.getSchool())
                .castingTime(card.getCastingTime())
                .range(card.getRange())
                .components(card.getComponents())
                .duration(card.getDuration())
                .classes(card.getClasses())
                .description(card.getDescription())
                .atHigherLevels(card.getAtHigherLevels())
                .domain(card.getDomain())
                .source(card.getSource())
                .tags(card.getTags())
                .build();
    }

    public Card fromCardDTOToCardEntity (CardDTO cardDTO){
        return objectMapper.convertValue(cardDTO, Card.class);
    }
}
