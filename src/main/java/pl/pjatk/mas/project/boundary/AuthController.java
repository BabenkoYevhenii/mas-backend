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

import javax.validation.Valid;

@RestController
@RequestMapping({"/api/auth"})
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    @NonNull AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<? extends UserDTO> login(@Valid @RequestBody LoginDTO loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @GetMapping("/user")
    public ResponseEntity<? extends UserDTO> getUserData(@NonNull @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(authService.getUserData(currentUser));
    }

    @PostMapping("/register/client")
    public ResponseEntity<ClientDTO> registerClient(@Valid @RequestBody RegisterDTO registerDto) {
        return ResponseEntity.ok(authService.registerClient(registerDto));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AdminDTO> registerAdmin(@Valid @RequestBody RegisterDTO registerDto) {
        return ResponseEntity.ok(authService.registerAdmin(registerDto));
    }
}
