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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PackagingTests {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    PackagingRepository packagingRepository;
    @Autowired
    MemberCustomerRepository memberCustomerRepository;
    @Autowired
    PackageTypeRepository packageTypeRepository;
    @Autowired
    SendingTypeRepository sendingTypeRepository;
    @Autowired
    MemberTypeRepository memberTypeRepository;
    @Autowired
    MemberLevelRepository memberLevelRepository;

    Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void b6009649_testCorrectDataInput(){

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
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("123 reciever");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);
        packaging = packagingRepository.saveAndFlush(packaging);

        Packaging found = packagingRepository.findById(packaging.getId()).get();

        assertEquals(memberCustomer,found.getSentBy());
        assertEquals(station,found.getAtStation());
        assertEquals(employee,found.getCreateBy());
        assertEquals(check,found.getPackageDate());
        assertEquals("T2012345",found.getCode());
        assertEquals("test place",found.getPlace());
        assertEquals("123 reciever",found.getReciever());
        assertEquals(ptype,found.getPackageType());
        assertEquals(stype,found.getSendingType());
    }

    @Test
    void b6009649_testCodeMustNotDuplicate(){

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
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("123 reciever");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);
        packagingRepository.saveAndFlush(packaging);

        Packaging packagingDup = new Packaging();
        packagingDup.setSentBy(memberCustomer);
        packagingDup.setAtStation(station);
        packagingDup.setCreateBy(employee);
        packagingDup.setPackageDate(check);
        packagingDup.setCode("T2012345");
        packagingDup.setPlace("test place");
        packagingDup.setReciever("123 reciever");
        packagingDup.setVolume(10L);
        packagingDup.setWeight(10L);
        packagingDup.setPackageType(ptype);
        packagingDup.setSendingType(stype);
        try{
            packagingRepository.saveAndFlush(packagingDup);
        }catch(Exception e){
            String[] s = e.getMessage().split(";");
            assertEquals("could not execute statement",s[0]);
        }
    }

    @Test
    void b6009649_testCodeMustNotBeNull(){

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
    @Test
    void b6009649_testCodeMustNotBeStartWithA(){

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
        packaging.setCode("A2012345");
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
        assertEquals("must match \"T20\\d{5}\"", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());
    }
    @Test
    void b6009649_testCodeMustNoLenght9(){

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
        packaging.setCode("T20123456");
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
        assertEquals("must match \"T20\\d{5}\"", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testReceiverMustNotBeEmpty(){

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
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);

        Set<ConstraintViolation<Packaging>> result = validator.validate(packaging);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Packaging> v = result.iterator().next();
        assertEquals("must not be empty", v.getMessage());
        assertEquals("reciever", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testPlaceMustNotBeNull(){

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
        packaging.setCode("T2012345");
        packaging.setPlace(null);
        packaging.setReciever("test receiver");
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
        assertEquals("place", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testWeightMustNotBeNull(){

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
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
        packaging.setVolume(10L);
        packaging.setWeight(null);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);

        Set<ConstraintViolation<Packaging>> result = validator.validate(packaging);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Packaging> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("weight", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testVolumeMustNotBeNull(){

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
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
        packaging.setVolume(null);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);

        Set<ConstraintViolation<Packaging>> result = validator.validate(packaging);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Packaging> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("volume", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testSentByMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        PackageType ptype = new PackageType();
        ptype.setType("test");
        ptype = packageTypeRepository.saveAndFlush(ptype);
        SendingType stype = new SendingType();
        stype.setType("test");
        stype.setUnit(1);
        stype = sendingTypeRepository.saveAndFlush(stype);

        Date check = new Date();
        Packaging packaging = new Packaging();
        packaging.setSentBy(null);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging.setPackageDate(check);
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
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
        assertEquals("sentBy", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testAtStationMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

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
        packaging.setAtStation(null);
        packaging.setCreateBy(employee);
        packaging.setPackageDate(check);
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
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
        assertEquals("atStation", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testCreateByMustNotBeNull(){

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
        packaging.setCreateBy(null);
        packaging.setPackageDate(check);
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
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
        assertEquals("createBy", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testPackageDateMustNotBeNull(){

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

        Packaging packaging = new Packaging();
        packaging.setSentBy(memberCustomer);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging.setPackageDate(null);
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
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
        assertEquals("packageDate", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testPackageTypeMustNotBeNull(){

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
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(null);
        packaging.setSendingType(stype);

        Set<ConstraintViolation<Packaging>> result = validator.validate(packaging);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Packaging> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("packageType", v.getPropertyPath().toString());
    }

    @Test
    void b6009649_testSendingTypeMustNotBeNull(){

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

        Date check = new Date();
        Packaging packaging = new Packaging();
        packaging.setSentBy(memberCustomer);
        packaging.setAtStation(station);
        packaging.setCreateBy(employee);
        packaging.setPackageDate(check);
        packaging.setCode("T2012345");
        packaging.setPlace("test place");
        packaging.setReciever("test receiver");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(null);

        Set<ConstraintViolation<Packaging>> result = validator.validate(packaging);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Packaging> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sendingType", v.getPropertyPath().toString());
    }
}