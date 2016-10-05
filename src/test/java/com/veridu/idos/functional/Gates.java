package com.veridu.idos.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileGates;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Gates extends MainTestSetup {
    private ProfileGates gate;
    private String name = "gate-test";

    @Before
    public void setUp() throws Exception {
        this.gate = factory.getGate();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.gate.create(userName, name, false);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals("false", data.get("pass").getAsString());
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.gate.getOne(userName, name);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertEquals(name, data.get("name").getAsString());
        assertEquals("false", data.get("pass").getAsString());
    }

    @Test
    public void test3UpdateOne() throws SDKException, UnsupportedEncodingException {
        response = this.gate.update(userName, name, true);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals("true", data.get("pass").getAsString());
    }

    @Test
    public void test4ListAll() throws SDKException {
        JsonObject json = this.gate.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("pass"));
    }

    @Test
    public void test5DeleteOne() throws SDKException {
        JsonObject json = this.gate.delete(userName, name.toLowerCase());
        assertTrue(json.get("status").getAsBoolean());
    }

    @Test
    public void test6DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first attr
        response = this.gate.create(userName, name, false);
        assertTrue(response.get("status").getAsBoolean());

        // create second attr
        response = this.gate.create(userName, "another-one", true);
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.gate.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.gate.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
        json = this.gate.listAll(userName);
        assertTrue(json.get("data").getAsJsonArray().size() == 0);

    }
}
