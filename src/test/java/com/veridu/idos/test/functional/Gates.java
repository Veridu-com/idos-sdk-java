package com.veridu.idos.test.functional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileGates;
import com.veridu.idos.exceptions.SDKException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Gates extends MainTestSetup {
    private ProfileGates gate;
    private String name = "gate";
    private String slug = "gate-confidence-level";

    @Before
    public void setUp() throws Exception {
        this.gate = factory.getGate();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        // Deletes all gates to avoid create an existing gate
        this.gate.deleteAll(userName);
        response = this.gate.create(userName, name, "confidence_level");
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals("confidence_level", data.get("confidence_level").getAsString());
    }

    @Test
    public void test2DeleteOne() throws SDKException {
        JsonObject json = this.gate.delete(userName, slug);
        assertTrue(json.get("status").getAsBoolean());
    }

    @Test
    public void test3UpsertOne() throws UnsupportedEncodingException, SDKException {
        response = this.gate.upsert(userName, name, "high");
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals("high", data.get("confidence_level").getAsString());
    }

    @Test
    public void test4GetOne() throws SDKException {
        response = this.gate.getOne(userName, slug);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertEquals(name, data.get("name").getAsString());
    }

    @Test
    public void test5UpdateOne() throws SDKException, UnsupportedEncodingException {
        response = this.gate.update(userName, slug, "medium");
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals("medium", data.get("confidence_level").getAsString());
    }

    @Test
    public void test6ListAll() throws SDKException {
        JsonObject json = this.gate.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("confidence_level"));
    }

    @Test
    public void test7DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first gate
        response = this.gate.upsert(userName, name, "medium");
        assertTrue(response.get("status").getAsBoolean());

        // create second gate
        response = this.gate.upsert(userName, "another-one", "high");
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
