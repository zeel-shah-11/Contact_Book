package mas;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static mas.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApiTest {

 private static String INPUT="input";
 private static String EXPECTED="expected";
 private static DynamoDB dynamoDB;
 private static JsonObject jsonObj;
 Api api = new Api();

 @BeforeClass
 public static <JSONObject> void setup() throws IOException, ParseException {
     AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
             new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
             .build();
     String jsonStr = IOUtils.toString(new FileReader("src\\test\\java\\mas\\ApiTest.json"));
     jsonObj = new JsonObject(jsonStr);
     dynamoDB = new DynamoDB(client);
 }
    @Test
    public void testQueryEmail_P()
    {
           final String testCaseName="testQueryEmail_P";
           final String testInput = jsonObj.getJsonObject(testCaseName).getString(INPUT);
           final String testExpected = jsonObj.getJsonObject(testCaseName).getString(EXPECTED);
           String result= api.queryEmail(testInput,dynamoDB).toString();
           assertEquals(result,testExpected);
    }

    @Test
    public void testQueryEmail_N()
    {
        final String testCaseName="testQueryEmail_N";
        final String testInput = jsonObj.getJsonObject(testCaseName).getString(INPUT);
        final String testExpected = jsonObj.getJsonObject(testCaseName).getString(EXPECTED);
        assertNull(api.queryEmail(testInput,dynamoDB));

    }

    @Test
    public void testQueryName_P()
    {
        final String testCaseName="testQueryName_P";
        final String testInput = jsonObj.getJsonObject(testCaseName).getString(INPUT);
        JsonArray testExpected = jsonObj.getJsonObject(testCaseName).getJsonArray(EXPECTED);
        List<String> result= api.queryName(testInput,dynamoDB);
        int i=0;
        for(String s:result)
        {
            JsonObject json = new JsonObject(s);
            assertEquals(json,testExpected.getJsonObject(i));
            i++;

        }
    }

    @Test
    public void testQueryName_N()
    {
        final String testCaseName="testQueryName_N";
        final String testInput = jsonObj.getJsonObject(testCaseName).getString(INPUT);
        JsonArray testExpected = jsonObj.getJsonObject(testCaseName).getJsonArray(EXPECTED);
        List<String> result= api.queryName(testInput,dynamoDB);
        assertEquals(result.size(), 0);
    }


    @Test
    public void testEditItem_P()
    {
        final String testCaseName="testEditItem_P";
        final JsonObject testInput = jsonObj.getJsonObject(testCaseName).getJsonObject(INPUT);
        final String testExpected = jsonObj.getJsonObject(testCaseName).getString(EXPECTED);
        api.editItem(testInput.getString(EMAILID),dynamoDB,testInput.getString(NAME),testInput.getString(CONTACTNO),testInput.getString(ADDRESS));
        String result= api.queryEmail(testInput.getString(EMAILID),dynamoDB).toString();
        assertEquals(result,testExpected);
    }

    @Test
    public void testCreate_P()
    {
        final String testCaseName="testCreate_P";
        final JsonObject testInput = jsonObj.getJsonObject(testCaseName).getJsonObject(INPUT);
        final String testExpected = jsonObj.getJsonObject(testCaseName).getString(EXPECTED);
        api.create(testInput.getString(EMAILID),dynamoDB,testInput.getString(NAME),testInput.getString(CONTACTNO),testInput.getString(ADDRESS));
        String result= api.queryEmail(testInput.getString(EMAILID),dynamoDB).toString();
        assertEquals(result,testExpected);
    }

    @Test
    public void testDel_P()
    {
        final String testCaseName="testDel_P";
        final String testInput = jsonObj.getJsonObject(testCaseName).getString(INPUT);
        api.del(testInput,dynamoDB);
        assertNull(api.queryEmail(testInput,dynamoDB));
    }

}
