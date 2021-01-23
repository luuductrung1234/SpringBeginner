package com.learning.conference.conferenceapp.services;

import com.learning.conference.conferenceapp.repository.FeeScheduleRepository;
import org.springframework.stereotype.Service;

@Service("feeCalculateService")
public class FeeCalculateServiceImpl implements FeeCalculateService {
    // no need @Autowired
    private final FeeScheduleRepository feeScheduleRepository;

    public FeeCalculateServiceImpl(FeeScheduleRepository feeScheduleRepository) {
        this.feeScheduleRepository = feeScheduleRepository;
    }

    @Override
    public double calculateFee() {
        return feeScheduleRepository.getAll().get(0);
    }
}
