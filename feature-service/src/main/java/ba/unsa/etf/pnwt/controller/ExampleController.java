package ba.unsa.etf.pnwt.controller;

import ba.unsa.etf.pnwt.dto.ExampleDTO;
import ba.unsa.etf.pnwt.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Example controller
 */
@RestController
@RequestMapping("/feature-service/example")
public class ExampleController {

    @Autowired
    protected ExampleService exampleService;

    @GetMapping("/all")
    public List<ExampleDTO> getExamples() {
        return exampleService.getExample();
    }
}
