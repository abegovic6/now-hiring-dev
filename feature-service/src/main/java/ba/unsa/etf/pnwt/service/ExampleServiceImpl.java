package ba.unsa.etf.pnwt.service;

import ba.unsa.etf.pnwt.dto.ExampleDTO;
import ba.unsa.etf.pnwt.mapper.ExampleMapper;
import ba.unsa.etf.pnwt.repository.ExampleRepository;
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
