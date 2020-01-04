package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/ConfirmPackage")
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
    public ConfirmPackage addConfirmPackage(@RequestBody Map<String,Long> allParams){

        Packaging packaging = packagingRepository.findById(allParams.get("packageId")).get();
        Employee createBy = employeeRepository.findById(allParams.get("employeeId")).get();
        SatisfactionLevel satisfactionLevel = satisfactionLevelRepository.findById(allParams.get("satisfactionLevelId")).get();

        ConfirmPackage newConfirmPackage = new ConfirmPackage();
        newConfirmPackage.setConfirmDate(new Date());
        newConfirmPackage.setSatisfactionLevel(satisfactionLevel);
        newConfirmPackage.setCreateBy(createBy);
        newConfirmPackage.setPackaging(packaging);

        return confirmPackageRepository.save(newConfirmPackage);
    }
}