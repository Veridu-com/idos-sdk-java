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
import com.veridu.idos.endpoints.ProfileReferences;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class References extends MainTestSetup {
    private ProfileReferences reference;
    private String name = "name-test";
    private String value = "WeirdValuesáéó!@$%£values";

    @Before
    public void setUp() throws Exception {
        this.reference = factory.getReference();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.reference.create(userName, name, value);
        JsonObject data = getResponseData(response);
        System.out.println(data);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.reference.getOne(userName, name.toLowerCase());
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertEquals(name, data.get("name").getAsString());
        assertEquals(value, data.get("value").getAsString());
    }

    @Test
    public void test3ListAll() throws SDKException {
        JsonObject json = this.reference.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("value"));
    }

    @Test
    public void test4DeleteOne() throws SDKException {
        JsonObject json = this.reference.delete(userName, name);
        assertTrue(json.get("status").getAsBoolean());
    }

    @Test
    public void test5DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first att
        response = this.reference.create(userName, name, value);
        assertTrue(response.get("status").getAsBoolean());

        // create second att
        response = this.reference.create(userName, "another-one", "123+-@áéã");
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.reference.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.reference.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
    }

}
