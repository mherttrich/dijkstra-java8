package de.micha.dijkstra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by micha on 06.08.16.
 */
public class TestDijkstra {


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
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    //feeding the input data and queries in and expect the route as output
    @Before
    public void setUpStreams() {
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
    }

    @Test
    public void testExcute() throws IOException {
        PublicTransportRouting.main(new String[]{});
        assertEquals("A -> C -> B: 130\nC: 70, D: 120, B: 130\n", outputStream.toString());
    }
}
