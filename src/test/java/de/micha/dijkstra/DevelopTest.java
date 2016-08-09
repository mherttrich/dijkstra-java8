package de.micha.dijkstra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by micha on 08.08.16.
 * This is not really a test, it's rather for feeding data in to run dijkstra while developing
 */
public class DevelopTest {


    private final String testData = "8\n" +
            "A -> B: 240\n" +
            "A -> C: 70\n" +
            "A -> D: 120\n" +
            "C -> B: 60\n" +
            "D -> E: 480\n" +
            "C -> E: 240\n" +
            "B -> E: 210\n" +
            "E -> A: 300\n" +
            "route A -> B\n" +
            "nearby A, 130\n";

    private final ByteArrayInputStream inputStream = new ByteArrayInputStream(testData.getBytes());

    //feeding the input data and queries in and expect the route as output
    @Before
    public void setUpStreams() {
        System.setIn(inputStream);
    }

    @After
    public void cleanUpStreams() {
        System.setIn(null);
    }

    @Test
    public void testExcute() throws IOException {
        PublicTransportRouting.main(new String[]{});
    }
}

