package pl.pjatk.mas.project.control.service;

import pl.pjatk.mas.project.boundary.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    List<EmployeeDTO> getEmployeesByTripId(Long tripId);
}
