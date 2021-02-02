package com.learning.trackzilla.service;

import com.learning.trackzilla.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> listTickets();

    Optional<Ticket> findById(long id);
}


