package ba.unsa.etf.pnwt.userservice.controller;

import ba.unsa.etf.pnwt.userservice.dto.ExampleDTO;
import ba.unsa.etf.pnwt.userservice.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Example controller
 */
@RestController
@RequestMapping("/user-service/example")
public class ExampleController {

    @Autowired
    protected ExampleService exampleService;

    @GetMapping("/all")
    public List<ExampleDTO> getExamples() {
        return exampleService.getExample();
    }
}
