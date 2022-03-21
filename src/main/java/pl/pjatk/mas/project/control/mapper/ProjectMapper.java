package pl.pjatk.mas.project.control.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import pl.pjatk.mas.project.boundary.dto.*;
import pl.pjatk.mas.project.control.entity.*;
import pl.pjatk.mas.project.control.security.UserPrincipal;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProjectMapper {
    LocalDateTime map(Instant value) {
        return LocalDateTime.ofInstant(value, ZoneOffset.UTC);
    }

    @Mappings({
            @Mapping(source = "name", target = "user.name"),
            @Mapping(source = "surname", target = "user.surname"),
            @Mapping(source = "email", target = "user.email"),
    })
    public abstract ClientEntity clientEntityFromDto(ClientDTO dto);

    @Mappings({
            @Mapping(source = "name", target = "user.name"),
            @Mapping(source = "surname", target = "user.surname"),
            @Mapping(source = "email", target = "user.email"),
    })
    public abstract ClientEntity clientEntityFromDto(RegisterDTO dto);

    @Mappings({
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "surname", source = "user.surname"),
            @Mapping(target = "email", source = "user.email"),
    })
    public abstract ClientDTO clientDtoFromEntity(ClientEntity dto);

    @Mappings({
            @Mapping(source = "name", target = "user.name"),
            @Mapping(source = "surname", target = "user.surname"),
            @Mapping(source = "email", target = "user.email"),
    })
    public abstract AdminEntity adminEntityFromDto(AdminDTO dto);

    @Mappings({
            @Mapping(source = "name", target = "user.name"),
            @Mapping(source = "surname", target = "user.surname"),
            @Mapping(source = "email", target = "user.email"),
    })
    public abstract AdminEntity adminEntityFromDto(RegisterDTO dto);

    @Mappings({
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "surname", source = "user.surname"),
            @Mapping(target = "email", source = "user.email"),
    })
    public abstract AdminDTO adminDtoFromEntity(AdminEntity dto);

    public abstract UserDTO userDtoFromPrincipal(UserPrincipal entity);

    public abstract UserPrincipal principalFromEntity(UserEntity entity);

    public abstract UserDTO userDtoFromEntity(UserEntity entity);

    public abstract TripDTO tripEntityToDto(TripEntity entity);

    public abstract StopDTO stopEntityToDto(StopEntity entity);

    public abstract TrainDTO trainEntityToDto(TrainEntity entity);

    public abstract CarriageDTO carriageEntityToDto(CarriageEntity entity);

    public abstract EmployeeDTO employeeEntityToDto(EmployeeEntity entity);

    @Mappings({
            @Mapping(target = "id", source = "employee.id"),
            @Mapping(target = "name", source = "employee.name"),
            @Mapping(target = "type", source = "employee.type")
    })
    public abstract EmployeeDTO employeeEntityToDto(EmployeeTripEntity entity);

    public abstract List<EmployeeDTO> employeeEntitiesToDtos(List<EmployeeEntity> employeeEntities);

    @Mapping(source = "createdAt", target = "createdAt")
    public abstract OrderDTO orderEntityToDto(OrderEntity entity);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "tickets", ignore = true),
    })
    public abstract OrderEntity orderDtoToEntity(OrderDTO dto);

    @Mappings({
            @Mapping(source = "trip.name", target = "tripName"),
            @Mapping(source = "trip.id", target = "tripId"),
            @Mapping(source = "trip.dateTime", target = "tripDate")
    })
    public abstract TicketDTO ticketEntityToDto(TicketEntity entity);

    public abstract TicketEntity ticketDtoToEntity(TicketDTO entity);

    public abstract PassengerDTO passengerEntityToDto(PassengerEntity entity);

    public abstract PassengerEntity passengerDtoToEntity(PassengerDTO entity);
}
