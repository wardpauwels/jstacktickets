package be.jstack.ticketing.data;

import be.jstack.ticketing.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
interface TicketsRepository<T extends Ticket, ID extends Serializable> extends MongoRepository<T, ID> {
}