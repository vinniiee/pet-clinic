package com.springframework.petclinic.services.mapservice;

import com.springframework.petclinic.model.BaseEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity,ID extends Number> {

    protected Map<ID,T> map = new HashMap<ID,T>();

    Set<T> findAll(){
        return new HashSet<T>(map.values());
    }
    T findById(ID id){
        return map.get(id);
    }
    T save(T object){



            map.put( (ID)object.getId(), object);

        return object;
    }
    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
    void deleteById(ID id){
        map.remove(id);
    }

}
