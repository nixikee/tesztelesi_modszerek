package com.jagrosh.jmusicbot.sajat.settings;

import static org.junit.Assert.*;

import com.jagrosh.jmusicbot.settings.QueueType;
import com.jagrosh.jmusicbot.settings.RepeatMode;
import com.jagrosh.jmusicbot.settings.Settings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import java.util.Collection;
import java.util.Collections;

public class SettingsTest {

    @Mock
    private Guild mockGuild;

    @Mock
    private TextChannel mockTextChannel;

    @Mock
    private VoiceChannel mockVoiceChannel;

    @Mock
    private Role mockRole;

    private Settings settings;
    private final long TEXT_ID = 123456789L;
    private final long VOICE_ID = 987654321L;
    private final long ROLE_ID = 135792468L;
    private final int VOLUME = 50;
    private final String DEFAULT_PLAYLIST = "default";
    private final RepeatMode REPEAT_MODE = RepeatMode.ALL;
    private final String PREFIX = "!";
    private final double SKIP_RATIO = 1.5;
    private final QueueType QUEUE_TYPE = QueueType.LINEAR;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        settings = new Settings(null, TEXT_ID, VOICE_ID, ROLE_ID, VOLUME, DEFAULT_PLAYLIST, REPEAT_MODE, PREFIX, SKIP_RATIO, QUEUE_TYPE);
    }

    @Test
    public void testGetTextChannel() {
        assertEquals(null, settings.getTextChannel(null));
        assertEquals(mockTextChannel, settings.getTextChannel(mockGuild));
    }

    @Test
    public void testGetVoiceChannel() {
        assertEquals(null, settings.getVoiceChannel(null));
        assertEquals(mockVoiceChannel, settings.getVoiceChannel(mockGuild));
    }

    @Test
    public void testGetRole() {
        assertEquals(null, settings.getRole(null));
        assertEquals(mockRole, settings.getRole(mockGuild));
    }

    @Test
    public void testGetVolume() {
        assertEquals(VOLUME, settings.getVolume());
    }

    @Test
    public void testGetDefaultPlaylist() {
        assertEquals(DEFAULT_PLAYLIST, settings.getDefaultPlaylist());
    }

    @Test
    public void testGetRepeatMode() {
        assertEquals(REPEAT_MODE, settings.getRepeatMode());
    }

    @Test
    public void testGetPrefix() {
        assertEquals(PREFIX, settings.getPrefix());
    }

    @Test
    public void testGetSkipRatio() {
        assertEquals(SKIP_RATIO, settings.getSkipRatio(), 0.01);
    }

    @Test
    public void testGetQueueType() {
        assertEquals(QUEUE_TYPE, settings.getQueueType());
    }

    @Test
    public void testGetPrefixes() {
        Collection<String> prefixes = settings.getPrefixes();
        assertEquals(1, prefixes.size());
        assertTrue(prefixes.contains(PREFIX));
    }

    // Add more tests for other methods and functionalities of Settings class

}
