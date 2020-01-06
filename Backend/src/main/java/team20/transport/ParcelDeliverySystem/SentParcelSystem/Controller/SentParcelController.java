package team20.transport.ParcelDeliverySystem.SentParcelSystem.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentTime;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentParcelRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentTimeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SentParcelController {
    @Autowired
    PackagingRepository packagingRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    SentParcelRepository sentParcelRepository;
    @Autowired
    SentTimeRepository sentTimeRepository;
    @PostMapping("/addSentParcel")
    public SentParcel addSentParcel(@RequestBody Map<String,Long> allParams){

        Packaging ofPackage = packagingRepository.findById(Long.valueOf(allParams.get("packageId")));
        Station atStation = stationRepository.findById(allParams.get("stationId")).get();
        SentTime toparcel = sentTimeRepository.findById(allParams.get("senttimeId")).get();

        SentParcel newSentParcel = new SentParcel();
        newSentParcel.setPackaging(ofPackage);
        newSentParcel.setAtStation(atStation);
        newSentParcel.setSenttime(toparcel);

        return sentParcelRepository.save(newSentParcel);
    }
}
