package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.SatisfactionLevelRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SatisfactionLevelController {
    @Autowired
    SatisfactionLevelRepository repository;

    @GetMapping("/satisfactionLevel")
    public List<SatisfactionLevel> getAllSatisfactionLevel() {
        List<SatisfactionLevel> satisfactionLevel = new ArrayList<>();
        repository.findAll().forEach(satisfactionLevel::add);
        return satisfactionLevel;
    }
}