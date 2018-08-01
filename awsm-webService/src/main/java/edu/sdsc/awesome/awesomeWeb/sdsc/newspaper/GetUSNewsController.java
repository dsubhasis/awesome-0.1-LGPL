package edu.sdsc.awesome.awesomeWeb.sdsc.newspaper;

import edu.sdsc.awesome.awesomeWeb.etalon.GetGeneList;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;

@RestController

public class GetUSNewsController {

    @RequestMapping(value = "/awesome/1.0/getusnews", method = RequestMethod.POST)
    public String getusnews(@RequestParam(value="author", defaultValue = "*") String authorSearch,
                            @RequestParam(value="title", defaultValue = "*") String titleSearch,
                            @RequestParam(value="news", defaultValue = "*") String newsSearch,
                            @RequestParam(value="daterange", defaultValue = "*") String dateSearch,

                            @RequestParam(value="config", defaultValue = "metadata") String config,
                            @RequestBody() String x){



        GetLuceneSearchString gcl = new GetLuceneSearchString(authorSearch, titleSearch, newsSearch, dateSearch,config);

        return gcl.getSearchquery();

    }

}
