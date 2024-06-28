package com.example.hobbysky;

import com.example.hobbysky.model.Hobby;
import com.example.hobbysky.model.Role;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.HobbyRepository;
import com.example.hobbysky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Comparator;

@Slf4j
//@RequiredArgsConstructor
@SpringBootApplication
@ComponentScan(basePackages = "com.example.hobbysky")
public class HobbyskyApplication implements CommandLineRunner {

	private final HobbyRepository hobbyRepository;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

    public HobbyskyApplication(HobbyRepository hobbyRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.hobbyRepository = hobbyRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
		SpringApplication.run(HobbyskyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		hobbyRepository.findAll().stream().map(Hobby::toString).forEach(log::warn);

		//make specific user by yourself
//		var user = new User();
//		user.setEmail("mary@gmail.com");
//		var password = passwordEncoder.encode("p");
//		System.out.println("Encrypted password: " + password);
//
//		user.setPassword(password);
//		user.setRole(Role.director);
//		user.setFirstName("mari");
//		user.setLastName("sikh");
//		System.out.println("User: " + user.toString());
//		userRepository.save(user);
	}
}

