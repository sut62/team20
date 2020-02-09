package team20.transport.ParcelDeliverySystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;
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
import team20.transport.ParcelDeliverySystem.Repository.StatusRepository;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Repository.ShippingStateRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
    @Autowired
    MemberLevelRepository memberLevelRepository;
    @Autowired
    MemberTypeRepository memberTypeRepository;
    @Autowired
    SendingTypeRepository sendingTypeRepository;
    @Autowired
    PackageTypeRepository packageTypeRepository;

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
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
    void b6003234_codeMustSHPFirst(){

        Employee employee = new Employee();
        employee.setName("B6003234");
        employee.setEmail("B6003234@g.sut.ac.th");
        employee = employeeRepository.saveAndFlush(employee);

        Station station = new Station();
        station.setName("test station");
        station = stationRepository.saveAndFlush(station);

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

        Status status = new Status();
        status.setName("test status");
        status = statusRepository.saveAndFlush(status);



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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("ABC%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
        shippingState.setOnStatus(status);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(packaging);

        Set<ConstraintViolation<ShippingState>> result = validator.validate(shippingState);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ShippingState> v = result.iterator().next();
        assertEquals("must match \"SHPT20\\d{5}_\\d{2}\"", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());

    }


    @Test
    void b6003234_codePosition123MustNotNumber(){

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("098%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
        shippingState.setOnStatus(status);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(packaging);

        Set<ConstraintViolation<ShippingState>> result = validator.validate(shippingState);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ShippingState> v = result.iterator().next();
        assertEquals("must match \"SHPT20\\d{5}_\\d{2}\"", v.getMessage());
        assertEquals("code", v.getPropertyPath().toString());

    }



    @Test
    void b6003234_codeMustNotDuplicate(){

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }
        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);


        ShippingState shippingState = new ShippingState();

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
        shippingState.setOnStatus(status);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(packaging);

         shippingStateRepository.saveAndFlush(shippingState);

        ShippingState dup_shippingState = new ShippingState();

        dup_shippingState.setCode(code);
        dup_shippingState.setTimestamp(new Date());
        dup_shippingState.setOnStatus(status);
        dup_shippingState.setAtStation(station);
        dup_shippingState.setCreateBy(employee);
        dup_shippingState.setOfPackage(packaging);


        try {
            shippingStateRepository.saveAndFlush(dup_shippingState);
        }
        catch (Exception e){

            // error message ตรงชนิด
            assertEquals("could not execute statement; SQL [n/a]; constraint [\"PUBLIC.UK6JH0LD8A7ATRD4ELOAT1NWM3F_INDEX_D ON PUBLIC.SHIPPINGSTATE(SHIPPINGSTATE_CODE) VALUES 1\"; SQL statement:\n" +
                    "insert into shippingstate (station_id, shippingstate_code, empolyee_id, packaging_id, status_id, shippingstate_timestamp, shippingstate_id) values (?, ?, ?, ?, ?, ?, ?) [23505-200]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",e.getMessage());
        }

    }


    @Test
    void b6003234_timeStampMustNotBeNull(){

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(null);
        
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
        assertEquals("timestamp", v.getPropertyPath().toString());

    }


    @Test
    void b6003234_createByMustNotBeNull(){

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
        shippingState.setOnStatus(status);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(null);
        shippingState.setOfPackage(packaging);

        Set<ConstraintViolation<ShippingState>> result = validator.validate(shippingState);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ShippingState> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createBy", v.getPropertyPath().toString());

    }

    @Test
    void b6003234_onStatusMustNotBeNull(){

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
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
    void b6003234_atStationMustNotBeNull(){

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
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

    @Test
    void b6003234_ofPackageMustNotBeNull(){

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


        int countShippingState = 0;
        if (packaging.getHaveShippingState() != null) {
            countShippingState = packaging.getHaveShippingState().size();
        }

        ShippingState shippingState = new ShippingState();

        // Generate code by package id
        String code = String.format("SHP%s_%02d" , packaging.getCode(),countShippingState+1);

        shippingState.setCode(code);
        shippingState.setTimestamp(new Date());
        
        shippingState.setOnStatus(status);
        shippingState.setAtStation(station);
        shippingState.setCreateBy(employee);
        shippingState.setOfPackage(null);

        Set<ConstraintViolation<ShippingState>> result = validator.validate(shippingState);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ShippingState> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ofPackage", v.getPropertyPath().toString());

    }



}