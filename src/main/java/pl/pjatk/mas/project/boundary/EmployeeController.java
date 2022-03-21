package pl.pjatk.mas.project.boundary;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.mas.project.boundary.dto.*;
import pl.pjatk.mas.project.control.security.CurrentUser;
import pl.pjatk.mas.project.control.security.UserPrincipal;
import pl.pjatk.mas.project.control.service.AuthService;
import pl.pjatk.mas.project.control.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/api/employee"})
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    @NonNull EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

//    @GetMapping("/user")
//    public ResponseEntity<? extends UserDTO> getUserData(@NonNull @CurrentUser UserPrincipal currentUser) {
//        return ResponseEntity.ok(authService.getUserData(currentUser));
//    }

}
