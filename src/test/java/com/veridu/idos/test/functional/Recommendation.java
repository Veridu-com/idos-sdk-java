package com.veridu.idos.test.functional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileGates;
import com.veridu.idos.endpoints.ProfileRecommendation;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by caue on 10/12/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Recommendation extends MainTestSetup {
    private ProfileRecommendation recommendation;

    @Before
    public void setUp() throws Exception {
        this.recommendation= factory.getRecommendation();
    }
    @Test
    public void test1UpsertOne() throws UnsupportedEncodingException, SDKException {
        JsonArray passed = new JsonArray();
        JsonArray failed = new JsonArray();
        response = this.recommendation.upsert(this.userName, "result", passed, failed);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals("result", data.get("result").getAsString());
        assertEquals(0, data.get("passed").getAsJsonArray().size());
        assertEquals(0, data.get("failed").getAsJsonArray().size());
    }

    @Test
    public void test2GetOne() throws SDKException {
        this.recommendation.setAuthType(IdOSAuthType.USER);
        response = this.recommendation.getOne(userName);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertTrue(isResponseOk(response));
        assertEquals("result", data.get("result").getAsString());
        assertEquals(0, data.get("passed").getAsJsonArray().size());
        assertEquals(0, data.get("failed").getAsJsonArray().size());
    }
}
