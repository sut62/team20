package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberType;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberTypeRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class MemberTypeController {
    @Autowired
    MemberTypeRepository memberTypeRepository;

    @GetMapping("/MemberType")
    public Collection<MemberType> MemberType() {
        return memberTypeRepository.findAll().stream().collect(Collectors.toList());
    }
}