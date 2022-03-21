package pl.pjatk.mas.project.control.service;

import pl.pjatk.mas.project.boundary.dto.TripDTO;

import java.util.List;

public interface TripService {
    List<TripDTO> getAllTrips();

    TripDTO getTripById(Long eventId);
}
