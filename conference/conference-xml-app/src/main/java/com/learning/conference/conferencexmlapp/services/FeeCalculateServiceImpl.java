package com.learning.conference.conferencexmlapp.services;

import com.learning.conference.conferencexmlapp.repository.FeeScheduleRepository;

public class FeeCalculateServiceImpl implements FeeCalculateService {
    private final FeeScheduleRepository feeScheduleRepository;

    public FeeCalculateServiceImpl(FeeScheduleRepository feeScheduleRepository) {
        this.feeScheduleRepository = feeScheduleRepository;
    }

    @Override
    public double calculateFee() {
        return feeScheduleRepository.getAll().get(0);
    }
}
