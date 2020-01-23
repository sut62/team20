package team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Entity.MemberCustomer;
import team20.transport.ParcelDeliverySystem.MemberCustomerSystem.Repository.MemberCustomerRepository;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class DisplayMemberCustomerController {

	@Autowired
    MemberCustomerRepository memberCustomerRepository;

    @GetMapping("/getMemberCustomer")
    public JSONArray getMemberCustomer(){
    	JSONArray allMem = new JSONArray();
    	for(MemberCustomer x : memberCustomerRepository.findAll()){
    		JSONObject mem = new JSONObject();
    		mem.put("id",x.getId());
    		mem.put("MemName",x.getMemName());
    		mem.put("Tel",x.getTel());
    		mem.put("email",x.getEmail());
    		mem.put("createBy",x.getCreateBy());
    		mem.put("memberType",x.getMemberType());
    		mem.put("memberLevel",x.getMemberLevel());
    		allMem.add(mem);
    	}
    	return allMem;

    }
    

}