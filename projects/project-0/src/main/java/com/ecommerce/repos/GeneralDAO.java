package com.ecommerce.repos;

public interface GeneralDAO <T>{

    T create(T obj);

    T update(T obj);

}
