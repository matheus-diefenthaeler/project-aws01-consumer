package br.com.diefenthaeler.matheus.aws_project_consumer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEvent {

    private long productId;
    private String code;
    private String username;

}
