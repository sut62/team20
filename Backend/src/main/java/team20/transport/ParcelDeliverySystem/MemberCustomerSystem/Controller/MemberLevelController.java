package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberLevel;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberLevelRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class MemberLevelController {
    @Autowired
    MemberLevelRepository memberLevelRepository;

    @GetMapping("/MemberLevel")
    public Collection<MemberLevel> MemberLevel() {
        return memberLevelRepository.findAll().stream().collect(Collectors.toList());
    }
}