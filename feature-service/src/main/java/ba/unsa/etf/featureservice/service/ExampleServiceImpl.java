package ba.unsa.etf.featureservice.service;

import ba.unsa.etf.featureservice.dto.ExampleDTO;
import ba.unsa.etf.featureservice.mapper.ExampleMapper;
import ba.unsa.etf.featureservice.repository.ExampleRepository;
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
