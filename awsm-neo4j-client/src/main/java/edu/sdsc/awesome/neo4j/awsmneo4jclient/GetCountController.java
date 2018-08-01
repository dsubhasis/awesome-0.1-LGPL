package edu.sdsc.awesome.neo4j.awsmneo4jclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonObject;

@RestController
public class GetCountController {
    @RequestMapping("/client")
    public String getSysPref(@RequestParam(value="dbname", defaultValue="World") String name) {
        GetCount gc = new GetCount();
        JsonObject per = gc.systemPerf();
        return per.toString();
    }





}