package me.seungwoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Leo.
 * User: sonseungwoo
 * Date: 2019-02-26
 * Time: 21:30
 */
@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    private final PersonService personService;

    private final ObjectMapper objectMapper;

    @GetMapping("/person/{id}")
    public Person getPerson(@Valid Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.get();
    }

    @PostMapping("/person")
    public String savePerson(@RequestBody PersonDto person) {
        Person person1 = objectMapper.convertValue(person, Person.class);
        String msg = personService.personSave(person1);
        return msg;
    }

    @PostMapping("/person2")
    @Transactional
    public ResponseEntity<?> savePerson2(@RequestBody @Valid PersonDto person) {
        Person person1 = objectMapper.convertValue(person, Person.class);
        return ResponseEntity.ok(personRepository.save(person1));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
