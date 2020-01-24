package team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Entity.ConfirmPackage;
import team20.transport.ParcelDeliverySystem.ConfirmPackageSystem.Repository.ConfirmPackageRepository;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class ShowConfirmPackageController {

    @Autowired
    ConfirmPackageRepository confirmPackageRepository;

    @GetMapping("/getConfirmPackage")
    public JSONArray getConfirmPackage(){
        JSONArray allConfirmPackage = new JSONArray();
        for(ConfirmPackage con : confirmPackageRepository.findAll()){
            JSONObject confirmPackage = new JSONObject();
            confirmPackage.put("id",con.getId());
            confirmPackage.put("code",con.getCode());
            confirmPackage.put("name",con.getName());
            confirmPackage.put("comment",con.getComment());
            confirmPackage.put("ConfirmDate",con.getConfirmDate());
            confirmPackage.put("Packaging",con.getPackaging());
            confirmPackage.put("createBy",con.getCreateBy().getName());
            confirmPackage.put("satisfactionLevel",con.getSatisfactionLevel());
            allConfirmPackage.add(confirmPackage);
        }

        return allConfirmPackage;
    }

}
