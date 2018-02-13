package edu.sdsc.awesome.awesomeWeb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;



@RestController

public class UniqueIDController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/uniqueid")
    public UniqueID uniqueid(@RequestParam(value="name", defaultValue="World") String name) {
        return new UniqueID(counter.incrementAndGet(),
                String.format(template, name));
    }



}
