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
import com.veridu.idos.endpoints.ProfileFeatures;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Features extends MainTestSetup {
    private ProfileFeatures feature;
    private String name = "Testing";
    private String value = "WeirdAttributesáéó!@$%£values";
    private int id;

    @Before
    public void setUp() throws Exception {
        this.feature = factory.getFeature();
        JsonObject json = this.feature.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        this.id = data.get("id").getAsInt();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.feature.create(userName, name, value);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
        assertEquals("string", data.get("type").getAsString());
        assertTrue(data.has("id"));
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.feature.getOne(userName, this.id);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
        assertEquals("string", data.get("type").getAsString());
        assertEquals("string", data.get("type").getAsString());
    }

    @Test
    public void test3ListAll() throws SDKException {
        JsonObject json = this.feature.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("value"));
        assertTrue(data.has("type"));
    }

    @Test
    public void test4DeleteOne() throws SDKException {
        JsonObject json = this.feature.delete(userName, this.id);
        assertTrue(json.get("status").getAsBoolean());
    }

    @Test
    public void test5DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first att
        response = this.feature.create(userName, name, value);
        assertTrue(response.get("status").getAsBoolean());

        // create second att
        response = this.feature.create(userName, "another-one", "123+-@áéã");
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.feature.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.feature.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
    }

    @Test
    public void test6Upsert() throws SDKException, UnsupportedEncodingException {
        response = this.feature.upsert(userName, name, value);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
        assertEquals("string", data.get("type").getAsString());
        assertTrue(data.has("id"));
        response = this.feature.upsert(userName, name, value);
        data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
        assertEquals("string", data.get("type").getAsString());
        assertTrue(data.has("id"));
    }
}
