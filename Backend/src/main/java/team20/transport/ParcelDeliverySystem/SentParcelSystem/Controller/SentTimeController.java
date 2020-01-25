package team20.transport.ParcelDeliverySystem.SentParcelSystem.Controller;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentTime;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentParcelRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentTimeRepository;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SentTimeController {
    @Autowired
    SentParcelRepository sentParcelRepository;
    @Autowired
    SentTimeRepository sentTimeRepository;

    @GetMapping("/getAllRangeTimes")
    public JSONArray getAllRangeTimes(){
        JSONArray x = new JSONArray();
        for(SentTime ss : sentTimeRepository.findAll()){
            JSONObject a = new JSONObject();
            a.put("id",ss.getId());
            a.put("rangeTime",ss.getFTime() + "-" + ss.getLTime());
            x.add(a);
        }

        return x;

    }
    @PostMapping("/senttime")
    public SentTime senttime(@RequestBody Map<String,Long> allParams){

        Collection<SentParcel> createBy = sentParcelRepository.findBySentparcelId(Long.valueOf(allParams.get("sentparcelId")));

        SentTime newSentTime = new SentTime();
        newSentTime.setSentParcel(createBy);

        return sentTimeRepository.save(newSentTime);
    }
}
