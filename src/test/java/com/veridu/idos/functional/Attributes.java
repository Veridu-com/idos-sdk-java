package com.veridu.idos.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import com.veridu.idos.endpoints.ProfileCandidates;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Attributes extends MainTestSetup {
    private ProfileCandidates attribute;
    private String name = "attribute-test";
    private String value = "WeirdAttributesáéó!@$%£values";
    private float support = 0.5f;

    @Before
    public void setUp() throws Exception {
        this.attribute = factory.getCandidates();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.attribute.create(userName, name, value, support);
        JsonObject data = getResponseData(response);
        
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
        assertEquals(support, data.get("support").getAsFloat(), 1e-10);
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.attribute.getOne(userName, name);
        JsonObject data = response.get("data").getAsJsonArray().getAsJsonArray().get(0).getAsJsonObject();
        
        assertTrue(response.get("status").getAsBoolean());
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
    }

    @Test
    public void test3ListAll() throws SDKException {
        JsonObject json = this.attribute.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("value"));
        assertTrue(data.has("support"));
    }

    @Test
    public void test4DeleteOne() throws SDKException {
        JsonObject json = this.attribute.delete(userName, name);
        
        assertTrue(json.get("status").getAsBoolean());
        assertEquals(1, json.get("deleted").getAsInt());
    }

    @Test
    public void test5DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first att
        response = this.attribute.create(userName, name, value, support);
        assertTrue(response.get("status").getAsBoolean());

        // create second att
        response = this.attribute.create(userName, "another-one", "123+-@áéã", 0.3);
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.attribute.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.attribute.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
        json = this.attribute.listAll(userName);
        assertTrue(json.get("data").getAsJsonArray().size() == 0);

    }
}
