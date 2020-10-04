import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import mas.Api;

import java.io.IOException;

public class Main {
    private static DynamoDB dynamoDB;
    public static void main(String[] args) throws IOException {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                .build();
        dynamoDB = new DynamoDB(client);
        Api api = new Api();
        api.createTable(dynamoDB);

    }

}
