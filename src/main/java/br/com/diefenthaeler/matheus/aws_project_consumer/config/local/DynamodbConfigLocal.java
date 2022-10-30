package br.com.diefenthaeler.matheus.aws_project_consumer.config.local;

import br.com.diefenthaeler.matheus.aws_project_consumer.model.ProductEventLog;
import br.com.diefenthaeler.matheus.aws_project_consumer.repository.ProductEventLogRepository;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

@Profile("local")
@Configuration
@EnableDynamoDBRepositories(basePackageClasses = ProductEventLogRepository.class)
public class DynamodbConfigLocal {

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.fromName(awsRegion)).build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setupDB(ApplicationReadyEvent event) {
        AmazonDynamoDB amazonDynamoDB = event.getApplicationContext().getBean(AmazonDynamoDB.class);
        DynamoDBMapper dynamoDBMapper = event.getApplicationContext().getBean(DynamoDBMapper.class);

        CreateTableRequest createTableTarefaRequest = dynamoDBMapper.generateCreateTableRequest(ProductEventLog.class);

        if (!amazonDynamoDB.listTables().getTableNames().contains(createTableTarefaRequest.getTableName())) {
            createTableTarefaRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            amazonDynamoDB.createTable(createTableTarefaRequest);
        }
    }
}
