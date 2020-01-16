package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.Repository.StatusRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Repository.ShippingStateRepository;
import java.time.LocalDateTime;
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

    @GetMapping("/getShippingStateById/{id}")
    public JSONObject getShippingStateById(@PathVariable Long id){
        ShippingState shippingstate = shippingStateRepository.findById(id).get();
        JSONObject ret = new JSONObject();
        ret.put("id",shippingstate.getId());
        ret.put("ofPackage",shippingstate.getOfPackage());
        ret.put("onStatus",shippingstate.getOnStatus());
        ret.put("atStation",shippingstate.getAtStation());
        ret.put("createBy",shippingstate.getCreateBy());
        ret.put("timeStamp",shippingstate.getTimestamp());

        return ret;
    }

    @PostMapping("/addShippingState")
    public ShippingState addShippingState(@RequestBody Map<String,Long> allParams){

        Packaging ofPackage = packagingRepository.findById(allParams.get("packageId")).get();
        Employee createBy = employeeRepository.findById(allParams.get("employeeId")).get();
        Status onStatus = statusRepository.findById(allParams.get("statusId")).get();
        Station atStation = stationRepository.findById(allParams.get("stationId")).get();

        ShippingState newShippingState = new ShippingState();

        // Generate code by package id
        String code = "SHP" + ofPackage.getCode();

        newShippingState.setTimestamp(LocalDateTime.now());
        newShippingState.setCode(code);
        newShippingState.setIsActive(true);
        newShippingState.setOnStatus(onStatus);
        newShippingState.setAtStation(atStation);
        newShippingState.setCreateBy(createBy);
        newShippingState.setOfPackage(ofPackage);

        return shippingStateRepository.save(newShippingState);
    }
}
