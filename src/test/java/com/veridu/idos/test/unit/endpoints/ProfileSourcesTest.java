package com.veridu.idos.test.unit.endpoints;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.endpoints.ProfileAttributes;
import com.veridu.idos.endpoints.ProfileSources;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.test.unit.AbstractUnit;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.IdOSUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ProfileAttributes.class, AbstractEndpoint.class, Request.class, Response.class, IdOSUtils.class,
        HttpClients.class, Executor.class, EntityUtils.class, HttpClient.class, HttpResponse.class, HttpEntity.class})
public class ProfileSourcesTest extends AbstractUnit {

    HashMap<String, String> credentials;

    @Before
    public void setUp() {
        this.credentials = this.getCredentials();
    }

    @Test
    public void testListAll() throws ClientProtocolException, IOException, SDKException {
        ProfileSources sourcesMock = Mockito.mock(ProfileSources.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(IdOSUtils.class);
        PowerMockito.mockStatic(HttpClients.class);
        sourcesMock.setCredentials(this.credentials);
        sourcesMock.setAuthType(IdOSAuthType.USER);
        sourcesMock.setBaseURL("https://idos.api.com");
        sourcesMock.setDoNotCheckSSLCertificate(false);
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
        assertEquals(json, sourcesMock.listAll("username"));

    }

    @Test
    public void testCreate() throws ClientProtocolException, IOException, SDKException {
        ProfileSources sourcesMock = Mockito.mock(ProfileSources.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(IdOSUtils.class);
        PowerMockito.mockStatic(HttpClients.class);
        sourcesMock.setCredentials(this.credentials);
        sourcesMock.setAuthType(IdOSAuthType.USER);
        sourcesMock.setBaseURL("https://idos.api.com");
        sourcesMock.setDoNotCheckSSLCertificate(false);
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
        when(Request.Post(anyString())).thenReturn(request);
        when(request.bodyByteArray(Matchers.anyObject(), Matchers.anyObject())).thenReturn(request);
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
        assertEquals(json, sourcesMock.create("userName", "email", new HashMap<String, String>()));
    }

    @Test
    public void testGetOne() throws ClientProtocolException, IOException, SDKException {
        ProfileSources sourcesMock = Mockito.mock(ProfileSources.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(IdOSUtils.class);
        PowerMockito.mockStatic(HttpClients.class);
        sourcesMock.setCredentials(this.credentials);
        sourcesMock.setAuthType(IdOSAuthType.USER);
        sourcesMock.setBaseURL("https://idos.api.com");
        sourcesMock.setDoNotCheckSSLCertificate(false);
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
        assertEquals(json, sourcesMock.getOne("userName", 758632156));
    }

    @Test
    public void testUpdate() throws ClientProtocolException, IOException, SDKException {
        ProfileSources sourcesMock = Mockito.mock(ProfileSources.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(IdOSUtils.class);
        PowerMockito.mockStatic(HttpClients.class);
        sourcesMock.setCredentials(this.credentials);
        sourcesMock.setAuthType(IdOSAuthType.USER);
        sourcesMock.setBaseURL("https://idos.api.com");
        sourcesMock.setDoNotCheckSSLCertificate(false);
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
        when(Request.Patch(anyString())).thenReturn(request);
        when(request.bodyByteArray(Matchers.anyObject(), Matchers.anyObject())).thenReturn(request);
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

        HashMap<String, String> tags = new HashMap<>();
        assertEquals(json, sourcesMock.update("userName", 785642136, tags));
    }

    @Test
    public void testDelete() throws ClientProtocolException, IOException, SDKException {
        ProfileSources sourcesMock = Mockito.mock(ProfileSources.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(IdOSUtils.class);
        PowerMockito.mockStatic(HttpClients.class);
        sourcesMock.setCredentials(this.credentials);
        sourcesMock.setAuthType(IdOSAuthType.USER);
        sourcesMock.setBaseURL("https://idos.api.com");
        sourcesMock.setDoNotCheckSSLCertificate(false);
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
        when(Request.Delete(anyString())).thenReturn(request);
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
        assertEquals(json, sourcesMock.delete("userName", 758632156));
    }

    @Test
    public void testDeleteAll() throws ClientProtocolException, IOException, SDKException {
        ProfileSources sourcesMock = Mockito.mock(ProfileSources.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(IdOSUtils.class);
        PowerMockito.mockStatic(HttpClients.class);
        sourcesMock.setCredentials(this.credentials);
        sourcesMock.setAuthType(IdOSAuthType.USER);
        sourcesMock.setBaseURL("https://idos.api.com");
        sourcesMock.setDoNotCheckSSLCertificate(false);
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
        when(Request.Delete(anyString())).thenReturn(request);
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
        assertEquals(json, sourcesMock.deleteAll("userName"));
    }
}
