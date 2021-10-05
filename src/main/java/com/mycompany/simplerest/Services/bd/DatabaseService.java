
package com.mycompany.simplerest.Services.bd;

import com.mycompany.simplerest.entities.Entity;
import java.util.List;

public abstract class DatabaseService<T> {
    public abstract void save(Entity entity);
    public abstract void delete(String id);
    public abstract T findOne (String id);
    public abstract List<T> find (Class<T> clazz);
  


}
