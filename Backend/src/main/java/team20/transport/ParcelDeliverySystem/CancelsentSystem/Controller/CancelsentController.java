package team20.transport.ParcelDeliverySystem.CancelsentSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.CancelsentRepository;
import team20.transport.ParcelDeliverySystem.Entity.Employee;
import team20.transport.ParcelDeliverySystem.Repository.EmployeeRepository;

import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;

import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Howtopay;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.HowtopayRepository;

import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Senttoback;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.SenttobackRepository;

import team20.transport.ParcelDeliverySystem.Entity.Status;
import team20.transport.ParcelDeliverySystem.Repository.StatusRepository;

import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class CancelsentController {
    @Autowired
    CancelsentRepository cancelsentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PackagingRepository packagingRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    SenttobackRepository senttobackRepository;
    @Autowired
    HowtopayRepository howtopayRepository;


    @PostMapping("/addCancelsent")
    public Cancelsent addCancelsent(@RequestBody Map<String,Long> allParams){

        Employee createBy = employeeRepository.findById(allParams.get("employeeId")).get();
        Packaging packaging = packagingRepository.findById(allParams.get("packageId")).get();
        Status onStatus = statusRepository.findById(allParams.get("statusId")).get();
        Senttoback onSenttoback = senttobackRepository.findById(allParams.get("senttobackId")).get();
        Howtopay onHowtopay = howtopayRepository.findById(allParams.get("howtopayId")).get();
        
        Long countAllName = cancelsentRepository.count();

        String name = String.format("CN%05d",countAllName + 1);

        Cancelsent newCancelsent = new Cancelsent();
        newCancelsent.setCreateBy(createBy);
        newCancelsent.setName(name);
        newCancelsent.setComment((allParams.get("cancelcomment")));
        newCancelsent.setOnPackageing(packaging);
        newCancelsent.setOnStatus(onStatus);
        newCancelsent.setOnSenttoback(onSenttoback);
        newCancelsent.setOnHowtopay(onHowtopay);

        return cancelsentRepository.save(newCancelsent);
        //
    }
}