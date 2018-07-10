package edu.sdsc.awesome.awesomeWeb.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller    // This means t this class is a Controller
@RequestMapping(path = "/basicschema")

public class BasicSchemaController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private BasicSchemaRepository basicSchemaRepository;

}
