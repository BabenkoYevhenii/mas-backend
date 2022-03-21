package pl.pjatk.mas.project.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pjatk.mas.project.control.dao.*;
import pl.pjatk.mas.project.control.entity.*;
import pl.pjatk.mas.project.control.entity.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DbSeeder {
    @NonNull PasswordEncoder passwordEncoder;

    @NonNull
    private RoleDAO roleDao;
    @NonNull UserDAO userDao;
    @NonNull
    private AdminDAO adminDao;
    @NonNull
    private ClientDAO clientDao;

    @NonNull
    private PromotionDAO promotionDao;
    @NonNull
    private PassengerDAO passengerDao;

    @NonNull
    private StopDAO stopDao;
    @NonNull
    private TripDAO tripDao;
    @NonNull
    private EmployeeDAO employeeDAO;

    @NonNull
    private OrderDAO orderDao;


    @Bean
    public void loadData() {
        var adminRole = roleDao.save(new RoleEntity(Role.ADMIN));
        var clientRole = roleDao.save(new RoleEntity(Role.CLIENT));

        UserEntity user = UserEntity.builder()
                .email("admin@mail.com")
                .name("Admin")
                .surname("Kowalski")
                .password(passwordEncoder.encode("pass"))
                .build();

        user.setRoles(Set.of(adminRole));

        AdminEntity admin = AdminEntity.builder()
                .identifier(UUID.randomUUID().toString())
                .build();

        user.setAdmin(admin);

        user = userDao.save(user);
        admin = user.getAdmin();

        /////////////////////////

        UserEntity user2 = UserEntity.builder()
                .email("client@mail.com")
                .name("Client")
                .surname("Kowalski")
                .password(passwordEncoder.encode("pass"))
                .build();

        user2.setRoles(Set.of(clientRole));

        ClientEntity client = ClientEntity.builder()
                .phone("+41")
                .address("My address")
                .build();

        user2.setClient(client);

        user2  = userDao.save(user2);
        client = user2.getClient();

        /////////////////////////

        PromotionEntity promotion = PromotionEntity.builder().discountPercent(30).type(PromotionType.STUDENT).build();

        client.addPromotion(promotion);

        client = clientDao.save(client);


        /////////////////////////


        PassengerEntity passenger = PassengerEntity.builder()
                .documentNumber("12345")
                .documentType(DocumentType.ID_CARD)
                .name("Vlad")
                .surname("Vladovich")
                .birthdate(LocalDate.of(1997, 2, 14))
                .client(client)
                .build();

        PassengerEntity passenger2 = PassengerEntity.builder()
                .documentNumber("098765")
                .documentType(DocumentType.DRIVER_LICENCE)
                .name("Another")
                .surname("Vladovich")
                .birthdate(LocalDate.of(2000, 11, 7))
                .client(client)
                .build();

        passengerDao.saveAll(List.of(passenger, passenger2));

        /////////////////////////

        StopEntity stop = stopDao.save(StopEntity.builder()
                .name("Localization 1")
                .city("Warszawa")
                .street("ul. Koszykowa")
                .buildingNumber("86")
                .build()
        );

        StopEntity stop2 = stopDao.save(StopEntity.builder()
                .name("Localization 2")
                .city("Poznan")
                .street("ul. Marszalkowska")
                .buildingNumber("136A")
                .build()
        );

        StopEntity stop3 = stopDao.save(StopEntity.builder()
                .name("Localization 3")
                .city("Krakow")
                .street("ul. Krakowska")
                .buildingNumber("123")
                .build()
        );

        StopEntity stop4 = stopDao.save(StopEntity.builder()
                .name("Localization 4")
                .city("Katowice")
                .street("ul. Katowicka")
                .buildingNumber("456")
                .build()
        );
        StopEntity stop5 = stopDao.save(StopEntity.builder()
                .name("Localization 6")
                .city("Kijow")
                .street("ul.Wokzalna")
                .buildingNumber("456")
                .build()
        );

        StopEntity stop6 = stopDao.save(StopEntity.builder()
                .name("Localization 5")
                .city("Warszawa")
                .street("ul. Koszykowa")
                .buildingNumber("98")
                .build()
        );
        /////////////////////////

        EmployeeEntity conductor = employeeDAO.save(EmployeeEntity.builder().name("Nadya Lupkin").type(EmployeeType.CONDUCTOR).build());
        EmployeeEntity conductor2 = employeeDAO.save(EmployeeEntity.builder().name("Vasya Pupkin").type(EmployeeType.CONDUCTOR).build());
        EmployeeEntity conductor3 = employeeDAO.save(EmployeeEntity.builder().name("Heh Pupkin").type(EmployeeType.CONDUCTOR).build());
        EmployeeEntity conductor4 = employeeDAO.save(EmployeeEntity.builder().name("Meh Pupkin").type(EmployeeType.CONDUCTOR).build());
        EmployeeEntity conductor5 = employeeDAO.save(EmployeeEntity.builder().name("Heh Lup").type(EmployeeType.CONDUCTOR).build());
        EmployeeEntity conductor6 = employeeDAO.save(EmployeeEntity.builder().name("Meh Pup").type(EmployeeType.CONDUCTOR).build());


        EmployeeEntity machinist = employeeDAO.save(EmployeeEntity.builder().name("Valera").type(EmployeeType.MACHINIST).build());
        EmployeeEntity machinist2 = employeeDAO.save(EmployeeEntity.builder().name("Petya").type(EmployeeType.MACHINIST).build());
        EmployeeEntity machinist3 = employeeDAO.save(EmployeeEntity.builder().name("Dyadya").type(EmployeeType.MACHINIST).build());
        EmployeeEntity machinist4 = employeeDAO.save(EmployeeEntity.builder().name("Zaal").type(EmployeeType.MACHINIST).build());
        EmployeeEntity machinist5 = employeeDAO.save(EmployeeEntity.builder().name("Dima").type(EmployeeType.MACHINIST).build());
        /////////////////////////

        TrainEntity train = TrainEntity.builder().manufacturer("manufacturer").power("1234").serialNumber("12312412AFA").maxCarriageCount(32).build();

        CarriageEntity carriageEntity = CarriageEntity.builder().manufacturer("manufacturer 2").serialNumber("65467ADASD").numberOfSeats(30).type(CarriageType.REGULAR).build();
        CarriageEntity carriageEntity2 = CarriageEntity.builder().manufacturer("manufacturer 2").serialNumber("123123ASDA").numberOfSeats(10).type(CarriageType.FIRST_CLASS).build();

        train.addCarriage(carriageEntity);
        train.addCarriage(carriageEntity2);

        TripEntity trip = tripDao.save(TripEntity.builder().name("Warsaw - Poznan").type(TripType.INTRERCITY)
                .dateTime(LocalDateTime.of(2020, 7, 18, 15, 0))
                .placeCount(10000).price(100.).train(train).build());

        log.info("Trip data: {}", trip);

        trip.addEmployee(machinist3, 190);
        trip.addEmployee(conductor, 60);
        trip.addEmployee(conductor3, 90);


        trip.addStop(stop);
        trip.addStop(stop2);

        log.info("Trip data: {}", trip);

        trip = tripDao.save(trip);

        log.info("Trip data: {}", trip);

        /////////////////////////

        TrainEntity train2 = TrainEntity.builder().manufacturer("manufacturer 2").power("4321").serialNumber("JKE12314FA").maxCarriageCount(25).build();

        CarriageEntity carriageEntity3 = CarriageEntity.builder().manufacturer("manufacturer 1").serialNumber("GKDASO12").numberOfSeats(25).type(CarriageType.REGULAR).build();
        CarriageEntity carriageEntity4 = CarriageEntity.builder().manufacturer("manufacturer 1").serialNumber("ASDK231").numberOfSeats(15).type(CarriageType.FIRST_CLASS).build();

        train2.addCarriage(carriageEntity3);
        train2.addCarriage(carriageEntity4);

        TripEntity trip2 = tripDao.save(TripEntity.builder().name("Katowice - Krakow ").type(TripType.POLREGIO)
                .dateTime(LocalDateTime.of(2020, 5, 9, 18, 30))
                .placeCount(200).price(200.).train(train2).build());

        log.info("Trip data: {}", trip2);

        trip2.addEmployee(machinist, 120);
        trip2.addEmployee(machinist2, 60);
        trip2.addEmployee(conductor2, 90);
        trip2.addEmployee(conductor3, 90);
        trip2.addEmployee(conductor4, 90);
        trip2.addStop(stop3);
        trip2.addStop(stop4);

        log.info("Trip data: {}", trip2);

        trip2 = tripDao.save(trip2);

        log.info("Trip data: {}", trip2);





        /////////////////////////////

        TrainEntity train3 = TrainEntity.builder().manufacturer("manufacturer 2").power("4321").serialNumber("JKE12377814FA").maxCarriageCount(25).build();

        CarriageEntity carriageEntity5 = CarriageEntity.builder().manufacturer("manufacturer 1").serialNumber("GKqqDASO12").numberOfSeats(25).type(CarriageType.REGULAR).build();
        CarriageEntity carriageEntity6 = CarriageEntity.builder().manufacturer("manufacturer 1").serialNumber("ASDKer231").numberOfSeats(15).type(CarriageType.FIRST_CLASS).build();

        train3.addCarriage(carriageEntity5);
        train3.addCarriage(carriageEntity6);


        TripEntity trip3 = tripDao.save(TripEntity.builder().name("Warsaw - Kijów ").type(TripType.POLREGIO)
                .dateTime(LocalDateTime.of(2020, 5, 9, 18, 30))
                .placeCount(200).price(200.).train(train3).build());

        log.info("Trip data: {}", trip2);

        trip3.addEmployee(machinist4, 120);
        trip3.addEmployee(machinist5, 60);
        trip3.addEmployee(conductor5, 90);
        trip3.addEmployee(conductor6, 90);

        trip3.addStop(stop5);
        trip3.addStop(stop6);


        log.info("Trip data: {}", trip3);

        trip3 = tripDao.save(trip3);

        log.info("Trip data: {}", trip3);


        /////////////////////////





        TicketEntity ticket = TicketEntity.builder().passenger(passenger).trip(trip).placeNumber("1A").type(TicketType.REGULAR).price(100.).build();
        TicketEntity ticket2 = TicketEntity.builder().passenger(passenger2).trip(trip).placeNumber("2A").type(TicketType.REGULAR).price(100.).build();
        TicketEntity ticket3 = TicketEntity.builder().passenger(passenger).trip(trip2).placeNumber("3A").type(TicketType.FIRST_CLASS).price(200.).build();

        /////////////////////////

        OrderEntity order = OrderEntity.builder().status(OrderStatus.PAID).client(client).totalPrice(170).build();
        order.addTicket(ticket);
        order.addTicket(ticket2);

        log.info("Order data: {}", order);
        orderDao.save(order);
        log.info("Order data: {}", order);

        /////////////////////////

        OrderEntity order2 = OrderEntity.builder().status(OrderStatus.PAID).client(client).totalPrice(200).build();
        order2.addTicket(ticket3);
        log.info("Order data: {}", order2);
        orderDao.save(order2);
        log.info("Order data: {}", order2);
    }
}
