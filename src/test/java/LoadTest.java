import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Random;

public class LoadTest {

    private final int RESPONSE_SUCCESS = 200;

    private static byte[] getRandomByteArray(){
        Random random= new Random();
        int size = random.nextInt(256) + 1;
        byte[] result= new byte[size];
        random.nextBytes(result);
        return result;
    }

    @BeforeTest
    void setUp(){
        //TODO: make clenup of database
    }

    @AfterTest
    void tearDown(){
        //TODO: calculate expected
    }

    @Parameters({ "testDuration" })
    @Test
    void loadTest(long testDuration) throws IOException, URISyntaxException {
        long startTime = System.currentTimeMillis(); //fetch starting time
        int counter = 0;
        while((System.currentTimeMillis() - startTime) < testDuration)
        {
            byte myArr[] = getRandomByteArray();
            String raw_data = new String(myArr);
            URI uri = new URI( String.format(
                    "http://localhost:8080/create?id=" + counter + "&data=%s",
                    URLEncoder.encode( raw_data , "UTF8" ) ) );


            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(uri);

            HttpResponse response = null;
            response = client.execute(request);

            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());
            Assert.assertTrue(response.getStatusLine().getStatusCode() == RESPONSE_SUCCESS);
            counter += 1;
        }
    }
}
