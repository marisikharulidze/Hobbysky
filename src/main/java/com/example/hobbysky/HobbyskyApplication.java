package com.example.hobbysky;

import com.example.hobbysky.model.*;
import com.example.hobbysky.repository.EventRepository;
import com.example.hobbysky.repository.HobbyRepository;
import com.example.hobbysky.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;

@Slf4j
//@RequiredArgsConstructor
@SpringBootApplication
@ComponentScan(basePackages = "com.example.hobbysky")
public class HobbyskyApplication implements CommandLineRunner {
	private final EventRepository eventRepository;

	private final HobbyRepository hobbyRepository;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

    public HobbyskyApplication(HobbyRepository hobbyRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
							   EventRepository eventRepository) {
        this.hobbyRepository = hobbyRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.eventRepository = eventRepository;
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

		//Event
//		LocalDate localDate = LocalDate.now();
//		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

//		Location location = new Location(1L,"Germany", "Berlin");
//		Hobby hobby = new Hobby(7L,"Mountain climbing");
//
//		var event = new Event();
//		event.setId(5L);
//		event.setName("smth");
//		event.setLocation(location);
//		event.setHobby(hobby);
//		event.setCreationDate(date);
//		event.setNumOfParticipants(5);
//		event.setLastModifiedDate(date);
//		event.setDate(date);
//		event.setImage("Sdff");
//		event.setStatus("Sdff");
//		System.out.println(event);
//		eventRepository.save(event);

		//update user
		var user = new User();
		user.setId(2L);
		user.setEmail("maryyy@gmail.com");
		var password = passwordEncoder.encode("p");
		System.out.println("Encrypted password: " + password);

		user.setPassword(password);
		user.setRole(Role.director);
		user.setFirstName("mari");
		user.setLastName("sikh");
		userRepository.save(user);

	}
}

