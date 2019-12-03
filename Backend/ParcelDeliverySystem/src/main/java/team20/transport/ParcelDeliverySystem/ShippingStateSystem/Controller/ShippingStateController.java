package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Repository.ShippingStateRepository;

import java.util.Collection;

@RestController
@CrossOrigin(origins = {"*"})
public class ShippingStateController {
    @Autowired
    ShippingStateRepository shippingStateRepository;

    @GetMapping("/ShippingState")
    public Collection<ShippingState> getAllShippingState(){
        return shippingStateRepository.findAll();
    }
}
