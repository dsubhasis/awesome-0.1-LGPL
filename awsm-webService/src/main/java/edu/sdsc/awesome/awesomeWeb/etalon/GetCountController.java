package edu.sdsc.awesome.awesomeWeb.etalon;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCountController {
    @RequestMapping("/etalon/0.1/getcount")
    public GetCount getcount(@RequestParam(value="dbname", defaultValue="World") String name) {
        GetCount gc = new GetCount(name);
        return gc;
    }





}
