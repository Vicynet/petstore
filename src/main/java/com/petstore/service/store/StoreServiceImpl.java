package com.petstore.service.store;

import com.petstore.model.Pet;
import com.petstore.model.Store;
import com.petstore.repository.StoreRepository;
import com.petstore.service.exception.StoreObjectNotPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Optional<Store> findById(Integer storeId) {
        return storeRepository.findById(storeId);
    }

    @Override
    public Store update(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void delete(Integer storeId) {
       storeRepository.deleteById(storeId);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store addPets(Pet pet, Integer storeId) throws NullPointerException, StoreObjectNotPresentException {

        if(pet == null) {
            throw new NullPointerException("Pet object cannot be epmty");
        }

//        Store savedStore = storeRepository.findById(storeId).orElse(null);
        Optional<Store> result = storeRepository.findById(storeId);

        if(result.isPresent()) {
            Store savedStore = result.get();
            savedStore.addPet(pet);
            return storeRepository.save(savedStore);
        }
        else {
            throw new StoreObjectNotPresentException("Store object not present in the database");
        }
    }
}
