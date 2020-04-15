package io.openvidu.classroom.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import io.openvidu.classroom.demo.lesson.Lesson;
import io.openvidu.classroom.demo.lesson.LessonRepository;
import io.openvidu.classroom.demo.user.User;
import io.openvidu.classroom.demo.user.UserRepository;

@Controller
public class DatabaseInitializer implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Sample users
		User user1 = new User("fucker1@gmail.com", "pass", "Fucking Fucker", "ROLE_STUDENT");
		User user2 = new User("fucker2@gmail.com", "pass", "Shitting Fucker", "ROLE_STUDENT");
		User user3 = new User("hostess@gmail.com",  "pass", "Sweet hostess", "ROLE_TEACHER");
		User user4 = new User("victor@devadmin.com",  "pass", "Victor", "ROLE_STUDENT");
		User user5 = new User("fin@devadmin.com",  "pass", "Fin", "ROLE_STUDENT");
		User user6 = new User("sheishin@devadmin.com",  "pass", "sheishin", "ROLE_STUDENT");
		User user7 = new User("fox@devadmin.com",  "pass", "Fox", "ROLE_STUDENT");
		User user8 = new User("d@devadmin.com",  "pass", "d", "ROLE_STUDENT");

		//Saving users
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		userRepository.save(user5);
		userRepository.save(user6);
		userRepository.save(user7);
		userRepository.save(user8);

		//Sample lessons
		Lesson c1 = new Lesson("Chatting room1", user3);
		Lesson c2 = new Lesson("Chatting room2", user3);
		
		c1.getAttenders().add(user1);
		c1.getAttenders().add(user2);
		c1.getAttenders().add(user3);
		c1.getAttenders().add(user4);
		c1.getAttenders().add(user5);
		c1.getAttenders().add(user6);
		c1.getAttenders().add(user7);
		c1.getAttenders().add(user8);

		c2.getAttenders().add(user1);
		c2.getAttenders().add(user3);
		c2.getAttenders().add(user4);
		c2.getAttenders().add(user5);
		c2.getAttenders().add(user6);
		c2.getAttenders().add(user7);
		c2.getAttenders().add(user8);

		//Saving lessons
		lessonRepository.save(c1);
		lessonRepository.save(c2);
	}

}
