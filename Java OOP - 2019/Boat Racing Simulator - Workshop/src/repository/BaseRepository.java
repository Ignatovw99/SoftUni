package repository;

import application.Constants;
import contracts.Database;
import exception.DuplicateModelException;
import exception.NonExistantModelException;
import contracts.Modelable;


import java.util.HashSet;
import java.util.Set;

public class BaseRepository<T extends Modelable> implements Database<T> {
    private Set<T> data;

    protected BaseRepository() {
        this.data = new HashSet<>();
    }

    @Override
    public T getByModel(String model) throws NonExistantModelException {
        T item = this.data.stream().filter(x -> x.getModel().equals(model)).findAny().orElse(null);
        if (item == null) {
            throw new NonExistantModelException(Constants.NonExistentModelMessage);
        }
        return item;
    }

    @Override
    public void save(T item) throws DuplicateModelException {
        boolean addResult = this.data.add(item);
        if (!addResult) {
            throw new DuplicateModelException(Constants.DuplicateModelMessage);
        }
    }
}
