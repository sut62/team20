package team20.transport.ParcelDeliverySystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberCustomerRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberLevelRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.PackageType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackageTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.SendingTypeRepository;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentTimeRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SentParcelTests {
    @Autowired
    PackagingRepository packagingRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    SentTimeRepository sentTimeRepository;
    @Autowired
    MemberCustomerRepository memberCustomerRepository;
    @Autowired
    MemberTypeRepository memberTypeRepository;
    @Autowired
    MemberLevelRepository memberLevelRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PackageTypeRepository packageTypeRepository;
    @Autowired
    SendingTypeRepository sendingTypeRepository;

    Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6004798_codeMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        MemberType mtype = new MemberType();
        mtype.setType("test");
        mtype = memberTypeRepository.saveAndFlush(mtype);
        MemberLevel mlevel = new MemberLevel();
        mlevel.setPermission("test");
        mlevel = memberLevelRepository.saveAndFlush(mlevel);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setEmail("test2542@gmail.com");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setMemberLevel(mlevel);
        memberCustomer.setMemberType(mtype);
        memberCustomer = memberCustomerRepository.saveAndFlush(memberCustomer);

        PackageType ptype = new PackageType();
        ptype.setType("test");
        ptype = packageTypeRepository.saveAndFlush(ptype);
        SendingType stype = new SendingType();
        stype.setType("test");
        stype.setUnit(1);
        stype = sendingTypeRepository.saveAndFlush(stype);

        Date check = new Date();
        Packaging packaging = new Packaging();
        packaging.setSentBy(memberCustomer);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging.setPackageDate(check);
        packaging.setCode(null);
        packaging.setPlace("test place");
        packaging.setReciever("123 reciever");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);

        Set<ConstraintViolation<Packaging>> result = validator.validate(packaging);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Packaging> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());
    }
}