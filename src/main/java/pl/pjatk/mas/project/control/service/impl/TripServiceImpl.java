package pl.pjatk.mas.project.control.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.pjatk.mas.project.boundary.dto.TripDTO;
import pl.pjatk.mas.project.control.dao.TripDAO;
import pl.pjatk.mas.project.control.entity.TripEntity;
import pl.pjatk.mas.project.control.mapper.ProjectMapper;
import pl.pjatk.mas.project.control.service.TripService;
import pl.pjatk.mas.project.controller.exceptions.EntityNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    @NonNull TripDAO tripDao;
    @NonNull ProjectMapper mapper;

    @Override
    public List<TripDTO> getAllTrips() {
        List<TripEntity> trips = tripDao.findAll();

        log.info("Trips: {}", trips);

        return trips.stream()
                .map(mapper::tripEntityToDto)
                .sorted(Comparator.comparing(TripDTO::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public TripDTO getTripById(Long eventId) {
        TripEntity tripEntity = tripDao.findById(eventId).orElseThrow(EntityNotFoundException::new);
        log.info("Event: {}", tripEntity);
        return mapper.tripEntityToDto(tripEntity);
    }
}
