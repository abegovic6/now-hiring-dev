package ba.unsa.etf.pnwt.jobsservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job-service/example")
public class ExampleController {

    @GetMapping("/all")
    public String getExamples() {
        return "Service running";
    }

}
