package com.petstore.service.store;

import com.petstore.model.Pet;
import com.petstore.model.Store;
import com.petstore.service.exception.StoreObjectNotPresentException;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    Store save(Store store);

    Optional<Store> findById(Integer storeId);

    Store update(Store store);

    void delete(Integer storeId);

    List<Store> findAll();

    Store addPets(Pet pet, Integer storeId) throws StoreObjectNotPresentException;
}
