package com.veridu.idos.test.unit.endpoints;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.endpoints.SSO;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.test.unit.AbstractUnit;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.IdOSUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SSO.class, AbstractEndpoint.class, Request.class, Response.class, IdOSUtils.class, Content.class })
public class SSOTest extends AbstractUnit {

    HashMap<String, String> credentials;

    @Before
    public void setUp() {
        this.credentials = this.getCredentials();
    }

    @Test
    public void testListAll() throws ClientProtocolException, IOException, SDKException {
        SSO ssoMock = Mockito.mock(SSO.class, Mockito.CALLS_REAL_METHODS);
        ssoMock.setCredentials(this.credentials);
        PowerMockito.mockStatic(IdOSUtils.class);
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        ssoMock.setAuthType(IdOSAuthType.NONE);
        PowerMockito.mockStatic(Request.class);
        PowerMockito.mockStatic(Response.class);
        PowerMockito.mockStatic(Content.class);
        Request request = Mockito.mock(Request.class);
        Response response = Mockito.mock(Response.class);
        Content content = Mockito.mock(Content.class);
        when(Request.Get(anyString())).thenReturn(request);
        when(request.setHeader(anyString(), anyString())).thenReturn(request);
        PowerMockito.doReturn(response).when(request).execute();
        PowerMockito.doReturn(content).when(response).returnContent();
        when(content.toString()).thenReturn("{\"status\":true}");
        when(IdOSUtils.generateHandlerToken(this.credentials.get("servicePrivateKey"),
                this.credentials.get("servicePublicKey"), this.credentials.get("credentialPublicKey")))
                        .thenReturn("token");
        assertEquals(json, ssoMock.listAll());

    }

    @Test
    public void testCreate() throws ClientProtocolException, IOException, SDKException {
        SSO ssoMock = Mockito.mock(SSO.class, Mockito.CALLS_REAL_METHODS);
        ssoMock.setCredentials(this.credentials);
        PowerMockito.mockStatic(IdOSUtils.class);
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        ssoMock.setAuthType(IdOSAuthType.NONE);
        PowerMockito.mockStatic(Request.class);
        PowerMockito.mockStatic(Response.class);
        PowerMockito.mockStatic(Content.class);
        Request request = Mockito.mock(Request.class);
        Response response = Mockito.mock(Response.class);
        Content content = PowerMockito.mock(Content.class);
        when(Request.Post(anyString())).thenReturn(request);
        when(request.setHeader(anyString(), anyString())).thenReturn(request);
        when(request.bodyByteArray(Matchers.anyObject(), Matchers.anyObject())).thenReturn(request);
        PowerMockito.doReturn(response).when(request).execute();
        PowerMockito.doReturn(content).when(response).returnContent();
        when(content.toString()).thenReturn("{\"status\":true}");
        when(IdOSUtils.generateHandlerToken(this.credentials.get("servicePrivateKey"),
                this.credentials.get("servicePublicKey"), this.credentials.get("credentialPublicKey")))
                        .thenReturn("token");
        assertEquals(json, ssoMock.create("userName", "facebook", "credentialToken", "tokenSecret"));
    }

    @Test
    public void testGetOne() throws ClientProtocolException, IOException, SDKException {
        SSO ssoMock = Mockito.mock(SSO.class, Mockito.CALLS_REAL_METHODS);
        ssoMock.setCredentials(this.credentials);
        PowerMockito.mockStatic(IdOSUtils.class);
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        ssoMock.setAuthType(IdOSAuthType.NONE);
        PowerMockito.mockStatic(Request.class);
        PowerMockito.mockStatic(Response.class);
        PowerMockito.mockStatic(Content.class);
        Request request = Mockito.mock(Request.class);
        Response response = Mockito.mock(Response.class);
        Content content = Mockito.mock(Content.class);
        when(Request.Get(anyString())).thenReturn(request);
        when(request.setHeader(anyString(), anyString())).thenReturn(request);
        PowerMockito.doReturn(response).when(request).execute();
        PowerMockito.doReturn(content).when(response).returnContent();
        when(content.toString()).thenReturn("{\"status\":true}");
        when(IdOSUtils.generateHandlerToken(this.credentials.get("servicePrivateKey"),
                this.credentials.get("servicePublicKey"), this.credentials.get("credentialPublicKey")))
                        .thenReturn("token");
        assertEquals(json, ssoMock.getOne("facebook"));
    }
}
