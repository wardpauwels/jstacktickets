package be.jstack.ticketing.data;

import be.jstack.ticketing.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
