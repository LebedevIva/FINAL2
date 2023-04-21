package com.example.springsecurityapplication.servises;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.PersonRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsServise implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsServise(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Получаем пользователя из таблици person по логину с формы аутентификации
        Optional<Person> person = personRepository.findByLogin(username);

        if(person.isEmpty()){
//            Выбрасываем исключение и выводим через Spring Security на страницу
            throw  new UsernameNotFoundException("Пользователь не найден");
        }

//        Возвращаем объект пользователя
        return new PersonDetails(person.get(), "your error message here");
    }
}
