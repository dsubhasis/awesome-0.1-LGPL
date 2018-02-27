package edu.sdsc.awesome.awesomeWeb.etalon;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GetGeneListController {

    @RequestMapping("/etalon/0.1/getgenelist")
    public String getgenelist(@RequestParam(value="genelist", defaultValue = "*") String geneList, @RequestParam(value="database", defaultValue = "metadata") String database){


        GetGeneList gcl = new GetGeneList(geneList, database);

        return gcl.getResult();

    }

}
