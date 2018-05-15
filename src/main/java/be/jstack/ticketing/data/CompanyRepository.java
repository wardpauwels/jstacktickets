package be.jstack.ticketing.data;

import be.jstack.ticketing.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findByName(String name);
}
