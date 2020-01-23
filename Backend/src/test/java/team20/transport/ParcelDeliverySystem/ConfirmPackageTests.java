package team20.transport.ParcelDeliverySystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberCustomerRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberLevelRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackageTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.SendingTypeRepository;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.Repository.StatusRepository;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.ConfirmPackage;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.ConfirmPackageRepository;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.SatisfactionLevelRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.PackageType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.SendingTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ConfirmPackageTests {
    @Autowired
    ConfirmPackageRepository confirmPackageRepository;
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
    @Autowired
    SatisfactionLevelRepository satisfactionLevelRepository;
    @Autowired
    MemberLevelRepository memberLevelRepository;
    @Autowired
    MemberTypeRepository memberTypeRepository;
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
    void b6012496_testCorrectDataInput() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(employee);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode("CPT2001234");
        confirmPackage.setName("C20");
        confirmPackage.setComment("Test");
        confirmPackage.setPackaging(packaging);
        confirmPackage.setSatisfactionLevel(satisfactionLevel);
        confirmPackage = confirmPackageRepository.saveAndFlush(confirmPackage);

        ConfirmPackage found = confirmPackageRepository.findById(confirmPackage.getId()).get();

        assertEquals(employee, found.getCreateBy());
        assertEquals(time, found.getConfirmDate());
        assertEquals("CPT2001234",found.getCode());
        assertEquals("C20",found.getName());
        assertEquals("Test",found.getComment());
        assertEquals(packaging, found.getPackaging());
        assertEquals(satisfactionLevel, found.getSatisfactionLevel());
    }


    @Test
    void b6012496_SatisfactionLevelMustNotBeNull() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(employee);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode("CPT2001234");
        confirmPackage.setName("C20");
        confirmPackage.setComment("Test");
        confirmPackage.setPackaging(packaging);
        confirmPackage.setSatisfactionLevel(null);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ConfirmPackage> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("satisfactionLevel", v.getPropertyPath().toString());
    }

    @Test
    void b6012496_patterncodeMustmatch() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(employee);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode("CPT20012345");
        confirmPackage.setName("C20");
        confirmPackage.setComment("Test");
        confirmPackage.setPackaging(packaging);
        confirmPackage.setSatisfactionLevel(satisfactionLevel);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ConfirmPackage> v = result.iterator().next();
        assertEquals("must match \"CPT20\\d{5}\"", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());
    }

    @Test
    void b6012496_notEmptyname() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(employee);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode("CPT2001234");
        confirmPackage.setName("");
        confirmPackage.setComment("Test");
        confirmPackage.setPackaging(packaging);
        confirmPackage.setSatisfactionLevel(satisfactionLevel);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ConfirmPackage> v = result.iterator().next();
        assertEquals("must not be empty", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void b6012496_createByMustNotBeNull() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(null);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode("CPT2001234");
        confirmPackage.setName("C20");
        confirmPackage.setComment("Test");
        confirmPackage.setPackaging(packaging);
        confirmPackage.setSatisfactionLevel(satisfactionLevel);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ConfirmPackage> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createBy", v.getPropertyPath().toString());
    }

    @Test
    void b6012496_PackagingMustNotBeNull() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(employee);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode("CPT2001234");
        confirmPackage.setName("C20");
        confirmPackage.setComment("Test");
        confirmPackage.setPackaging(null);
        confirmPackage.setSatisfactionLevel(satisfactionLevel);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ConfirmPackage> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("packaging", v.getPropertyPath().toString());
    }

    @Test
    void b6012496_CommentNotSize() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(employee);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode("CPT2001234");
        confirmPackage.setName("C20");
        confirmPackage.setComment("");
        confirmPackage.setPackaging(packaging);
        confirmPackage.setSatisfactionLevel(satisfactionLevel);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ConfirmPackage> v = result.iterator().next();
        assertEquals("size must be between 1 and 20", v.getMessage());
        assertEquals("comment", v.getPropertyPath().toString());
    }

    @Test
    void b6012496_CodeMustNotBeNull() {

        Employee employee = new Employee();
        employee.setName("B6012496");
        employee.setEmail("B6012496@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);

        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setPermission("Regular");
        memberLevel = memberLevelRepository.save(memberLevel);

        MemberType memberType = new MemberType();
        memberType.setType("Company");
        memberType = memberTypeRepository.save(memberType);

        MemberCustomer memberCustomer = new MemberCustomer();
        memberCustomer.setMemName("mem Test");
        memberCustomer.setTel("0987654321");
        memberCustomer.setCreateBy(employee);
        memberCustomer.setEmail("test@localhost");
        memberCustomer.setMemberType(memberType);
        memberCustomer.setMemberLevel(memberLevel);
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

        SatisfactionLevel satisfactionLevel = new SatisfactionLevel();
        satisfactionLevel.setSatisfactionlevel_name("test sat");
        satisfactionLevel = satisfactionLevelRepository.saveAndFlush(satisfactionLevel);

        Date time = new Date();
        ConfirmPackage confirmPackage = new ConfirmPackage();
        confirmPackage.setCreateBy(employee);
        confirmPackage.setConfirmDate(time);
        confirmPackage.setCode(null);
        confirmPackage.setName("C20");
        confirmPackage.setComment("Test");
        confirmPackage.setPackaging(packaging);
        confirmPackage.setSatisfactionLevel(satisfactionLevel);

        Set<ConstraintViolation<ConfirmPackage>> result = validator.validate(confirmPackage);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ConfirmPackage> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());
    }

}
