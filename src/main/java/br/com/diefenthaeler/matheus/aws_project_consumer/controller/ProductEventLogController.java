package br.com.diefenthaeler.matheus.aws_project_consumer.controller;

import br.com.diefenthaeler.matheus.aws_project_consumer.repository.ProductEventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductEventLogController {

    private final ProductEventLogRepository productEventLogRepository;

}
