package com.veridu.idos.functional;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileAttributes;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Attributes extends MainTestSetup {
    private ProfileAttributes attribute;

    @Before
    public void setUp() throws Exception {
        this.attribute = factory.getAttributes();
    }

    @Test
    public void test3ListAll() throws SDKException, UnsupportedEncodingException {
        // Creates a candidate to be listed in the attributes endpoint.
        factory.getCandidates().create(Config.userName, "email", "jhon@jhon.com", 0.9);

        JsonObject json = this.attribute.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("value"));
    }
}
