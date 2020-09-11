package jwt.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<String>> getAllCourses() {
        return ResponseEntity.ok(List.of("Docker", "Spring Advanced", "Data Structures"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<String>> getAllaDMINCourses() {
        return ResponseEntity.ok(List.of("Docker", "Spring Advanced", "Data Structures", "Course for admins"));
    }
}
