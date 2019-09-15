package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private String name;

    private Category parentCategory;

    private Set<Category> subcategories;

    private Set<Contest> contests;

    @Column(nullable = false)
    @NotNull
    @Size(min = 4)
    @Pattern(regexp = "[A-Z].+")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Category.class)
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    @OneToMany(mappedBy = "parentCategory")
    public Set<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories = subcategories;
    }

    @OneToMany(targetEntity = Contest.class, mappedBy = "category")
    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }
}
