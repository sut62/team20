package team20.transport.ParcelDeliverySystem.CancelsentSystem.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.CancelsentRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;

@RestController
@RequestMapping("/team20 ") 
@CrossOrigin(origins = {"*"})
public class DisplayCancelsentController {

	@Autowired
	CancelsentRepository cancelSentRepository;
	@Autowired
	PackagingRepository packagingRepository;
    @GetMapping("/getCancelsent")
    public JSONArray getCancelsent(@RequestBody Map<String,String> allParams){
		JSONArray allCancel = new JSONArray();
		for(Cancelsent x : cancelSentRepository.findAll()){
			Packaging id = packagingRepository.findById(Long.valueOf(allParams.get(x))).get();
			JSONObject cancel = new JSONObject();
    		cancel.put("id",x.getId());
			cancel.put("Name",x.getName());
			cancel.put("OnPackageing",x.getOnPackageing());
			cancel.put("CreateBy",x.getCreateBy());
    		cancel.put("OnSenttoback",x.getOnSenttoback());
			cancel.put("OnHowtopay",x.getOnHowtopay());
			cancel.put("price",(id.getWeight()*id.getVolume()*id.getSendingType().getUnit())*0.5);
			cancel.put("Comment",x.getComment());
    		allCancel.add(cancel);
    	}
    	return allCancel;

    }
    

}