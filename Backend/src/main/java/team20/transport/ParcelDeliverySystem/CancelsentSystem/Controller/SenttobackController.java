package team20.transport.ParcelDeliverySystem.CancelSentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.CancelSentSystem.Entity.Senttoback;
import team20.transport.ParcelDeliverySystem.CancelSentSystem.Repository.SenttobackRepository;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SenttobackController {
    @Autowired
    SenttobackRepository senttobackRepository;

    @GetMapping("/Senttoback")
    public Collection<Senttoback> Senttoback() {
        return senttobackRepository.findAll().stream().collect(Collectors.toList());
    }
}