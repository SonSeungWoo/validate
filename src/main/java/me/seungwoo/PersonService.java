package me.seungwoo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.Optional;

/**
 * Created by Leo.
 * User: sonseungwoo
 * Date: 2019-02-26
 * Time: 21:30
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person getPersonByName(String firstName){
        return personRepository.findByFirstName(firstName);
    }

    public String personSave(Person data){
        ConstraintViolation<Person> validate = ValidatorUtils.constraintViolation(data);
        if (validate == null){
            personRepository.save(data);
        } else {
            return "["+ validate.getPropertyPath() + "]" + validate.getMessage();
        }
        return "success";
    }
}
