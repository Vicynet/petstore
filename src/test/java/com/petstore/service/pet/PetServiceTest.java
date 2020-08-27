package com.petstore.service.pet;

import com.petstore.model.Pet;
import com.petstore.repository.PetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PetServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService;

    Pet testPet;

    @BeforeEach
    void setUp() {
        petService =  new PetServiceImpl();
        testPet = new Pet();
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        when(petService.save(testPet)).thenReturn(testPet);
        petService.save(testPet);

        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void findById() {
        when(petService.findById(1)).thenReturn(Optional.of(testPet));
        petService.findById(testPet.getId());

        verify(petRepository, times(1)).findById(testPet.getId());
    }

    @Test
    void update() {
        when(petService.update(testPet)).thenReturn(testPet);
        petService.update(testPet);

        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void delete() {
        doNothing().when(petRepository).delete(testPet);
        petService.delete(testPet.getId());

        verify(petRepository, times(1)).deleteById(testPet.getId());
    }

    @Test
    void findAll() {
        List<Pet> petList = new ArrayList<>();
        when(petService.findAll()).thenReturn(petList);
        petService.findAll();

        verify(petRepository, times(1)).findAll();
    }
}