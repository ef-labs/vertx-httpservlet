package com.englishtown.vertx.http.impl;

import io.vertx.core.http.HttpServerRequest;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link VertxHttpServletRequest}
 */
public class VertxHttpServletRequestTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private HttpServerRequest request;

    @Test
    public void testParseUri() throws Exception {

        String uri = "http://test.org/test?a=b";

        when(request.absoluteURI()).thenReturn(uri);
        VertxHttpServletRequest vsr = new VertxHttpServletRequest(request);

        verify(request).absoluteURI();

        assertEquals("http", vsr.getScheme());
        assertEquals("test.org", vsr.getServerName());
        assertEquals("/test", vsr.getRequestURI());

    }

    @Test
    public void testParseUri_Bad_Query() throws Exception {

        String scheme = "http";
        String host = "test.org";
        String path = "/test";
        String query = "a=b=1|c=d|e=f&g=h";

        when(request.scheme()).thenReturn(scheme);
        when(request.host()).thenReturn(host);
        when(request.path()).thenReturn(path);
        when(request.query()).thenReturn(query);

        VertxHttpServletRequest vsr = new VertxHttpServletRequest(request);

        verify(request).absoluteURI();

        assertEquals(scheme, vsr.getScheme());
        assertEquals(host, vsr.getServerName());
        assertEquals(path, vsr.getRequestURI());

    }


}