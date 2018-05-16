package be.jstack.ticketing.service;

import be.jstack.ticketing.data.BugIssueRepository;
import be.jstack.ticketing.data.ImprovementRepository;
import be.jstack.ticketing.data.NewFeatureRepository;
import be.jstack.ticketing.entity.types.BugIssue;
import be.jstack.ticketing.entity.types.Improvement;
import be.jstack.ticketing.entity.types.NewFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private final BugIssueRepository bugIssueRepository;
    private final ImprovementRepository improvementRepository;
    private final NewFeatureRepository newFeatureRepository;

    @Autowired
    public TicketService(BugIssueRepository bugIssueRepository, ImprovementRepository improvementRepository, NewFeatureRepository newFeatureRepository) {
        this.bugIssueRepository = bugIssueRepository;
        this.improvementRepository = improvementRepository;
        this.newFeatureRepository = newFeatureRepository;
    }

    public List<BugIssue> findAllTickets(){
        return bugIssueRepository.findAll();
    }

    public void createNewBugIssue(BugIssue bugIssue) {
        bugIssue.setCreationDate(new Date());
        bugIssueRepository.save(bugIssue);
    }

    public void createNewImprovement(Improvement improvement) {
        improvementRepository.save(improvement);
    }

    public void createNewNewFeature(NewFeature newFeature) {
        newFeatureRepository.save(newFeature);
    }
}