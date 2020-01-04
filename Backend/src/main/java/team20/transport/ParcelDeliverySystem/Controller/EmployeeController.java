package team20.transport.ParcelDeliverySystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;

import java.util.Collection;

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

}
