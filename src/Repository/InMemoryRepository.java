package Repository;


import Domain.IValidator;
import Domain.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Domain.*;

public class InMemoryRepository<T extends Entity> implements IRepository<T> {

    private Map <String, T> storage = new HashMap <>();
    private IValidator <T> validator;

    public InMemoryRepository(IValidator < T > validator) {
        this.validator = validator;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public void insert(T entity) {
        if (storage.containsKey( entity.getId() )){
            throw new RepositoryException ("An entity with given id already exists");
        }
        validator.validate(entity);
        storage.put( entity.getId(), entity );
    }

    @Override
    public List < T > getAll() {
        return new ArrayList <>( storage.values() );
    }
}

