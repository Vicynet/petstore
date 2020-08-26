package com.petstore.service.store;

import com.petstore.model.Store;

import java.util.List;

public interface StoreService {

    Store save(Store store);

    Store findById(Integer storeId);

    Store update(Store store);

    void delete(Integer storeId);

    List<Store> findAll();
}
