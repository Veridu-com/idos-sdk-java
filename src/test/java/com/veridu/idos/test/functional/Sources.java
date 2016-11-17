package com.veridu.idos.test.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileSources;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sources extends MainTestSetup {
    private ProfileSources source;
    private String name = "sms";
    private HashMap<String, String> tags;
    private int id;

    @Before
    public void setUp() throws Exception {
        this.source = factory.getSource();
        tags = new HashMap<>();
        tags.put("otpcheck", "email");
        tags.put("hey", "eré$%+*/%");
        JsonObject json = this.source.listAll(userName);
        if (json.get("data").getAsJsonArray().size() > 0)
            this.id = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.source.create(userName, name, this.tags);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        JsonObject jsonTags = new JsonObject();
        for (String key : tags.keySet())
            jsonTags.addProperty(key, this.tags.get(key));
        assertEquals(jsonTags, data.get("tags").getAsJsonObject());
        assertTrue(data.has("id"));
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.source.getOne(userName, this.id);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("tags"));
        assertTrue(data.has("id"));
    }

    @Test
    public void test3ListAll() throws SDKException {
        JsonObject json = this.source.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("tags"));
        assertTrue(data.has("id"));
    }

    @Test
    public void test4DeleteOne() throws SDKException {
        JsonObject json = this.source.delete(userName, this.id);
        assertTrue(json.get("status").getAsBoolean());
    }

    @Test
    public void test5DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first source
        response = this.source.create(userName, name, this.tags);
        assertTrue(response.get("status").getAsBoolean());

        // create second source
        this.tags.put("sms-test", "váluèe-+.-*#@");
        response = this.source.create(userName, "sms", this.tags);
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.source.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.source.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
    }
}
