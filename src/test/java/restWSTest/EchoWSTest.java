package restWSTest;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.apache.http.Header;
import org.apache.http.client.fluent.Request;
import restWS.Echo;

import java.io.IOException;
import java.util.Arrays;

public class EchoWSTest extends TestCase {

    public EchoWSTest (String testName) {
        super(testName);
    }

    public void testEchoStatusCode() throws IOException {

        int status = Request.Get("http://echo.jsontest.com/key/value/one/two")
                .execute()
                .returnResponse()
                .getStatusLine()
                .getStatusCode();

        System.out.println(status + " = 200");
        assertEquals("returned status", 200, status);
    }

    public void testEchoHeader() throws IOException {

        Header[] headers = Request.Get("http://echo.jsontest.com/key/value/one/two")
                .execute()
                .returnResponse()
                .getHeaders("Content-Type");

        System.out.println(Arrays.asList(headers));
        String server = headers[0].getValue();
        assertEquals("application/json; charset=ISO-8859-1", server);
    }

    public void testEchoContent() throws IOException {

        String json = Request.Get("http://echo.jsontest.com/key/value/one/two")
                .execute()
                .returnContent()
                .asString();

        Gson gson = new Gson();
        Echo echo = gson.fromJson(json, Echo.class);
        System.out.println(echo.getOne() + " = two");
        assertEquals("two", echo.getOne());

    }
}
