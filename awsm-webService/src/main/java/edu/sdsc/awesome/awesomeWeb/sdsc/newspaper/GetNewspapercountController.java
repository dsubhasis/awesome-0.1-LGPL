package edu.sdsc.awesome.awesomeWeb.sdsc.newspaper;


import edu.sdsc.awesome.awesomeWeb.etalon.GetGeneList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sdsc/newspaper", method = RequestMethod.GET)

public class GetNewspapercountController {

    private static final Logger logger = LoggerFactory.getLogger(GetNewspapercountController.class);

    public String getnewscount(@RequestParam(value="genelist", defaultValue = "*") String geneList, @RequestParam(value="database", defaultValue = "metadata") String database){


        GetGeneList gcl = new GetGeneList(geneList, database);

        return gcl.getResult();

    }



}
