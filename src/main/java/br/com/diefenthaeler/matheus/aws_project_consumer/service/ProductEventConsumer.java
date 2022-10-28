package br.com.diefenthaeler.matheus.aws_project_consumer.service;

import br.com.diefenthaeler.matheus.aws_project_consumer.model.Envelope;
import br.com.diefenthaeler.matheus.aws_project_consumer.model.ProductEvent;
import br.com.diefenthaeler.matheus.aws_project_consumer.model.SnsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductEventConsumer {

    private final ObjectMapper objectMapper;

    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receivedProductEvent(TextMessage textMessage) throws JMSException, IOException {


        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(), SnsMessage.class);

        Envelope envelope = objectMapper.readValue(snsMessage.getMessage(), Envelope.class);

        ProductEvent productEvent = objectMapper.readValue(envelope.getData(), ProductEvent.class);

        log.info("Product event received - Event: {} - Productid: {} - ",
                envelope.getEventType(),
                productEvent.getProductId());
    }
}
