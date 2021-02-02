package com.learning.trackzilla.controller;

import com.learning.trackzilla.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketsController.class)
public class TicketsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TicketService ticketService;

    @Test
    public void getAllApplicationsTest() throws Exception{
        mockMvc.perform(get("/v1/api/tickets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(ticketService, times(1)).listTickets();
    }
}
