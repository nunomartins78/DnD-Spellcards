package SpellCards.controller;

import SpellCards.dto.CardDTO;
import SpellCards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("")
    public ResponseEntity<List<CardDTO>> getAllCards(){
        List<CardDTO> card = cardService.getAllCards();
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable int id){
        CardDTO card = cardService.getCardById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <CardDTO> getCardById(@PathVariable int id){
        CardDTO card = cardService.getCardById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }*/

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CardDTO>> getByName(@PathVariable String name){
        List<CardDTO> card = cardService.getCardByName(name);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<CardDTO>> getByLevel (@PathVariable String level){
        List<CardDTO> card = cardService.getCardsByLevel(level);
        return new ResponseEntity<>(card, HttpStatus.OK);

    }

    @GetMapping("/school/{school}")
    public ResponseEntity<List<CardDTO>> getBySchool(@PathVariable String school){
        List<CardDTO> card = cardService.getCardBySchool(school);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }
    // CREATE GETS FOR REMAINING PARAMETERS
    @PostMapping
    public ResponseEntity<CardDTO> createCard(@Valid @RequestBody CardDTO cardDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();

                for(FieldError error : errors){
                    System.out.println(error.getObjectName() + "-"+error.getDefaultMessage());
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CardDTO createdCard = cardService.createCard(cardDTO);
        return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long id, @Valid @RequestBody CardDTO cardDTO,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                System.out.println(error.getObjectName() + "-" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CardDTO updatedCard = cardService.updateCard(id, cardDTO);
        return new ResponseEntity<>(updatedCard, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardDTO> deleteCard(@PathVariable Long id){
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
