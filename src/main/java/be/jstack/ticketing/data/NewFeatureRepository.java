package be.jstack.ticketing.data;

import be.jstack.ticketing.entity.types.NewFeature;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewFeatureRepository extends MongoRepository<NewFeature, String> {
}
