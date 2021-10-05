package com.mycompany.simplerest.Services.bd;

import com.mycompany.simplerest.entities.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaticTemp<T> extends DatabaseService {

    private static List<Entity> entities = new ArrayList<>();

    @Override
    public void save(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void delete(String id) {
        Entity entity = entities
                .stream()
                .filter(_entity -> _entity.getId().equals(id))
                .findFirst()
                .get();

        if (entity != null) {
            entities.remove(entity);
        }
    }

    @Override
    public T findOne(String id) {
        return (T) entities
                .stream()
                .filter(_entity -> _entity.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List find(Class clazz) {
        return (List<T>) entities
                .stream()
                .collect(Collectors.toList());
    }
}
