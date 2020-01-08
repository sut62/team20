package team20.transport.ParcelDeliverySystem.PackagingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.SendingTypeRepository;

import java.util.Collection;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SendingTypeController {
    @Autowired
    SendingTypeRepository repository;

    @GetMapping("/getSendingType")
    public Collection<SendingType> getAllSendingType() {
        return repository.findAll();
    }
}
