package com.learning.trackzilla.service;

import com.learning.trackzilla.entity.Release;

import java.util.List;
import java.util.Optional;

public interface ReleaseService {
    List<Release> listReleases();
    Optional<Release> findById(long id);
}


