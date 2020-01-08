package team20.transport.ParcelDeliverySystem.CancelsentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Senttoback;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.SenttobackRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SenttobackController {
    @Autowired
    SenttobackRepository senttobackRepository;

    @GetMapping("/getSenttoback")
    public Collection<Senttoback> Senttoback() {
        return senttobackRepository.findAll().stream().collect(Collectors.toList());
    }
}