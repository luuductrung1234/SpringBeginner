package com.learning.conference.conferencefirstbootapp.controllers;

import com.learning.conference.conferencefirstbootapp.models.Session;
import com.learning.conference.conferencefirstbootapp.repositories.SessionRepository;
import com.learning.conference.conferencefirstbootapp.views.FindSessionParameters;
import com.sun.istack.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    private final SessionRepository sessionRepository;

    public SessionsController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<Session>> list(FindSessionParameters parameters) {

        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withIgnorePaths("id")
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Session> example = Example.of(
                Session.from(parameters.getSearchText(), parameters.getSearchText(), parameters.getLength()),
                customExampleMatcher);

        var sessions = sessionRepository.findAll(example, parameters.toPageRequest());
        return ResponseEntity.ok(sessions);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Session> get(@PathVariable Long id) {
        return Optional.of(sessionRepository.getOne(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Session> create(@RequestBody final Session session) {
        if (!createRequestValidation(session)) {
            return ResponseEntity.badRequest().body(session);
        }

        return ResponseEntity.ok(sessionRepository.saveAndFlush(session));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        if (!sessionRepository.existsSessionById(id)) {
            return ResponseEntity.notFound().build();
        }

        sessionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Session> update(@PathVariable Long id, @RequestBody Session session) {
        session.setId(id);

        if (!updateRequestValidation(session)) {
            return ResponseEntity.badRequest().body(session);
        }

        if (!sessionRepository.existsSessionById(session.getId())) {
            return ResponseEntity.notFound().build();
        }

        var sessionInDb = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, sessionInDb, "id");
        return ResponseEntity.ok(sessionRepository.saveAndFlush(sessionInDb));
    }

    private boolean createRequestValidation(@NotNull Session session) {
        if (session.getName() == "" || session.getLength() <= 0)
            return false;
        return true;
    }

    private boolean updateRequestValidation(@NotNull Session session) {
        if (session.getId() <= 0 || session.getName() == "" || session.getLength() <= 0)
            return false;
        return true;
    }
}
