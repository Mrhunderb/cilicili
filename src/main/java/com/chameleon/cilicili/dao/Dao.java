package com.chameleon.cilicili.dao;

import java.util.List;

public interface Dao<T> {

    public void insert(T obj);

    public void update(T obj);

    public T selectById(String id);

    public void deleteById(String id);
    
    public List<T> selectAll();

}
