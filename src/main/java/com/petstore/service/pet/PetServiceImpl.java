package com.petstore.service.pet;

import com.petstore.model.Pet;
import com.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet findById(Integer petId) {
        return petRepository.findById(petId).orElse(null);
    }

    @Override
    public Pet update(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Integer petId) {
        petRepository.deleteById(petId);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }
}
