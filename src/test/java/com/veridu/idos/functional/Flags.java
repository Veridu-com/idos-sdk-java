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
import com.veridu.idos.endpoints.ProfileFlags;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Flags extends MainTestSetup {
    private ProfileFlags flag;
    private String slug = "middle-slug-mismatch";
    private String attribute = "middle-name";

    @Before
    public void setUp() throws Exception {
        this.flag = factory.getFlags();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.flag.create(userName, slug, attribute);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(slug, data.get("slug").getAsString());
        assertEquals(attribute, data.get("attribute").getAsString());
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.flag.getOne(userName, slug);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertEquals(slug, data.get("slug").getAsString());
        assertEquals(attribute, data.get("attribute").getAsString());
    }

    @Test
    public void test3ListAll() throws SDKException {
        JsonObject json = this.flag.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("slug"));
        assertTrue(data.has("attribute"));
    }

    @Test
    public void test4DeleteOne() throws SDKException {
        JsonObject json = this.flag.delete(userName, slug);
        assertTrue(json.get("status").getAsBoolean());
    }

    @Test
    public void test5DeleteAll() throws SDKException, UnsupportedEncodingException {
        // create first flag
        response = this.flag.create(userName, slug, attribute);
        assertTrue(response.get("status").getAsBoolean());

        // create second flag
        response = this.flag.create(userName, "another-one", "another");
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.flag.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.flag.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
        json = this.flag.listAll(userName);
        assertTrue(json.get("data").getAsJsonArray().size() == 0);
    }
}
