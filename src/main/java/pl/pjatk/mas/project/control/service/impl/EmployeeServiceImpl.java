package pl.pjatk.mas.project.control.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.pjatk.mas.project.boundary.dto.*;

import pl.pjatk.mas.project.control.dao.EmployeeDAO;
import pl.pjatk.mas.project.control.mapper.ProjectMapper;
import pl.pjatk.mas.project.control.service.EmployeeService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @NonNull EmployeeDAO employeeDAO;
    @NonNull ProjectMapper projectMapper;


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return projectMapper.employeeEntitiesToDtos(employeeDAO.findAll());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByTripId(Long tripId) {
        return projectMapper.employeeEntitiesToDtos(employeeDAO.findAll());
    }
}
