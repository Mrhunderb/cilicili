package com.chameleon.cilicili.dao;

import java.util.List;

public interface Dao {

    public void insert(Object obj);

    public void update(Object obj);


    public Object selectById(String id);

    public void deleteById(String id);
    
    public List<?> selectAll();

}
