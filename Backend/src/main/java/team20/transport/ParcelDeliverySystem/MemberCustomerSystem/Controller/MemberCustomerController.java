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

    @PostMapping("/Register/{Mname}/{Tel}/{employee_id}/{type_id}/{level_id}")
    public MemberCustomer Register(MemberCustomer newMemberCustomer,
                                    @PathVariable String mname,
                                    @PathVariable Long tel,
                                    @PathVariable Long employee_id,
                                    @PathVariable Long type_id,
                                    @PathVariable Long level_id){

        Employee emp = employeeRepository.findById(employee_id).get();
        MemberType mt = memberTypeRepository.findById(type_id).get();
        MemberLevel ml = memberLevelRepository.findById(level_id).get();

        newMemberCustomer.setMemName(mname);
        newMemberCustomer.setTel(tel);
        newMemberCustomer.setCreateBy(emp);
        newMemberCustomer.setMemberType(mt);
        newMemberCustomer.setMemberLevel(ml);
        return memberCustomerRepository.save(newMemberCustomer);
    }
}