package br.com.diefenthaeler.matheus.aws_project_consumer.model;

import br.com.diefenthaeler.matheus.aws_project_consumer.enums.EventType;
import lombok.Getter;

@Getter
public class ProductEventLogDto {

    private final String code;
    private final EventType eventType;
    private final long productId;
    private final String username;
    private final long timestamp;
    private final String messageId;

    public ProductEventLogDto(ProductEventLog productEventLog){
        this.code = productEventLog.getPk();
        this.eventType = productEventLog.getEventType();
        this.productId = productEventLog.getProductId();
        this.username = productEventLog.getUsername();
        this.timestamp = productEventLog.getTimestamp();
        this.messageId = productEventLog.getMessageId();
    }
}
