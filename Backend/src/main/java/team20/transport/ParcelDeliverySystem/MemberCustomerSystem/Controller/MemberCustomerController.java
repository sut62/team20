package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.*;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class MemberCustomerController {
    @Autowired
    MemberCustomerRepository memberCustomerRepository;
    @Autowired
    MemberTypeRepository memberTypeRepository;
    @Autowired
    MemberLevelRepository memberLevelRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/MemberCustomer")
    public Collection<MemberCustomer> MemberCustomer() {
        return memberCustomerRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("addMemberCustomer")
    public MemberCustomer Register(@RequestBody Map<String,Long> allParams){
            Employee emp = employeeRepository.findById(allParams.get("employeeId")).get();
            MemberType mt = memberTypeRepository.findById(allParams.get("typeId")).get();
            MemberLevel ml = memberLevelRepository.findById(allParams.get("levelId")).get();
            
            newMemberCustomer.setMemName((allParams.get("mname")).get());
            newMemberCustomer.setTel((allParams.get("tel")).get());
            newMemberCustomer.setCreateBy(emp);
            newMemberCustomer.setMemberType(mt);
            newMemberCustomer.setMemberLevel(ml);
        return memberCustomerRepository.save(newMemberCustomer);
    }
}