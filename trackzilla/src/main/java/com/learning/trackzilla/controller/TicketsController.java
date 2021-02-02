package com.learning.trackzilla.controller;

import com.learning.trackzilla.entity.Ticket;
import com.learning.trackzilla.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("v1/api/tickets")
public class TicketsController {
    private TicketService ticketService;

    public TicketsController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets() {
        List<Ticket> tickets = ticketService.listTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") long id) {
        return ResponseEntity.of(ticketService.findById(id));
    }

    @PutMapping("/{id}:approve")
    public ResponseEntity<Ticket> approve(@PathVariable("id") long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Approve Ticket is not implemented");
    }
}
