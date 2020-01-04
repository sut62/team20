package team20.transport.ParcelDeliverySystem.CancelsentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.HowtopayRepository;

import java.util.Collection;
import java.util.stream.Collectors;

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