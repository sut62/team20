package team20.transport.ParcelDeliverySystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/getEmployees")
    public Collection<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/chkLogin")
    public Employee chkLogin(@RequestBody Map<String,String> allParams){
        return employeeRepository.findByIdAndEmail(Long.valueOf(allParams.get("employeeId")),allParams.get("email"));
    }

}
