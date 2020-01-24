package team20.transport.ParcelDeliverySystem.PackagingSystem.Controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class DisplayPackagePayment {
    @Autowired
    PackagingRepository packagingRepository;

    @GetMapping("getPackagePayment/{code}")
    public JSONObject getPackagePayment(@PathVariable String code){
        Packaging pack = packagingRepository.findPackageByCode(code);
        JSONObject json = new JSONObject();
        json.put("sentBy",pack.getSentBy().getMemName());
        json.put("createBy",pack.getCreateBy().getName());
        json.put("atStation",pack.getAtStation().getName());
        json.put("place",pack.getPlace());
        json.put("receiver",pack.getReciever());
        json.put("sendingType",pack.getSendingType().getType());
        json.put("weight",pack.getWeight());
        json.put("price",pack.getWeight()*pack.getVolume()*pack.getSendingType().getUnit());
        return json;
    }
}
