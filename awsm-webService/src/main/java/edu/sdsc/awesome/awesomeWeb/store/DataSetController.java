package edu.sdsc.awesome.awesomeWeb.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/dataset")


public class DataSetController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private DataSetRepository dataSetRepository;





    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewDataset (@RequestParam Double id
            , @RequestParam String DatasetName, @RequestParam String DataSourceName, @RequestParam Double SchemaId, @RequestParam Integer UpdateFreq
                      , @RequestParam Date StartDate, @RequestParam Date LastDate, @RequestParam Double OwnerId)
    {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        //
        DataSet n = new DataSet();
       n.setDatasetName(DatasetName);
       n.setDataSourceName(DataSourceName);
       n.setSchemaId(SchemaId);
       n.setUpdateFreq(UpdateFreq);
       n.setStartDate(StartDate);
       n.setLastDate(LastDate);
       n.setOwnerId(OwnerId);

       dataSetRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<DataSet> getAllUsers() {
        // This returns a JSON or XML with the users
        return dataSetRepository.findAll();
    }




}
