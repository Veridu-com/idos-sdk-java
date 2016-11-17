package com.veridu.idos.test.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileCandidates;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Candidates extends MainTestSetup {
    private ProfileCandidates candidate;
    private String attribute = "attribute";
    private String value = "WeirdAttributesáéó!@$%£values";
    private float support = 0.5f;

    @Before
    public void setUp() throws Exception {
        this.candidate = factory.getCandidate();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.candidate.create(userName, attribute, value, support);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(attribute, data.get("attribute").getAsString());
        assertEquals(value, data.get("value").getAsString());
        assertEquals(support, data.get("support").getAsFloat(), 1e-10);
    }

    @Test
    public void test2ListAll() throws SDKException {
        JsonObject json = this.candidate.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();

        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("attribute"));
        assertTrue(data.has("value"));
        assertTrue(data.has("support"));
    }

    @Test
    public void test3DeleteAll() throws SDKException, UnsupportedEncodingException {

        // create first candidate
        response = this.candidate.create(userName, attribute, value, support);
        assertTrue(response.get("status").getAsBoolean());

        // create second candidate
        response = this.candidate.create(userName, "another-one", "123+-@áéã", 0.3);
        assertTrue(response.get("status").getAsBoolean());

        // test they are there
        response = this.candidate.listAll(userName);
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(response.get("data").getAsJsonArray().size() >= 2);

        // test actual delete all
        JsonObject json = this.candidate.deleteAll(userName);
        assertTrue(isResponseOk(json));
        assertTrue(json.has("deleted"));
        assertTrue(json.get("deleted").getAsInt() >= 2);
        json = this.candidate.listAll(userName);
        assertTrue(json.get("data").getAsJsonArray().size() == 0);

    }
}
