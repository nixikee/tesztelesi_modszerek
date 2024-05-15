package com.jagrosh.jmusicbot.sajat.settings;

import static org.junit.Assert.*;

import com.jagrosh.jmusicbot.settings.RepeatMode;
import org.junit.Test;

public class RepeatModeTest {

    @Test
    public void testGetEmoji() {
        assertEquals(null, RepeatMode.OFF.getEmoji());
        assertEquals("\uD83D\uDD01", RepeatMode.ALL.getEmoji());
        assertEquals("\uD83D\uDD02", RepeatMode.SINGLE.getEmoji());
    }

    @Test
    public void testGetUserFriendlyName() {
        assertEquals("Off", RepeatMode.OFF.getUserFriendlyName());
        assertEquals("All", RepeatMode.ALL.getUserFriendlyName());
        assertEquals("Single", RepeatMode.SINGLE.getUserFriendlyName());
    }

    // Add more tests for other methods and functionalities of RepeatMode enum

}

