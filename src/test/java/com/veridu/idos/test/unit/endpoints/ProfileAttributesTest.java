package com.veridu.idos.test.unit.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.endpoints.ProfileAttributes;
import com.veridu.idos.settings.Config;
import com.veridu.idos.test.unit.AbstractUnit;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.IdOSUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ProfileAttributes.class, AbstractEndpoint.class, Request.class, Response.class, IdOSUtils.class,
        HttpClients.class, Executor.class, EntityUtils.class, HttpClient.class, HttpResponse.class, HttpEntity.class})
public class ProfileAttributesTest extends AbstractUnit {
    /**
     * ProfileAttributes instance
     */
    ProfileAttributes attributes;
    HashMap<String, String> credentials = new HashMap<>();

    @Before
    public void setUp() {
        this.credentials = this.getCredentials();
    }

    @Test
    public void testConstructor() {
        ProfileAttributes attributes = new ProfileAttributes(this.credentials, Config.BASE_URL, false);
        assertThat(attributes, isA(ProfileAttributes.class));
    }

    @Test
    public void testListAll() throws Exception {
        ProfileAttributes attributesMock = Mockito.mock(ProfileAttributes.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(IdOSUtils.class);
        PowerMockito.mockStatic(HttpClients.class);
        attributesMock.setCredentials(this.credentials);
        attributesMock.setAuthType(IdOSAuthType.HANDLER);
        attributesMock.setBaseURL("https://idos.api.com");
        attributesMock.setDoNotCheckSSLCertificate(false);
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        PowerMockito.mockStatic(Request.class);
        PowerMockito.mockStatic(Response.class);
        PowerMockito.mockStatic(Executor.class);
        PowerMockito.mockStatic(EntityUtils.class);
        Request request = Mockito.mock(Request.class);
        Response response = Mockito.mock(Response.class);
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        Executor executor = Mockito.mock(Executor.class);
        when(Request.Get(anyString())).thenReturn(request);
        when(Executor.newInstance(any())).thenReturn(executor);
        doReturn(response).when(executor).execute(any());
        when(request.setHeader(anyString(), anyString())).thenReturn(request);
        when(response.returnResponse()).thenReturn(httpResponse);

        HttpEntity entity = Mockito.mock(HttpEntity.class);
        when(httpResponse.getEntity()).thenReturn(entity);
        EntityUtils entityUtils = mock(EntityUtils.class);
        when(entityUtils.toString(any())).thenReturn("{\"status\":true}");
        when(IdOSUtils.generateToken(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn("token");
        assertEquals(json, attributesMock.listAll("username"));
    }
}
