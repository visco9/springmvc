package com.visco9.services;

import java.util.List;

/**
 * Created by x on 2/18/2017.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}