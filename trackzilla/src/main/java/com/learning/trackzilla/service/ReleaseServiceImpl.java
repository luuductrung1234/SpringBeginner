package com.learning.trackzilla.service;

import com.learning.trackzilla.entity.Release;
import com.learning.trackzilla.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReleaseServiceImpl implements ReleaseService {
    private final ReleaseRepository releaseRepository;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    public List<Release> listReleases() {
        return (List<Release>) releaseRepository.findAll();
    }

    @Override
    public Optional<Release> findById(long id) {
        return releaseRepository.findById(id);
    }

}
