package edu.sdsc.awesome.awesomeWeb.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(path="/graphSchemaController")

public class GraphSchemaController {

    @Autowired
    private DataSourceRepository dataSourceRepository;

}
