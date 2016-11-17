package com.veridu.idos.endpoints.functional;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileProcesses;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Processes extends MainTestSetup {
    private ProfileProcesses process;
    private int processId;

    @Before
    public void setUp() throws Exception {
        this.process = factory.getProcess();
        JsonObject json = this.process.listAll(userName);
        if (json.get("data").getAsJsonArray().size() > 0)
            this.processId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
    }

    @Test
    public void test1ListAll() throws SDKException {
        JsonObject json = this.process.listAll(userName);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("event"));
        assertTrue(data.has("id"));
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.process.getOne(userName, this.processId);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertTrue(data.has("name"));
        assertTrue(data.has("event"));
        assertTrue(data.has("id"));
    }

}
