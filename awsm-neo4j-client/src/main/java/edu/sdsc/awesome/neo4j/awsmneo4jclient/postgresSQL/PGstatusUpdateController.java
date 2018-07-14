package edu.sdsc.awesome.neo4j.awsmneo4jclient.postgresSQL;

import edu.sdsc.awesome.neo4j.awsmneo4jclient.GetCount;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonObject;

@RestController
public class PGstatusUpdateController {
    @RequestMapping("/client/pgsql")
    public String getdbstat(@RequestParam(value = "schema", defaultValue = "World") String name) {
        PGstatusUpdate gc = new PGstatusUpdate();
        JsonObject per = gc.dbstats(name);
        return per.toString();
    }
}



