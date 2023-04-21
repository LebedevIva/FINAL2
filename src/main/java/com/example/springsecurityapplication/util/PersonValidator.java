package com.example.springsecurityapplication.util;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.servises.PersonServise;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PersonServise personServise;

    public PersonValidator(PersonServise personServise) {
        this.personServise = personServise;
    }

    //    В данном методе указываем для какой модели предназначен данный валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

//        Если поиск по логину не вернул пустое то пользователь занят
        if(personServise.getPersonFindByLogin(person) != null){
            errors.rejectValue("login", "", "Логин занят");
        }
    }
}
