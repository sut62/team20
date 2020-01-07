package team20.transport.ParcelDeliverySystem.PackagingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.SendingTypeRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Packaging")
@CrossOrigin(origins = {"*"})
public class SendingTypeController {
    @Autowired
    SendingTypeRepository repository;

    @GetMapping("/getSendingType")
    public List<SendingType> getAllSendingType() {
        List<SendingType> sendingTypes = new ArrayList<>();
        repository.findAll().forEach(sendingTypes::add);
        return sendingTypes;
    }
}
