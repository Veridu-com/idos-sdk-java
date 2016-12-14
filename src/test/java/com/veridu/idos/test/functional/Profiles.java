package com.veridu.idos.test.functional;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Profiles extends MainTestSetup {
    private com.veridu.idos.endpoints.Profiles profile;

    @Before
    public void setUp() throws Exception {
        this.profile = factory.getProfile();
    }

    @Test
    public void test1GetOne() throws SDKException {
        response = this.profile.getOne(userName);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertTrue(data.has("attributes"));
        assertTrue(data.has("candidates"));
        assertTrue(data.has("scores"));
        assertTrue(data.has("gates"));
        assertTrue(data.has("sources"));
    }
}
