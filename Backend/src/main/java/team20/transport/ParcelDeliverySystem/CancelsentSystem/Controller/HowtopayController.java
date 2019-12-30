package team20.transport.ParcelDeliverySystem.CancelSentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.CancelSentSystem.Entity.Howtopay;
import team20.transport.ParcelDeliverySystem.CancelSentSystem.Repository.HowtopayRepository;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class HowtopayController {
    @Autowired
    HowtopayRepository howtopayRepository;

    @GetMapping("/Howtopay")
    public Collection<Howtopay> Howtopay() {
        return howtopayRepository.findAll().stream().collect(Collectors.toList());
    }
}