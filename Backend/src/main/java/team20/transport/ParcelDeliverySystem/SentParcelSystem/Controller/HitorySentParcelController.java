package team20.transport.ParcelDeliverySystem.SentParcelSystem.Controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentParcelRepository;

import java.util.Collection;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class HitorySentParcelController {

    @Autowired
    SentParcelRepository sentParcelRepository;

    @GetMapping("/getAllSentParcel")
    public JSONArray historysentparcel(){
        Collection<SentParcel> sentParcel = sentParcelRepository.findAll();
        JSONArray sent = new JSONArray();
        for(SentParcel temp : sentParcel){
            JSONObject history = new JSONObject();
            history.put("id",temp.getId());
            history.put("atOriginStation",temp.getAtOriginStation());
            history.put("atArriveStation",temp.getAtArriveStation());
            history.put("sentparcel",temp.getSenttime());
            history.put("packageCode",temp.getPackaging().getCode());
            sent.add(history);
        }
        return sent;
    }
}
