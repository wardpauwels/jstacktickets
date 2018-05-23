package be.jstack.ticketing.data;


import be.jstack.ticketing.entity.Project;
import be.jstack.ticketing.entity.Ticket;

import java.util.List;

public interface TicketRepository extends TicketsRepository<Ticket, String> {

    List<Ticket> findAllByProject(Project project);

}
