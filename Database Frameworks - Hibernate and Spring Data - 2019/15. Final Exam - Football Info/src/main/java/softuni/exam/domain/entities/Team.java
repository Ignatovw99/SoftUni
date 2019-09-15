package softuni.exam.domain.entities;

import org.hibernate.validator.constraints.Length;
import softuni.exam.domain.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    private String name;

    private Picture picture;

    public Team() {
    }

    @Column(name = "name", nullable = false)
    @NotNull
    @Length(min = 3, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Picture.class)
    @JoinColumn(name = "picture_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
