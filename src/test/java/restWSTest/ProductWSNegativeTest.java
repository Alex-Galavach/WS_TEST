package restWSTest;

import junit.framework.TestCase;
import org.apache.http.Header;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import java.util.Arrays;

public class ProductWSNegativeTest extends TestCase {

    public ProductWSNegativeTest(String testName) {
        super(testName);
    }

    public void testProductNotFound() throws IOException {

        int status = Request.Get("http://www.thomas-bayer.com/sqlrest/PRODUCT/555")
                .execute()
                .returnResponse()
                .getStatusLine()
                .getStatusCode();

        System.out.println(status + " = 404");
        assertEquals("returned status", 404, status);
    }

    public void testProductHeadedNotFound() throws IOException {

        Header[] headers = Request.Get("http://www.thomas-bayer.com/sqlrest/PRODUCT/555")
                .execute()
                .returnResponse()
                .getHeaders("Content-Length");

        System.out.println(Arrays.asList(headers));
        String response = headers[0].getValue();
        assertEquals("1003", response);
    }
}