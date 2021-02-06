package com.learning.trackzilla.service;

import com.learning.trackzilla.entity.Ticket;
import com.learning.trackzilla.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> listTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(long id) {
        return ticketRepository.findById(id);
    }

}
