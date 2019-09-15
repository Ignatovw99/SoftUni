package productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productsshop.domain.dto.CategoryProductsDto;
import productsshop.domain.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new productsshop.domain.dto.CategoryProductsDto(c.name, SIZE(c.products), AVG(p.price), SUM(p.price)) " +
            "FROM Category c " +
            "JOIN c.products p " +
            "GROUP BY c.id " +
            "ORDER BY SIZE(c.products) DESC")
    List<CategoryProductsDto> findAllByProductsCountAverageAndTotalPrice();
}
