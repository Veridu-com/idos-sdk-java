package com.veridu.idos.endpoints.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.ProfileTasks;
import com.veridu.idos.exceptions.SDKException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tasks extends MainTestSetup {
    private ProfileTasks tasks;
    private String name = "Testing";
    private String event = "testing-event";
    private boolean running = false;
    private boolean success = true;
    private int processId;
    private int taskId;
    private String message = "this is a weird message [ejiq389+.*-3";

    @Before
    public void setUp() throws Exception {
        this.tasks = factory.getTask();
        JsonObject json = factory.getProcess().listAll(userName);
        if (json.get("data").getAsJsonArray().size() > 0)
            this.processId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
        json = this.tasks.listAll(userName, processId);
        if (json.get("data").getAsJsonArray().size() > 0)
            this.taskId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
    }

    @Test
    public void test1CreateNew() throws UnsupportedEncodingException, SDKException {
        response = this.tasks.create(userName, this.processId, name, event, running, success, message);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals(event, data.get("event").getAsString());
        assertEquals(running, data.get("running").getAsBoolean());
        assertEquals(success, data.get("success").getAsBoolean());
        assertTrue(data.has("id"));
    }

    @Test
    public void test1CreateNewWithoutMessageAndSuccess() throws UnsupportedEncodingException, SDKException {
        response = this.tasks.create(userName, this.processId, name, event, running);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertEquals(name, data.get("name").getAsString());
        assertEquals(event, data.get("event").getAsString());
        assertEquals(running, data.get("running").getAsBoolean());
        assertTrue(data.has("id"));
    }

    @Test
    public void test2GetOne() throws SDKException {
        response = this.tasks.getOne(userName, this.processId, this.taskId);
        assertTrue(response.get("status").getAsBoolean());
        JsonObject data = response.get("data").getAsJsonObject();
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("event"));
        assertTrue(data.has("running"));
        assertTrue(data.has("success"));
    }

    @Test
    public void test3ListAll() throws SDKException {
        JsonObject json = this.tasks.listAll(userName, this.processId);
        JsonArray array = json.get("data").getAsJsonArray();
        JsonObject data = array.get(0).getAsJsonObject();
        assertTrue(json.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("event"));
        assertTrue(data.has("running"));
        assertTrue(data.has("success"));
    }

    @Test
    public void test4Update() throws UnsupportedEncodingException, SDKException {
        response = this.tasks.update(userName, this.processId, this.taskId, running, true);
        JsonObject data = getResponseData(response);
        assertTrue(isResponseOk(response));
        assertTrue(response.get("status").getAsBoolean());
        assertTrue(data.has("name"));
        assertTrue(data.has("event"));
        assertTrue(data.has("running"));
        assertTrue(data.has("success"));
        assertEquals(running, data.get("running").getAsBoolean());
        assertEquals(true, data.get("success").getAsBoolean());
        assertTrue(data.has("id"));
    }
}
