package com.eduproctor.tut;

import com.eduproctor.tut.entity.QuestionType;
import com.eduproctor.tut.entity.Role;
import com.eduproctor.tut.repository.RoleRepository;
import com.eduproctor.tut.repository.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EduProctorApplication {


	// We have added annotations for all the components so now all the components are in the radar of spring so when the spring application starts all the objects will be created and added to Spring COntainer
	// Later we can say that we need particular class
	public static void main(String[] args) {
		SpringApplication.run(EduProctorApplication.class, args);
	}

		@Bean
		public CommandLineRunner addRolesOnStartup(RoleRepository roleRepository) {
			return args -> {
				// Create roles if they don't already exist
				if (roleRepository.findByRoleName("ADMIN") == null) {
					Role adminRole = new Role();
	//				adminRole.setId(1);
					adminRole.setRoleName("ADMIN");
					roleRepository.save(adminRole);
				}

				if (roleRepository.findByRoleName("USER") == null) {
					Role userRole = new Role();
	//				userRole.setId(2);
					userRole.setRoleName("USER");
					roleRepository.save(userRole);
				}

	//			if (roleRepository.findByName("GUEST") == null) {
	//				Role guestRole = new Role();
	//				guestRole.setName("GUEST");
	//				roleRepository.save(guestRole);
	//			}
			};

		}

	@Bean
	public CommandLineRunner addQuestionTypesOnStartup(QuestionTypeRepository questionTypeRepository) {
		return args -> {
			addIfNotExists(questionTypeRepository, "QuizQuestion");
			addIfNotExists(questionTypeRepository, "FillInTheBlank");
			addIfNotExists(questionTypeRepository, "ShortAnswer");
			addIfNotExists(questionTypeRepository, "EssayQuestion");
		};
	}

	private void addIfNotExists(QuestionTypeRepository repository, String typeName) {
		if (repository.findByTypeName(typeName) == null) {
			QuestionType questionType = new QuestionType();
			questionType.setTypeName(typeName);
			repository.save(questionType);
		}
	}


}
