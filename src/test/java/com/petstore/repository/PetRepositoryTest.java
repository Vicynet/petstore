package com.petstore.repository;

import com.petstore.model.Pet;
import com.petstore.model.PetSex;
import com.petstore.model.PetType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"classpath:db\\insert-pet.sql"})
class PetRepositoryTest {

    Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    PetRepository petRepository;

    Pet testPetData;

    @BeforeEach
    void setUp() {
        testPetData = petRepository.findById(12).orElse(null);
        assertThat(testPetData).isNotNull();
        log.info("Test pet data retrieves from database -->"+ testPetData);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPetObject_thenSaveToDatabaseTest(){
        Pet pet = new Pet();
        pet.setName("Bingo");
        pet.setType(PetType.DOG);
        pet.setSex(PetSex.MALE);
        pet.setBreed("German Shepherd");
        pet.setBirthDate(new Date());
        pet.setAge(5);

        log.info("Created pet Object --> "+ pet);
        assertThat(pet.getId()).isNull();

        //save Pet object to the database
        pet = petRepository.save(pet);

        log.info("After saving pet object --> "+ pet);
        assertThat(pet.getId()).isNotNull();
    }
}