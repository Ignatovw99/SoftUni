package contracts;

import exception.DuplicateModelException;
import exception.NonExistantModelException;

public interface Database<T> {
    T getByModel(String model) throws NonExistantModelException;
    void save(T item) throws DuplicateModelException;
}
