package br.com.diefenthaeler.matheus.aws_project_consumer.controller;

import br.com.diefenthaeler.matheus.aws_project_consumer.model.ProductEventLogDto;
import br.com.diefenthaeler.matheus.aws_project_consumer.repository.ProductEventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductEventLogController {

    private final ProductEventLogRepository productEventLogRepository;
    @GetMapping("/events")
    public ResponseEntity<List<ProductEventLogDto>> findAll() {

        List<ProductEventLogDto> list = StreamSupport.stream(productEventLogRepository.findAll().spliterator(), false)
                .map(ProductEventLogDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @GetMapping("/events/{code}")
    public ResponseEntity<List<ProductEventLogDto>> findByCode(@PathVariable String code) {

        List<ProductEventLogDto> allByPk = productEventLogRepository.findAllByPk(code)
                .stream()
                .map(ProductEventLogDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allByPk);
    }

    @GetMapping("/events/{code}/{event}")
    public ResponseEntity<List<ProductEventLogDto>> findByCodeAndEventTypr(@PathVariable String code,
                                                                           @PathVariable String event) {
        List<ProductEventLogDto> allByPkAndSk = productEventLogRepository.findAllByPkAndSkStartsWith(code, event)
                .stream()
                .map(ProductEventLogDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allByPkAndSk);
    }
}
