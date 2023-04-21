package com.example.springsecurityapplication.config;

import com.example.springsecurityapplication.servises.PersonDetailsServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Основной конфиг для конфигурации безопасности в приложении
@Configuration
@EnableWebSecurity
//Аннотация сообщает, что доступно разграничение ролей на основе аннотаций
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PersonDetailsServise personDetailServise;

    @Autowired
    public SecurityConfig(PersonDetailsServise personDetailsServise) {
        this.personDetailServise = personDetailsServise;
    }

    //    Метод по настройке аутентификации
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        Производим аутентификацию с помощью сервиса
        authenticationManagerBuilder.userDetailsService(personDetailServise).passwordEncoder(getPasswordEncoder());
    }

//        Конфигурируем сам Spring Security
//        Конфигурируем авторизацию
    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        Отключаем защиту от межсплитовой подделки запросов
        http
//                Все страницы защищены аутентификацией
                        .authorizeRequests()

//                Указываем, что страница /admin доступна всем пользователям с ролью ADMIN
//                .antMatchers("/admin").hasAnyRole("ADMIN")


//                        Указываем, что не аутентифицированные пользователи могут заходить на страницу с формой и ошибкой
                        .antMatchers("/auth/login", "/error", "/auth/registration", "/product","/static/**", "/product/info/{id}", "/img/**","/resources/**", "product/product", "/product/searching","/css/**", "/js/**").permitAll()


//                .antMatchers("/product/info/{id}").hasAnyRole("USER")

//                     Указываем что все остальные страницы доступны пользователям с ролями USER и ADMIN
                        .anyRequest().hasAnyRole("USER","ADMIN")

//                Указываем дальнейшую конфигурацию аутентификации и соединениями с настройкой доступа
//        Указываем какой url запрос будет отправляться при заходе на закрытые страницы
        .and()
        .formLogin().loginPage("/auth/login")

//                Указываем на какой url будут уходить данные с формы. url задался по умолчанию, Spring Security будет ждать логин и пароль с формы и сверять из бд
                .loginProcessingUrl("/process_login")

//                Указываем куда отправлять пользователя после успешной аутентификации
//                Второй аргумент это перенаправление на данную страницу в любом случае при успешной аутентификации
                .defaultSuccessUrl("/index", true)

//                Указываем, куда перенаправляется пользователь при неудачной аутентификации
                .failureUrl("/auth/login?error")
                .and()

//                Указываем, что при переходе на /logout будет очищена сессия пользователя и перенаправление на /auth/login
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");


    }





    public class SecurityConfigcss extends WebSecurityConfigurerAdapter {

        // other configuration code

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
