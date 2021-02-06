package com.learning.trackzilla.service;

import com.learning.trackzilla.entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<Application> listApplications();
    Optional<Application> findById(long id);
}


