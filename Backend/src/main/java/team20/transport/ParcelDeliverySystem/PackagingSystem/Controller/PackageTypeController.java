package team20.transport.ParcelDeliverySystem.PackagingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.PackageType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackageTypeRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Packaging")
@CrossOrigin(origins = {"*"})
public class PackageTypeController {
    @Autowired
    PackageTypeRepository repository;

    @GetMapping("/packageType")
    public List<PackageType> getAllPackageType() {
        List<PackageType> packageTypes = new ArrayList<>();
        repository.findAll().forEach(packageTypes::add);
        return packageTypes;
    }
}
