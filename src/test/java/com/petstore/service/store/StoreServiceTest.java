package com.petstore.service.store;

import com.petstore.model.Pet;
import com.petstore.model.PetSex;
import com.petstore.model.PetType;
import com.petstore.model.Store;
import com.petstore.repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@SpringBootTest
class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;

    @Autowired
    StoreRepository liveStoreRepository;

    @InjectMocks
    StoreService storeService = new StoreServiceImpl();
//    StoreService storeService;

    Store testStore;
    Pet pet;

    Logger log = Logger.getLogger(getClass().getName());

    @BeforeEach
    void setUp() {
//        storeService = new StoreServiceImpl();
        testStore = new Store();
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        when(storeService.save(testStore)).thenReturn(testStore);
        storeService.save(testStore);

        verify(storeRepository, times(1)).save(testStore);
    }

    @Test
    void findById() {
        when(storeService.findById(testStore.getId())).thenReturn(Optional.of(testStore));
        storeService.findById(testStore.getId());

        verify(storeRepository, times(1)).findById(testStore.getId());
    }

    @Test
    void update() {
        when(storeService.update(testStore)).thenReturn(testStore);
        storeService.update(testStore);

        verify(storeRepository, times(1)).save(testStore);
    }

    @Test
    void delete() {
        doNothing().when(storeRepository).deleteById(testStore.getId());
        storeService.delete(testStore.getId());

        verify(storeRepository, times(1)).deleteById(testStore.getId());
    }

    @Test
    void findAll() {
        List<Store> storeList = new ArrayList<>();
        when(storeService.findAll()).thenReturn(storeList);
        storeService.findAll();

        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void addPets() {

        //Retrieve store details
        Store savedStore = liveStoreRepository.findById(1).orElse(null);
        assertThat(savedStore).isNotNull();
        assertThat(savedStore.getPetList()).hasSize(4);

        //add pets to store
        Pet pet = new Pet();
        pet.setName("Lingo");
        pet.setBreed("Rotwieler");
        pet.setType(PetType.DOG);
        pet.setSex(PetSex.FEMALE);
        pet.setAge(3);
        pet.setBirthDate(new Date());
        pet.setPetStore(savedStore);

        savedStore.addPet(pet);

        // save store
        savedStore = liveStoreRepository.save(savedStore);

        // retrieve store
        Store savedStore2 = liveStoreRepository.findById(1).orElse(null);
        assertNotNull(savedStore2);
        log.info("Retrieve Pets From DB --> "+ savedStore2);


        // verify that pet is added to store
        assertThat(savedStore2.getPetList()).hasSize(5);
        log.info("Added pets to the store --> "+ savedStore2);


//        when(storeService.addPets(pet)).thenReturn(testStore);
//        storeService.addPets(pet);
//
//        verify(storeRepository, times(1)).save(testStore);
    }
}