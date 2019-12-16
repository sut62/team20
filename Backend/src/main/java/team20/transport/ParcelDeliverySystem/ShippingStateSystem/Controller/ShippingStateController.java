package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Repository.ShippingStateRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class ShippingStateController {
    @Autowired
    ShippingStateRepository shippingStateRepository;

    @PostMapping("/addShippingState")
    public ShippingState addShippingState(@RequestBody Map<String,Long> allParams){
        Timestamp CurrentTimeStamp = new Timestamp(new Date().getTime());
        ShippingState newShippingState = new ShippingState();
        newShippingState.setTimestamp(CurrentTimeStamp);

        return shippingStateRepository.save(newShippingState);
    }
}
