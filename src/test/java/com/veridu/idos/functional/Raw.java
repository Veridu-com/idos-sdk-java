package com.veridu.idos.functional;

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
import com.veridu.idos.endpoints.ProfileRaw;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Raw extends MainTestSetup {
    private ProfileRaw raw;
    private int sourceId;

    @Before
    public void setUp() throws Exception {
        this.raw = factory.getRaw();
        JsonObject json = factory.getSource().listAll(userName);
        this.sourceId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("value", "value-test");
        
        response = this.raw.create(userName, this.sourceId, "collection-name-1", hashmap);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals("collection-name-1", data.get("collection").getAsString());
        assertEquals("value-test", data.get("data").getAsJsonObject().get("value").getAsString());
    }

     @Test
     public void test2Upsert() throws SDKException {
	     HashMap<String, String> hashmap = new HashMap<>();
	     hashmap.put("value", "value-test");
	     
	     response = this.raw.upsert(userName, this.sourceId, "collection-name-2", hashmap);
	     JsonObject data = getResponseData(response);
	     assertTrue(isResponseOk(response));
	     assertEquals("collection-name-2", data.get("collection").getAsString());
	     assertEquals("value-test", data.get("data").getAsJsonObject().get("value").getAsString());
     }

    @Test
    public void test3ListAll() throws SDKException {
        JsonObject json = this.raw.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("source"));
        assertTrue(data.has("collection"));
        assertTrue(data.has("data"));
    }
}
