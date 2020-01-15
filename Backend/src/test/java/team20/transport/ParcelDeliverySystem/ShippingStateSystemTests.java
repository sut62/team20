package team20.transport.ParcelDeliverySystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.*;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.*;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.Repository.StatusRepository;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Repository.ShippingStateRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ShippingStateSystemTests {
    @Autowired
    ShippingStateRepository shippingStateRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    PackagingRepository packagingRepository;
    @Autowired
    MemberCustomerRepository memberCustomerRepository;

    Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void b6003234_testCorrectDataInput(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer = memberCustomerRepository.saveAndFlush(memberCustomer);

            

        Packaging packaging = new Packaging();
        packaging.setCode("T2001234");
        packaging.setSentBy(memberCustomer);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging = packagingRepository.saveAndFlush(packaging);


        ShippingState shippingState = new ShippingState();

        String code = "SHP" + packaging.getCode();
        
        shippingState.setCode(code);
        shippingState.setTimestamp(LocalDateTime.now());
        shippingState.setOnStatus(status);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(packaging);

        shippingState = shippingStateRepository.saveAndFlush(shippingState);

        ShippingState found = shippingStateRepository.findById(shippingState.getId()).get();

        assertEquals(shippingState.getTimestamp(),found.getTimestamp());
        assertEquals(code,found.getCode());
        assertEquals(status,found.getOnStatus());
        assertEquals(station,found.getAtStation());
        assertEquals(employee,found.getCreateBy());
        assertEquals(packaging,found.getOfPackage());

    }

    


    @Test
    void b6003234_codeMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer = memberCustomerRepository.saveAndFlush(memberCustomer);

            

        Packaging packaging = new Packaging();
        packaging.setCode("T2001234");
        packaging.setSentBy(memberCustomer);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging = packagingRepository.saveAndFlush(packaging);


        ShippingState shippingState = new ShippingState();

        String code = "SHP" + packaging.getCode();
        
        shippingState.setCode(code);
        shippingState.setTimestamp(LocalDateTime.now());
        shippingState.setCode(null);
        shippingState.setOnStatus(status);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(packaging);

        Set<ConstraintViolation<ShippingState>> result = validator.validate(shippingState);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ShippingState> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());

    }

    @Test
    void b6003234_statusMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer = memberCustomerRepository.saveAndFlush(memberCustomer);

            

        Packaging packaging = new Packaging();
        packaging.setCode("T2001234");
        packaging.setSentBy(memberCustomer);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging = packagingRepository.saveAndFlush(packaging);


        ShippingState shippingState = new ShippingState();

        String code = "SHP" + packaging.getCode();
        
        shippingState.setCode(code);
        shippingState.setTimestamp(LocalDateTime.now());
        shippingState.setOnStatus(null);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(packaging);

        Set<ConstraintViolation<ShippingState>> result = validator.validate(shippingState);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ShippingState> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("onStatus", v.getPropertyPath().toString());

    }

    @Test
    void b6003234_stationMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);


        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer = memberCustomerRepository.saveAndFlush(memberCustomer);

            

        Packaging packaging = new Packaging();
        packaging.setCode("T2001234");
        packaging.setSentBy(memberCustomer);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging = packagingRepository.saveAndFlush(packaging);


        ShippingState shippingState = new ShippingState();

        String code = "SHP" + packaging.getCode();
        
        shippingState.setCode(code);
        shippingState.setTimestamp(LocalDateTime.now());
        shippingState.setOnStatus(status);
        shippingState.setAtStation(null);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(packaging);

        Set<ConstraintViolation<ShippingState>> result = validator.validate(shippingState);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ShippingState> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("atStation", v.getPropertyPath().toString());

    }


}