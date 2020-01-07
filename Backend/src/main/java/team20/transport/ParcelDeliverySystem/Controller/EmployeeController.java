package team20.transport.ParcelDeliverySystem.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
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
    public JSONObject chkLogin(@RequestBody Map<String,String> allParams){
        Employee x = employeeRepository.findByIdAndEmail(Long.valueOf(allParams.get("employeeId")),allParams.get("email"));
        JSONObject z = new JSONObject();
        z.put("id",x.getId());
        z.put("name",x.getName());
        z.put("email",x.getEmail());
        z.put("employeePosition",x.getEmployeePosition());

        return z;
    }

}
