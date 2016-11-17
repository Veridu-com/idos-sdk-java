package com.veridu.idos.endpoints.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileScores;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Scores extends MainTestSetup {
    private ProfileScores score;
    private String name = "Testing";
    private String attributeName = "firstName";
    private float value = 0.6f;

    @Before
    public void setUp() throws Exception {
        this.score = factory.getScore();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.score.deleteAll(userName);
        if (response.get("status").getAsBoolean()) {
            response = this.score.create(userName, attributeName, name, value);
            JsonObject data = getResponseData(response);
            assertTrue(isResponseOk(response));
            assertEquals(attributeName, data.get("name").getAsString());
            assertEquals(value, data.get("value").getAsFloat(), 1e-3);
        }
    }

    @Test
    public void test2DeleteOne() throws SDKException {
        JsonObject json = this.score.delete(userName, attributeName);
        assertTrue(json.get("status").getAsBoolean());
    }

    @Test
    public void test3UpsertOne() throws UnsupportedEncodingException, SDKException {
        response = this.score.upsert(userName, attributeName, name, value);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(attributeName, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsFloat(), 1e-3);
    }

    @Test
    public void test4GetOne() throws SDKException {
        response = this.score.getOne(userName, this.attributeName);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertEquals(attributeName, data.get("name").getAsString());
        assertEquals(name, data.get("attribute").getAsString());
        assertEquals(value, data.get("value").getAsFloat(), 1e-3);
    }

    @Test
    public void test5ListAll() throws SDKException {
        JsonObject json = this.score.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("value"));
        assertTrue(data.has("attribute"));
    }

    @Test
    public void test6DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first att
        response = this.score.upsert(userName, attributeName, name, value);
        assertTrue(response.get("status").getAsBoolean());

        // create second att
        response = this.score.upsert(userName, "attributeName", "other-name", 0.6f);
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.score.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.score.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
    }

}
