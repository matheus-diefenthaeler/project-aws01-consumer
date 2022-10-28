package br.com.diefenthaeler.matheus.aws_project_consumer.model;

import br.com.diefenthaeler.matheus.aws_project_consumer.enums.EventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Envelope {
    private EventType eventType;
    private String data;
}
