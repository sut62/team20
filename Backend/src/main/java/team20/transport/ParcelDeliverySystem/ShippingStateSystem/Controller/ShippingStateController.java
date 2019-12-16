package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.Repository.StatusRepository;
import team20.transport.ParcelDeliverySystem.PackagingStateSystem.Repository.PackagingRepository;
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
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    PackagingRepository packagingRepository;

    @PostMapping("/addShippingState")
    public ShippingState addShippingState(@RequestBody Map<String,Long> allParams){

        Packaging ofPackage = packagingRepository.findById(allParams.get("packageId")).get();
        Employee createBy = employeeRepository.findById(allParams.get("employeeId")).get();
        Status onStatus = statusRepository.findById(allParams.get("statusId")).get();
        Station atStation = stationRepository.findById(allParams.get("stationId")).get();

        Timestamp CurrentTimeStamp = new Timestamp(new Date().getTime());
        ShippingState newShippingState = new ShippingState();
        newShippingState.setTimestamp(CurrentTimeStamp);
        newShippingState.setOnStatus(onStatus);
        newShippingState.setAtStation(atStation);
        newShippingState.setCreateBy(createBy);
        newShippingState.setOfPackage(ofPackage);

        return shippingStateRepository.save(newShippingState);
    }
}
