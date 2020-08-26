package com.petstore.repository;

import com.petstore.model.Pet;
import com.petstore.model.PetSex;
import com.petstore.model.PetType;
import com.petstore.model.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.logging.Logger;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreRepositoryTest {

    Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    StoreRepository storeRepository;

    Store store;

    @BeforeEach
    void setUp() {
        store = storeRepository.findById(1).orElse(null);
        assertThat(store).isNotNull();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createNewStoreTest() {
        Store londonStore = new Store();
        londonStore.setStoreName("London Store");
        londonStore.setState("Lagos");
        londonStore.setCity("Yaba");
        londonStore.setAddress("Herbert Macaulay Way");
        londonStore.setStoreNumber(312);
        londonStore.setCountry("Nigeria");

        assertThat(londonStore.getId()).isNull();

        londonStore = storeRepository.save(londonStore);
        log.info("After saving store object-->"+ londonStore);
        assertThat(londonStore.getId()).isNotNull();
    }

    @Test
    void whenAddPetsToStore_thenSaveToDbTest() {
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setBreed("Bull Dog");
        pet.setType(PetType.DOG);
        pet.setSex(PetSex.FEMALE);
        pet.setAge(5);
        pet.setBirthDate(new Date());
        pet.setPetStore(store);
        store.addPet(pet);

        store = storeRepository.save(store);

        log.info("Added pets to the store --> "+ store);
    }

    @Test
    void whenStoreIsRetrieved_thenRetrievedStoredPetsTest() {
        Pet pet = new Pet();
        pet.setName("BloBlo");
        pet.setBreed("Bull Dog");
        pet.setType(PetType.DOG);
        pet.setSex(PetSex.FEMALE);
        pet.setAge(6);
        pet.setBirthDate(new Date());
        pet.setPetStore(store);
        store.addPet(pet);

        store = storeRepository.save(store);

        log.info("Store Pet Objects to DB --> "+ store);

        Store savedStore = storeRepository.findById(store.getId()).orElse(null);
        assertThat(savedStore.getPetList()).isNotNull();
        assertThat(savedStore.getPetList()).hasSize(2);
    }
}