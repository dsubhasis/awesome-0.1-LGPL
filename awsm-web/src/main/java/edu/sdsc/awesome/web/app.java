package edu.sdsc.awesome.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Hello world!
 *
 */

 @Controller
 @EnableAutoConfiguration

public class App 
{

    @RequestMapping("/")
    @ResponseBody
    String Home(){
        return "this is awesome";


    }
    public static void main( String[] args )
    {   
        SpringApplication.run(SamepleController.class, args)


    }
}