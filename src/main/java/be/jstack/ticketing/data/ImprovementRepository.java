package be.jstack.ticketing.data;

import be.jstack.ticketing.entity.types.Improvement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImprovementRepository extends MongoRepository<Improvement, String> {
}
