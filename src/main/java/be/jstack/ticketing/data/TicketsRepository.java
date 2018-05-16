package be.jstack.ticketing.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
interface TicketsRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
}