package com.veridu.idos.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SSO extends MainTestSetup {
    private com.veridu.idos.endpoints.SSO sso;

    @Before
    public void setUp() throws Exception {
        this.sso = factory.getSSO();
    }

    @Test
    public void test1ListAll() throws SDKException {
        response = this.sso.listAll();
        JsonArray array = response.get("data").getAsJsonArray();
        assertEquals("[\"amazon\",\"facebook\",\"google\",\"linkedin\",\"paypal\",\"twitter\"]", array.toString());
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.sso.getOne("facebook");
        JsonObject data = this.getResponseData(response);
        assertTrue(data.has("enabled"));
    }

    @Test
    public void test3GetOne() throws SDKException {
        response = this.sso.getOne("twitter");
        JsonObject data = this.getResponseData(response);
        assertTrue(data.has("enabled"));
    }
}
