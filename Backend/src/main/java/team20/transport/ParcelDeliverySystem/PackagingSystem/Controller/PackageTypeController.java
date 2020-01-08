package team20.transport.ParcelDeliverySystem.PackagingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.PackageType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackageTypeRepository;

import java.util.Collection;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class PackageTypeController {
    @Autowired
    PackageTypeRepository repository;

    @GetMapping("/getPackageType")
    public Collection<PackageType> getAllPackageType(){
        return repository.findAll();
    }
}
