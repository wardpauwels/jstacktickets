package be.jstack.ticketing.entity;

import be.jstack.ticketing.exception.AlreadyContainsProjectException;
import be.jstack.ticketing.exception.AlreadyContainsUserException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
public class Company {

    @Id
    private String id;
    private String name;
    @DBRef
    private Map<String, Project> projects;
    @DBRef
    private Map<String, User> users;

    public void addProject(Project project) throws AlreadyContainsProjectException {
        if (projects == null) projects = new HashMap<>();
        if (!projects.containsKey(project.getId())) {
            projects.put(project.getId(), project);
        } else throw new AlreadyContainsProjectException(project.getId());
    }

    public void deleteProject(Project project) {
        if (projects != null && !projects.isEmpty()) {
            projects.remove(project.getId());
        }
    }

    public void addUser(User user) throws AlreadyContainsUserException {
        if (users == null) users = new HashMap<>();
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        } else throw new AlreadyContainsUserException(user.getId());
    }

    public void deleteUser(User user) {
        if (users != null && !users.isEmpty()) {
            users.remove(user.getId());
        }
    }
}