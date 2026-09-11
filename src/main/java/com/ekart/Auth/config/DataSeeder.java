package com.ekart.Auth.config;

import com.ekart.Auth.entity.User;
import com.ekart.Auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedUser(UserRepository userRepository)
    {

        return args -> {
            PasswordEncoder encoder=new BCryptPasswordEncoder();

            if(userRepository.findByUserName("admin").isEmpty())
            {
                User admin= new User();
                admin.setUserName("admin");
                admin.setUserpassword(encoder.encode("admin123"));
                admin.setUserRoles(List.of("ADMIN"));
                userRepository.save(admin);
            }

            if(userRepository.findByUserName("ashu").isEmpty())
            {
                User user=new User();
                user.setUserName("ashu");
                user.setUserpassword(encoder.encode("ashu123"));
                user.setUserRoles(List.of("USER"));
                userRepository.save(user);
            }
        };


    }
}
