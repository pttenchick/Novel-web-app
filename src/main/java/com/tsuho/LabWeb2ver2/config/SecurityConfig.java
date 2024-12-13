package com.tsuho.LabWeb2ver2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers("api/").permitAll()
                .requestMatchers("api/novels/createNovel").authenticated()
                        .requestMatchers("api/novels/get/**").permitAll()
                        .requestMatchers("api/users/getUserByName/**").permitAll()
                        .requestMatchers("api/users/getUserById/**").hasRole("ADMIN")
                        .requestMatchers("api/users/getUserByName/**").permitAll()
                        .requestMatchers("api/users/searchAuthorByName/**").permitAll()
                        .requestMatchers("api/novels/chapters/create").authenticated()
                        .requestMatchers("api/novels/chapters/edit/{chapterNumber}").authenticated()
                        .requestMatchers("api/novels/chapters/{chapterId}").permitAll()
                        .requestMatchers("api/novels/comments/create").authenticated()
                        .requestMatchers("api/novels/comments/edit/{chapterNumber}").authenticated()
                        .requestMatchers("api/novels/comments/{chapterId}").permitAll()
        );
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/", "/home").permitAll() // Разрешить доступ к корневым страницам
//                                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login") // Указать страницу логина
//                                .permitAll() // Разрешить всем доступ к странице логина
//                )
//                .logout(logout ->
//                        logout.permitAll() // Разрешить всем выходить из системы
//                );

        return http.build();
    }


}
