package be.jstack.ticketing.data;

import be.jstack.ticketing.entity.types.BugIssue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BugIssueRepository extends MongoRepository<BugIssue, String> {
}
