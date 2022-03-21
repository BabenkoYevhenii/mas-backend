package pl.pjatk.mas.project.control.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pjatk.mas.project.boundary.dto.*;
import pl.pjatk.mas.project.control.dao.AdminDAO;
import pl.pjatk.mas.project.control.dao.ClientDAO;
import pl.pjatk.mas.project.control.dao.RoleDAO;
import pl.pjatk.mas.project.control.entity.AdminEntity;
import pl.pjatk.mas.project.control.entity.ClientEntity;
import pl.pjatk.mas.project.control.entity.RoleEntity;
import pl.pjatk.mas.project.control.entity.enums.Role;
import pl.pjatk.mas.project.control.mapper.ProjectMapper;
import pl.pjatk.mas.project.control.security.JwtTokenProvider;
import pl.pjatk.mas.project.control.security.UserPrincipal;
import pl.pjatk.mas.project.control.service.AuthService;
import pl.pjatk.mas.project.controller.exceptions.EntityNotFoundException;

import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @NonNull ClientDAO clientDao;
    @NonNull AdminDAO adminDao;
    @NonNull RoleDAO roleDao;
    @NonNull AuthenticationManager authenticationManager;
    @NonNull JwtTokenProvider tokenProvider;
    @NonNull
    private ProjectMapper mapper;
    @NonNull PasswordEncoder passwordEncoder;

    @Override
    public UserDTO login(LoginDTO loginDTO) {
        log.info("User credentials: {}", loginDTO);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        log.info("I'm here");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());

        log.info("Access token for user with credentials: {}, token: {}", authentication.getPrincipal(), jwt);

        UserDTO userDto = mapper.userDtoFromPrincipal((UserPrincipal) authentication.getPrincipal());
        userDto.setAccessToken(jwt);

        return userDto;
    }


    @Override
    public UserDTO getUserData(UserPrincipal currentUser) {
        String jwt = tokenProvider.generateToken(currentUser);
        UserDTO userDto = mapper.userDtoFromPrincipal(currentUser);
        userDto.setAccessToken(jwt);
        return userDto;
    }

    @Override
    public ClientDTO registerClient(RegisterDTO registerDto) {
        log.info("Register data: {}", registerDto);
        ClientEntity clientEntity = mapper.clientEntityFromDto(registerDto);
        clientEntity.getUser().setPassword(passwordEncoder.encode(clientEntity.getUser().getPassword()));

        RoleEntity clientRole = roleDao.findByName(Role.CLIENT)
                .orElseThrow(EntityNotFoundException::new);

        clientEntity.getUser().setRoles(Collections.singleton(clientRole));

        clientEntity = clientDao.save(clientEntity);

        String jwt = tokenProvider.generateToken(mapper.principalFromEntity(clientEntity.getUser()));

        ClientDTO candidateDto = mapper.clientDtoFromEntity(clientEntity);
        candidateDto.setAccessToken(jwt);

        return candidateDto;
    }

    @Override
    public AdminDTO registerAdmin(RegisterDTO registerDto) {
        log.info("Register data: {}", registerDto);
        AdminEntity adminEntity = mapper.adminEntityFromDto(registerDto);
        adminEntity.getUser().setPassword(passwordEncoder.encode(adminEntity.getUser().getPassword()));
        RoleEntity adminRole = roleDao.findByName(Role.ADMIN)
                .orElseThrow(EntityNotFoundException::new);

        adminEntity.getUser().setRoles(Collections.singleton(adminRole));

        adminEntity.setIdentifier(UUID.randomUUID().toString());

        adminEntity = adminDao.save(adminEntity);

        String jwt = tokenProvider.generateToken(mapper.principalFromEntity(adminEntity.getUser()));

        AdminDTO adminDto = mapper.adminDtoFromEntity(adminEntity);
        adminDto.setAccessToken(jwt);

        return adminDto;
    }
}
