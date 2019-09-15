package softuni.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.workshop.domain.entities.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByName(String projectName);

    List<Project> findAllByIsFinishedTrue();

    List<Project> findAllByNameEndingWith(String nameEndingPattern);
}
