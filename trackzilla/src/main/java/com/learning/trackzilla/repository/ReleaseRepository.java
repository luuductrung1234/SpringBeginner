package com.learning.trackzilla.repository;

import com.learning.trackzilla.entity.Release;
import org.springframework.data.repository.CrudRepository;

public interface ReleaseRepository extends CrudRepository<Release, Long> {
}
