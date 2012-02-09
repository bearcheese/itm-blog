package hu.bearmaster.itm.common.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    T create(T t);
    
    T find(ID id);
    
    List<T> findAll();
    
    long countAll();   

    T update(T t);
    
    void delete(ID id);
}