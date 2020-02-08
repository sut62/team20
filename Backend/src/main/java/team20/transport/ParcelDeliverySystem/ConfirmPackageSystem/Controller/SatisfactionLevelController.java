package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.SatisfactionLevel;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.SatisfactionLevelRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SatisfactionLevelController {
    @Autowired
    SatisfactionLevelRepository satisfactionLevelRepository;

    @GetMapping("/satisfactionLevel")
    public Collection<SatisfactionLevel> SatisfactionLevel() {
        return satisfactionLevelRepository.findAll().stream().collect(Collectors.toList());
    }
}