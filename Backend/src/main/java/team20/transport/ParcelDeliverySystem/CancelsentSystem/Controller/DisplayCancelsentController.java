package team20.transport.ParcelDeliverySystem.CancelsentSystem.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Entity.Cancelsent;
import team20.transport.ParcelDeliverySystem.CancelsentSystem.Repository.CancelsentRepository;

@RestController
@RequestMapping("/team20 ") 
@CrossOrigin(origins = {"*"})
public class DisplayCancelsentController {

	@Autowired
	CancelsentRepository cancelSentRepository;
    @GetMapping("/getCancelsent")
    public JSONArray getCancelsent(){
		JSONArray allCancel = new JSONArray();
		for(Cancelsent x : cancelSentRepository.findAll()){
			JSONObject cancel = new JSONObject();
    		cancel.put("id",x.getId());
			cancel.put("Name",x.getName());
			cancel.put("OnPackageing",x.getOnPackageing());
			cancel.put("CreateBy",x.getCreateBy());
    		cancel.put("OnSenttoback",x.getOnSenttoback());
			cancel.put("OnHowtopay",x.getOnHowtopay());
			cancel.put("price",(x.getOnPackageing().getWeight() * x.getOnPackageing().getSendingType().getUnit())*0.5);
			cancel.put("Comment",x.getComment());
    		allCancel.add(cancel);
    	}
    	return allCancel;

    }
    

}