package kayzer.utils;


import kayzer.domain.entities.UserRole;
import kayzer.repositories.UserRoleRepository;

import java.util.ListIterator;

public class UserRoleIterator<T extends UserRole> implements ListIterator<T> {

    private ListIterator<T> userRolesOrderedByPower;

    public UserRoleIterator(UserRoleRepository userRoleRepository) {
        this.userRolesOrderedByPower = (ListIterator<T>) userRoleRepository.findAllByOrderByPower()
                .listIterator();
    }

    @Override
    public boolean hasNext() {
        return this.userRolesOrderedByPower.hasNext();
    }

    @Override
    public T next() {
        return this.userRolesOrderedByPower.next();
    }

    @Override
    public boolean hasPrevious() {
        return this.userRolesOrderedByPower.hasPrevious();
    }

    @Override
    public T previous() {
        return this.userRolesOrderedByPower.previous();
    }

    @Override
    public int nextIndex() {
        return this.userRolesOrderedByPower.nextIndex();
    }

    @Override
    public int previousIndex() {
        return this.userRolesOrderedByPower.previousIndex();
    }

    @Override
    public void remove() {
        this.userRolesOrderedByPower.remove();
    }

    @Override
    public void set(T t) {
        this.userRolesOrderedByPower.set(t);
    }

    @Override
    public void add(T t) {
        this.userRolesOrderedByPower.add(t);
    }
}
