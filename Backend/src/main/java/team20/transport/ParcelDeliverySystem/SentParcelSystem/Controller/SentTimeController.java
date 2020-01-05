package team20.transport.ParcelDeliverySystem.SentParcelSystem.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentTime;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentParcelRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentTimeRepository;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SentTimeController {
    @Autowired
    SentParcelRepository sentParcelRepository;
    @Autowired
    SentTimeRepository sentTimeRepository;

    @PostMapping("/senttime")
    public SentTime senttime(@RequestBody Map<String,Long> allParams){

        Collection<SentParcel> createBy = sentParcelRepository.findBySentparcelId(Long.valueOf(allParams.get("sentparcelId")));

        SentTime newSentTime = new SentTime();
        newSentTime.setSentParcel(createBy);

        return sentTimeRepository.save(newSentTime);
    }
}
