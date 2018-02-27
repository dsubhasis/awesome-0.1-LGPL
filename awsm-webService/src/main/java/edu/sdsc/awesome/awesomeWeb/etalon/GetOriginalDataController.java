package edu.sdsc.awesome.awesomeWeb.etalon;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GetOriginalDataController {

    @RequestMapping("/etalon/0.1/getoriginaldata")
    public String  getcount(@RequestParam(value="dbname", defaultValue="World") String name) {
        GetOriginalData gc = new GetOriginalData(name);
        return gc.getResult();
    }
}
