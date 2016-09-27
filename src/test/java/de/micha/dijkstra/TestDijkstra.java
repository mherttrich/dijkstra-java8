package de.micha.dijkstra;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by micha on 06.08.16.
 */
public class TestDijkstra {

    @Test
    public void testExcute() throws IOException {
        PublicTransportRouting routing = new PublicTransportRouting();
        assertEquals("A -> C -> B: 130", routing.route("A","B"));
        assertEquals("C: 70, D: 120, B: 130",routing.nearby("A", 130));
    }
}
