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
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Sql(scripts = {"classpath:db\\insert-pet.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//@Sql(scripts = {"classpath:db\\insert-pet.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PetRepositoryTest {

    Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    PetRepository petRepository;

    Pet testPetData;

    @BeforeEach
    void setUp() {
        testPetData = petRepository.findById(15).orElse(null);
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

        List<Pet> checkPets = petRepository.findAll();
        assertThat(checkPets.size()).isEqualTo(3);
        log.info("After adding pet object --> "+ pet);

    }

    @Test
    void whenFindAllPetsIsCalled_thenRetrieveListOfPetsTest() {
        List<Pet> allPets = petRepository.findAll();
        assertThat(allPets.size()).isEqualTo(2);
        log.info("All pets retrieved from the database -->"+allPets);
    }

    @Test
    void whenPetDetailsIsUpdated_thenUpdateDatabaseDetails() {

        //update pet name
//        testPetData = petRepository.findById(15).orElse(null);

        assertThat(testPetData.getName()).isEqualTo("Holla");
        testPetData.setName("Ehen");

        testPetData = petRepository.save(testPetData);
        assertThat(testPetData.getName()).isEqualTo("Ehen");
        log.info("Test After pet table is Updated -->"+ testPetData);
        List<Pet> checkPets = petRepository.findAll();
        assertThat(checkPets.size()).isEqualTo(3);

    }

    @Test
    void whenDeleteIsCalled_thenDeletePetDataTest() {
        List<Pet> allPets = petRepository.findAll();
        assertThat(allPets).isNotNull();
        assertThat(allPets.size()).isEqualTo(2);

        Pet savedPet = petRepository.findById(16).orElse(null);
        assertThat(savedPet).isNotNull();
        petRepository.deleteById(16);

        Pet deletePet = petRepository.findById(16).orElse(null);
        assertThat(deletePet).isNull();

        allPets = petRepository.findAll();
        assertThat(allPets).isNotNull();
        assertThat(allPets.size()).isEqualTo(1);

    }
}