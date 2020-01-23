package team20.transport.ParcelDeliverySystem.ShippingStateSystem.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;
import team20.transport.ParcelDeliverySystem.ShippingStateSystem.Entity.ShippingState;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class DisplayShippingStateController {

    @Autowired
    PackagingRepository packagingRepository;

    @GetMapping("/getShippingStateByPackageCode/{code}")
    public JSONArray getShippingStateByPackageCode(@PathVariable String code){
        Packaging packaging = packagingRepository.findPackageByCode(code);
        JSONArray allShippingState = new JSONArray();
        for(ShippingState temp : packaging.getHaveShippingState()){
            JSONObject shippingState = new JSONObject();
            shippingState.put("id",temp.getId());
            shippingState.put("code",temp.getCode());
            shippingState.put("onStatus",temp.getOnStatus().getName());
            shippingState.put("atStation",temp.getAtStation().getName());
            shippingState.put("createBy",temp.getCreateBy().getName());
            shippingState.put("timeStamp",temp.getTimestamp());
            allShippingState.add(shippingState);
        }

        return allShippingState;
    }

}
