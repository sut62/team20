package team20.transport.ParcelDeliverySystem.PackagingSystem.Controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackageTypeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.SendingTypeRepository;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberCustomerRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.PackageType;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.SendingType;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class PackagingController {
    @Autowired
    StationRepository stationRepository;
    @Autowired
    PackagingRepository packagingRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MemberCustomerRepository memberCustomerRepository;
    @Autowired
    PackageTypeRepository packageTypeRepository;
    @Autowired
    SendingTypeRepository sendingTypeRepository;

    @PostMapping("/addPackaging")
    public Packaging addPackaging(@RequestBody Map<String,Long> allParams){
        MemberCustomer sentBy = memberCustomerRepository.findById(allParams.get("customerId")).get();
        Employee createBy = employeeRepository.findById(allParams.get("employeeId")).get();
        Station atStation = stationRepository.findById(allParams.get("stationId")).get();
        PackageType ptype = packageTypeRepository.findById(allParams.get("pTypeId")).get();
        SendingType stype = sendingTypeRepository.findById(allParams.get("sTypeId")).get();

        Date pdate = new Date();
        Packaging newPackaging = new Packaging();
        newPackaging.setSentBy(sentBy);
        newPackaging.setAtStation(atStation);
        newPackaging.setCreateBy(createBy);
        newPackaging.setPackageType(ptype);
        newPackaging.setSendingType(stype);
        newPackaging.setPackageDate(pdate);

        return packagingRepository.save(newPackaging);
    }
    @GetMapping("/findPackageById/{id}")
    public JSONObject findPackageById(@PathVariable Long id){
        Packaging pac = packagingRepository.findById(id).get();
        JSONObject json = new JSONObject();
        json.put("id",pac.getId());
        json.put("packaging_date",pac.getPackageDate());
        json.put("place",pac.getPlace());
        json.put("receiever",pac.getReciever());
        json.put("volume",pac.getVolume());
        json.put("weight",pac.getWeight());
        json.put("station",pac.getAtStation());
        json.put("createBy",pac.getCreateBy());
        json.put("packageType",pac.getPackageType());
        json.put("sendingType",pac.getSendingType());
        json.put("sentBy",pac.getSentBy());
        return json;
    }
}
