package pl.pjatk.mas.project.boundary;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.mas.project.boundary.dto.TripDTO;
import pl.pjatk.mas.project.control.service.TripService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/api/trips"})
@RequiredArgsConstructor
@Slf4j
public class TripController {
    @NonNull TripService tripService;

    @GetMapping
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDTO> getTripById(@Valid @PathVariable Long tripId) {
        return ResponseEntity.ok(tripService.getTripById(tripId));
    }
}
