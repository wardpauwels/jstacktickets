package be.jstack.ticketing.data;

import be.jstack.ticketing.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
