package com.example.knightboard.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonServiceTest {

    private static final String VALID_URL = "https://storage.googleapis.com/jobrapido-backend-test/board.json";
    private static final String INVALID_URL = "INVALIDURL";
    private static final String NON_EXISTING_URL = "https://INVALIDURL.com";

    @Test
    public void whenInvokeFetchJsonWithAValidUrl_ShouldReturnAJson() throws IOException {
        var ret = JsonService.fetchJson(VALID_URL);
        assertNotNull(ret);
    }

    @Test
    public void whenInvokeFetchJsonWithAnInvalidUrl_ShouldThrowMalformedURLException() throws IOException {
        assertThrows(MalformedURLException.class, () -> JsonService.fetchJson(INVALID_URL));
    }

    @Test
    public void whenInvokeFetchJsonWithANonExistingUrl_ShouldThrowUnknownHostException() throws IOException {
        assertThrows(UnknownHostException.class, () -> JsonService.fetchJson(NON_EXISTING_URL));
    }
}
