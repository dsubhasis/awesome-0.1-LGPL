package edu.sdsc.awesome.awesomeWeb.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/dataset")


public class DataSetController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private DataSetRepository dataSetRepository;





    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody
    String addDataset (@RequestParam String id
            , @RequestParam String name, @RequestParam String source, @RequestParam String schema, @RequestParam Integer ufreq
                      , @RequestParam String start, @RequestParam String last, @RequestParam String owner)
    {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        // ?id=10&name=ram&source=x&schema=y&ufreq=10&start=2016-01-09&end=2018-00-00&owner=pota
        //http://localhost:8080/dataset/add?addDataset?id=10
       Date startDate = new Date();
        Date endDate = new Date();
        System.out.println("Hi");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
             startDate = formatter.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DataSet n = new DataSet();
       n.setDatasetName(name);
       n.setDataSourceName(source);
       n.setSchemaId(Double.valueOf(schema));
       n.setUpdateFreq(ufreq);
       n.setStartDate(startDate);
       n.setLastDate(endDate);
       n.setOwnerId(Double.valueOf(owner));

       dataSetRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<DataSet> getAllUsers() {
        // This returns a JSON or XML with the users

        System.out.println("Hi");

        return dataSetRepository.findAll();
    }




}
