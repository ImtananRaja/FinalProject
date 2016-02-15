package com.imtanan.finalproject;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class RequestHandlerTest {
    @Test
    public void shouldConvertHashMapOfURIsToSingleString() throws UnsupportedEncodingException {
        HashMap<String, String> input = new HashMap<>();
        input.put("key1", "value1");
        input.put("key2", "value2");
        input.put("key3", "value3");

        String expectedString = "key1=value1&key2=value2&key3=value3";

        assertEquals(new RequestHandler().getPostDataString(input), expectedString);
    }
}