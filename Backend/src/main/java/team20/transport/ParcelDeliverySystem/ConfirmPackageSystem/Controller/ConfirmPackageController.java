package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.SatisfactionLevelRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.ConfirmPackage;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.ConfirmPackageRepository;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class ConfirmPackageController {
    @Autowired
    ConfirmPackageRepository confirmPackageRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    SatisfactionLevelRepository satisfactionLevelRepository;
    @Autowired
    PackagingRepository packagingRepository;

    @PostMapping("/addConfirmPackage")
    @ResponseBody
    public Object addConfirmPackage(@RequestBody Map<String,String> allParams) {

        Packaging packaging = packagingRepository.findById(Long.valueOf(allParams.get("packageId"))).get();
        Employee createBy = employeeRepository.findById(Long.valueOf(allParams.get("employeeId"))).get();
        SatisfactionLevel satisfactionLevel = satisfactionLevelRepository.findById(Long.valueOf(allParams.get("satisfactionLevelId"))).get();

        if (packaging.getConfirmPackage() != null) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        long countaddConfirmPackge = confirmPackageRepository.count();
        String code = String.format("CPT20%05d", countaddConfirmPackge + 1);
        String name = allParams.get("name");
        String comment = allParams.get("comment");

        ConfirmPackage newConfirmPackage = new ConfirmPackage();
        newConfirmPackage.setCode(code);
        newConfirmPackage.setName(name);
        newConfirmPackage.setComment(comment);
        newConfirmPackage.setSatisfactionLevel(satisfactionLevel);
        newConfirmPackage.setCreateBy(createBy);
        newConfirmPackage.setPackaging(packaging);

            return confirmPackageRepository.save(newConfirmPackage);
    }
}
