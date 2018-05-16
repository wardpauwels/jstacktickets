package be.jstack.ticketing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Company {

    @Id
    private String id;
    private String name;
    @DBRef
    private List<Project> projects;

    public void addProject(Project project) {
        if (projects == null) projects = new ArrayList<>();
        projects.add(project);
    }
}