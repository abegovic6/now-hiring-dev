package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.ExampleDTO;
import ba.unsa.etf.pnwt.userservice.entity.ExampleEntity;
import ba.unsa.etf.pnwt.userservice.mapper.ExampleMapper;
import ba.unsa.etf.pnwt.userservice.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    public List<ExampleDTO> getExample() {
        return ExampleMapper.mapToProjections(exampleRepository.findAll());
    }
}
