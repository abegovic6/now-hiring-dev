package ba.unsa.etf.pnwt.userservice.controller;

import ba.unsa.etf.pnwt.userservice.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    @Autowired
    protected DummyService dummyService;

    @PostMapping("/upload")
    public ResponseEntity getExamples() {
        dummyService.uploadData();
        return ResponseEntity.ok().build();
    }
}
