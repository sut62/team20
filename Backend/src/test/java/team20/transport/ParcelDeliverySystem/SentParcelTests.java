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
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentTime;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentParcelRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentTimeRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Time;
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
    @Autowired
    SentParcelRepository sentParcelRepository;

    Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6004798_codeMustNotBeNull(){

        Employee employee = new Employee();
        employee.setName("B6004798");
        employee.setEmail("B6004798@g.sut.ac.th");
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
        memberCustomer.setTel("0999999999");
        memberCustomer.setEmail("test2541@gmail.com");
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
        packaging.setCode("T2001234");
        packaging.setPlace("test place");
        packaging.setReciever("123 reciever");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);
        packaging = packagingRepository.saveAndFlush(packaging);

        SentTime sentTime = new SentTime();
        sentTime.setFTime(null);
        sentTime.setLTime(new Time(1556175797428L));


        Set<ConstraintViolation<SentTime>> result = validator.validate(sentTime);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<SentTime> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("fTime", v.getPropertyPath().toString());
    }



    @Test
    void b6004798_testCorrectDataInput(){

        Employee employee = new Employee();
        employee.setName("B6004798");
        employee.setEmail("B6004798@g.sut.ac.th");
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
        memberCustomer.setTel("0999999999");
        memberCustomer.setEmail("test2541@gmail.com");
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

        SentTime sentTime = new SentTime();
        sentTime.setFTime(new Time(1256175797428L));
        sentTime.setLTime(new Time(1556175797428L));
        sentTime = sentTimeRepository.saveAndFlush(sentTime);

        SentParcel sentParcel = new SentParcel();
        sentParcel.setCode("SN01234");
        sentParcel.setSenttime(sentTime);
        sentParcel.setAtArriveStation(station);
        sentParcel.setAtOriginStation(station);
        sentParcel.setPackaging(packaging);
        sentParcel.setIsActive(true);
        sentParcel = sentParcelRepository.saveAndFlush(sentParcel);


        SentTime found = sentTimeRepository.findById(sentTime.getId()).get();

        assertEquals(new Time(1256175797428L),found.getFTime());
        assertEquals(new Time(1556175797428L),found.getLTime());
//        assertEquals(memberCustomer,found.getSentBy());
//        assertEquals(memberCustomer,found.getSentBy());
//        assertEquals(station,found.getAtStation());
//        assertEquals(employee,found.getCreateBy());
//        assertEquals(check,found.getPackageDate());
//        assertEquals("T2012345",found.getCode());
//        assertEquals("test place",found.getPlace());
//        assertEquals("123 reciever",found.getReciever());
//        assertEquals(ptype,found.getPackageType());
//        assertEquals(stype,found.getSendingType());
    }




    @Test
    void b6004798_codePattern(){

        Employee employee = new Employee();
        employee.setName("B6004798");
        employee.setEmail("B6004798@g.sut.ac.th");
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
        memberCustomer.setTel("0999999999");
        memberCustomer.setEmail("test2541@gmail.com");
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
        packaging.setCode("T2001234");
        packaging.setPlace("test place");
        packaging.setReciever("123 reciever");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);
        packaging = packagingRepository.saveAndFlush(packaging);

        SentTime sentTime = new SentTime();
        sentTime.setFTime(new Time(1256175797428L));
        sentTime.setLTime(new Time(1556175797428L));
        sentTime = sentTimeRepository.saveAndFlush(sentTime);

        SentParcel sentParcel = new SentParcel();
        sentParcel.setCode(null);
        sentParcel.setSenttime(sentTime);
        sentParcel.setAtArriveStation(station);
        sentParcel.setPackaging(packaging);
        sentParcel.setAtOriginStation(station);
        sentParcel.setIsActive(true);

        Set<ConstraintViolation<SentParcel>> result = validator.validate(sentParcel);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<SentParcel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());
    }


    @Test
    void b6004798_isActiveTrue(){

        Employee employee = new Employee();
        employee.setName("B6004798");
        employee.setEmail("B6004798@g.sut.ac.th");
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
        memberCustomer.setTel("0999999999");
        memberCustomer.setEmail("test2541@gmail.com");
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
        packaging.setCode("T2001234");
        packaging.setPlace("test place");
        packaging.setReciever("123 reciever");
        packaging.setVolume(10L);
        packaging.setWeight(10L);
        packaging.setPackageType(ptype);
        packaging.setSendingType(stype);
        packaging = packagingRepository.saveAndFlush(packaging);

        SentTime sentTime = new SentTime();
        sentTime.setFTime(new Time(1256175797428L));
        sentTime.setLTime(new Time(1556175797428L));
        sentTime = sentTimeRepository.saveAndFlush(sentTime);

        SentParcel sentParcel = new SentParcel();
        sentParcel.setCode("SN01234");
        sentParcel.setSenttime(sentTime);
        sentParcel.setAtArriveStation(station);
        sentParcel.setPackaging(packaging);
        sentParcel.setAtOriginStation(station);
        sentParcel.setIsActive(false);

        Set<ConstraintViolation<SentParcel>> result = validator.validate(sentParcel);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<SentParcel> v = result.iterator().next();
        assertEquals("must be true", v.getMessage());
        assertEquals("isActive", v.getPropertyPath().toString());
    }
}