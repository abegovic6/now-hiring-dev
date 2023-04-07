//package ba.unsa.etf.pnwt.jobsservice.controller;
//
//import ba.unsa.etf.pnwt.jobsservice.dto.Job2ApplicationDTO;
//import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
//import ba.unsa.etf.pnwt.jobsservice.entity.Job2ApplicationEntity;
//import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
//import ba.unsa.etf.pnwt.jobsservice.service.Job2ApplicationService;
//import ba.unsa.etf.pnwt.jobsservice.service.JobService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Example controller
// */
//@RestController
//@RequestMapping("/api/job2application")
//public class Job2ApplicationController {
//
//    @Autowired
//    protected Job2ApplicationService job2ApplicationService;
//
//    @GetMapping("/all")
//    public List<Job2ApplicationDTO> getAll() {
//        return job2ApplicationService.getAll();
//    }
//
//    @GetMapping("/get")
//    public Job2ApplicationDTO get(@RequestParam int id) {
//        return job2ApplicationService.getById(id);
//    }
//
//    @PostMapping("/add")
//    public Job2ApplicationDTO add(@RequestBody Job2ApplicationEntity job){
//        return job2ApplicationService.save(job);
//    }
//
//    @PostMapping("/update")
//    public Job2ApplicationDTO update(@RequestBody Job2ApplicationEntity job){
//        return job2ApplicationService.update(job);
//    }
//
//    @DeleteMapping("/delete")
//    public void delete(@RequestBody Job2ApplicationEntity job){
//        job2ApplicationService.delete(job);
//    }
//
//    @DeleteMapping("/deleteJ2A")
//    public void deleteById(@RequestParam int id){
//        job2ApplicationService.deleteById(id);
//    }
//}
