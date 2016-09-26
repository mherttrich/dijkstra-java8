package de.micha.dijkstra;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by micha on 08.08.16.
 * This is not really a test, it's rather for feeding data in to run dijkstra while developing
 */
public class DevelopTest {

    @Test
    public void testExcute() throws IOException {
        PublicTransportRouting publicTransportRouting = new PublicTransportRouting();
        System.out.println(publicTransportRouting.route("A","B"));
        System.out.println(publicTransportRouting.nearby("A","130"));
    }
}

