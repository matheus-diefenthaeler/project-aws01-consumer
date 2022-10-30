package br.com.diefenthaeler.matheus.aws_project_consumer.repository;

import br.com.diefenthaeler.matheus.aws_project_consumer.model.ProductEventKey;
import br.com.diefenthaeler.matheus.aws_project_consumer.model.ProductEventLog;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface ProductEventLogRepository extends CrudRepository <ProductEventLog, ProductEventKey> {

    List<ProductEventLog> findAllByPk(String code);

    List<ProductEventLog> findAllByPkAndSkStartsWith(String code, String eventType);
}
