package com.jagrosh.jmusicbot.sajat;

import org.junit.Test;
import static org.junit.Assert.*;
import com.jagrosh.jmusicbot.entities.Pair;
public class PairUnitTest {

    @Test
    public void testConstructorAndGetters() {
        String key = "testKey";
        Integer value = 123;
        Pair<String, Integer> pair = new Pair<>(key, value);
        assertEquals(key, pair.getKey());
        assertEquals(value, pair.getValue());
    }

    @Test
    public void testGetKey() {
        String key = "testKey";
        Integer value = 123;
        Pair<String, Integer> pair = new Pair<>(key, value);
        assertEquals(key, pair.getKey());
    }

    @Test
    public void testGetValue() {
        String key = "testKey";
        Integer value = 123;
        Pair<String, Integer> pair = new Pair<>(key, value);
        assertEquals(value, pair.getValue());
    }
}

