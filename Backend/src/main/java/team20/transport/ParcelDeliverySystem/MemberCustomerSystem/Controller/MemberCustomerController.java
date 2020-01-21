package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.*;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import net.minidev.json.JSONObject;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Date;

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

    @GetMapping("/FindMemberCustomerId/{id}")
    public JSONObject MemberCustomer(@PathVariable Long id) {
        MemberCustomer x = memberCustomerRepository.findById(id).get();
        JSONObject y = new JSONObject();
        y.put("id",x.getId());
        y.put("name",x.getMemName());
        y.put("tel",x.getTel());
        return y;
    }

    @PostMapping("/addMemberCustomer")
    public MemberCustomer Register(@RequestBody Map<String,String> allParams){
            MemberCustomer newMemberCustomer = new MemberCustomer();
            
            Employee emp = employeeRepository.findById(Long.valueOf(allParams.get("employeeId"))).get();
            MemberType mt = memberTypeRepository.findById(Long.valueOf(allParams.get("typeId"))).get();
            MemberLevel ml = memberLevelRepository.findById(Long.valueOf(allParams.get("levelId"))).get();
            

            newMemberCustomer.setMemName((allParams.get("mname")));
            newMemberCustomer.setTel((allParams.get("tel")));
            newMemberCustomer.setEmail((allParams.get("email")));
            newMemberCustomer.setCreateBy(emp);
            newMemberCustomer.setMemberType(mt);
            newMemberCustomer.setMemberLevel(ml);
        return memberCustomerRepository.save(newMemberCustomer);
    }
}