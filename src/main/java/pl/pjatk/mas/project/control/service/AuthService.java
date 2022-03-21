package pl.pjatk.mas.project.control.service;

import pl.pjatk.mas.project.boundary.dto.*;
import pl.pjatk.mas.project.control.security.UserPrincipal;

public interface AuthService {
    UserDTO login(LoginDTO loginDTO);
    ClientDTO registerClient(RegisterDTO clientDto);
    AdminDTO registerAdmin(RegisterDTO adminDto);
    UserDTO getUserData(UserPrincipal currentUser);
}
