package com.veridu.idos.test.functional;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Profiles extends MainTestSetup {
    private com.veridu.idos.endpoints.Profiles profile;

    @Before
    public void setUp() throws Exception {
        this.profile = factory.getProfile();
    }

    @Test
    public void test1ListAll() throws SDKException {
        JsonObject json = this.profile.listAll();
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("username"));
        assertTrue(data.has("credential"));
        assertTrue(data.has("id"));
    }

}
