package com.ignatov.entities;

import com.ignatov.orm.annotations.Column;
import com.ignatov.orm.annotations.Entity;
import com.ignatov.orm.annotations.PrimaryKey;

@Entity(name = "departments")
public class Department {
    @PrimaryKey(name = "department_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manager_id")
    private long managerId;

    public Department() {
    }

    public Department(String name, long managerId) {
        this.name = name;
        this.managerId = managerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return this.getId() + " | " + this.getName() + " | " + this.getManagerId();
    }
}
