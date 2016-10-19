package com.veridu.idos.endpoints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractEndpoint.class)
public class AbstractEndpointTest {

    /**
     * Tests if the Constructor has the expected behavior
     *
     * @throws InvalidToken
     */
    @Test
    public void testConstructor() throws InvalidToken {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("credentialPrivateKey", "credentialPrivateKey");
        credentials.put("credentialPublicKey", "credentialPublicKey");
        credentials.put("servicePrivateKey", "servicePrivateKey");
        credentials.put("servicePublicKey", "servicePublicKey");
        ProfileTasks tasks = new ProfileTasks(credentials);
        assertSame("HANDLER", tasks.authType.toString());
    }

    /**
     * Tests the fetch() method with the data parameter empty
     *
     * @throws SDKException
     */
    @Test
    public void testFetchEmptyData() throws SDKException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
        JsonObject json = new JsonObject();
        json.addProperty("response", "response");
        abstractMock.setAuthType(IdOSAuthType.IDENTITY);
        Mockito.when(abstractMock.request("GET", Config.BASE_URL + "/profiles", null, null)).thenReturn(json);
        Mockito.when(abstractMock.fetch("GET", "/profiles", null, null)).thenCallRealMethod();
        assertSame(json, abstractMock.fetch("GET", "/profiles", null, null));
    }

    /**
     * Tests the fetch() method with data parameter and no filter
     *
     * @throws SDKException
     */
    @Test
    public void testFetch() throws SDKException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
        JsonObject json = new JsonObject();
        json.addProperty("data", "data");
        JsonObject response = new JsonObject();
        response.addProperty("response", "response");
        Mockito.when(abstractMock.request("POST", Config.BASE_URL + "/profiles", json, null)).thenReturn(response);
        Mockito.when(abstractMock.fetch("POST", "/profiles", json, null)).thenCallRealMethod();
        assertSame(response, abstractMock.fetch("POST", "/profiles", json, null));
    }

    /**
     * Tests fetch() method with data and filter parameters
     *
     * @throws SDKException
     */
    @Test
    public void testFetchWithFilter() throws SDKException {
        Filter filter = Filter.createFilter();
        filter.addFilterByKeyName("filter", "filter");
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
        JsonObject json = new JsonObject();
        json.addProperty("data", "data");
        JsonObject response = new JsonObject();
        response.addProperty("response", "response");
        Mockito.when(abstractMock.request("POST", Config.BASE_URL + "/profiles?filter=filter", json, filter))
                .thenReturn(response);
        Mockito.when(abstractMock.fetch("POST", "/profiles", json, filter)).thenCallRealMethod();
        assertSame(response, abstractMock.fetch("POST", "/profiles", json, filter));
    }

    /**
     * Tests the transformURL() with the GET method and check if the queries are
     * builded as expected
     *
     * @throws Exception
     */
    @Test
    public void testTransformURLGETMethod() throws Exception {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("credentialPublicKey", "credentialPublicKey");
        credentials.put("creentialPrivateKey", "credentialPrivateKey");

        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(credentials);
        ProfileCandidates attribute = idOSAPIFactory.getAttribute();
        Object[] params = { "GET", "profiles/attributes", null };
        assertEquals(Config.BASE_URL + "/profiles/attributes",
                Whitebox.invokeMethod(attribute, "transformURL", params));
    }

    /**
     * Tests the transformURL() with the POST method and check if the queries
     * are builded as expected
     *
     * @throws Exception
     */
    @Test
    public void testTransformURLPOSTMethod() throws Exception {
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("credentialPublicKey", "credentialPublicKey");
        credentials.put("creentialPrivateKey", "credentialPrivateKey");
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(credentials);
        ProfileCandidates attribute = idOSAPIFactory.getAttribute();
        JsonObject data = new JsonObject();
        data.addProperty("response", "response");
        Filter filter = Filter.createFilter();
        filter.addFilterByKeyName("filter", "filter");
        Object[] params = { "POST", "profiles/attributes", filter };
        assertEquals(Config.BASE_URL + "/profiles/attributes?filter=filter",
                Whitebox.invokeMethod(attribute, "transformURL", params));
    }

    @Test
    public void testConvertToJson() throws Exception {
        AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        assertEquals(json, endpoint.convertToJson("{\"status\":true}"));
    }
}
