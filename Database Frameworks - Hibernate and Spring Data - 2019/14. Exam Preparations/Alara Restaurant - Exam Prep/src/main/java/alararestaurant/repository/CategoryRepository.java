package alararestaurant.repository;

import alararestaurant.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String categoryName);

    @Query("SELECT c FROM Category c JOIN c.items i GROUP BY c.name ORDER BY COUNT (i) DESC , SUM(i.price) DESC")
    List<Category> findAllOrderByItemsCount();
}
