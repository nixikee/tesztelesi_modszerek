package com.jagrosh.jmusicbot.sajat.settings;

import static org.junit.Assert.*;

import com.jagrosh.jmusicbot.settings.QueueType;
import com.jagrosh.jmusicbot.settings.RepeatMode;
import com.jagrosh.jmusicbot.settings.Settings;
import com.jagrosh.jmusicbot.settings.SettingsManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashMap;
import net.dv8tion.jda.api.entities.Guild;

public class SettingsManagerTest {

    private SettingsManager settingsManager;

    @Mock
    private Guild mockGuild;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        settingsManager = new SettingsManager();
    }

    @Test
    public void testGetSettings_GuildNotNull() {
        assertNotNull(settingsManager.getSettings(mockGuild));
    }

    @Test
    public void testGetSettings_GuildNull() {
        assertNotNull(settingsManager.getSettings(1234567890L));
    }

    @Test
    public void testCreateDefaultSettings() {
        Settings defaultSettings = settingsManager.createDefaultSettings();
        assertNotNull(defaultSettings);
        assertEquals(0, defaultSettings.textId);
        assertEquals(0, defaultSettings.voiceId);
        assertEquals(0, defaultSettings.roleId);
        assertEquals(100, defaultSettings.getVolume());
        assertNull(defaultSettings.getDefaultPlaylist());
        assertEquals(RepeatMode.OFF, defaultSettings.getRepeatMode());
        assertNull(defaultSettings.getPrefix());
        assertEquals(-1, defaultSettings.getSkipRatio(), 0.01);
        assertEquals(QueueType.FAIR, defaultSettings.getQueueType());
    }

    @Test
    public void testGetSettings_CachedSettings() {
        // Create a mock settings object
        Settings mockSettings = new Settings(null, 123, 456, 789, 50, "playlist", RepeatMode.ALL, "!", 0.5, QueueType.LINEAR);

        // Inject the mock settings into the settings manager's cache
        settingsManager.settings.put(123L, mockSettings);

        // Ensure that the settings manager returns the cached settings object
        assertEquals(mockSettings, settingsManager.getSettings(123L));
    }

    @Test
    public void testGetSettings_DefaultSettings() {
        // Ensure that the settings manager returns a new settings object when no cached settings are available
        assertNotNull(settingsManager.getSettings(1234567890L));
    }

    @Test
    public void testWriteSettings() {
        // Create a new settings object
        Settings settings = new Settings(null, 123, 456, 789, 50, "playlist", RepeatMode.ALL, "!", 0.5, QueueType.LINEAR);

        // Inject the settings into the settings manager's cache
        settingsManager.settings.put(123L, settings);

        // Write settings to file
        settingsManager.writeSettings();

        // Retrieve settings from file
        Settings retrievedSettings = settingsManager.getSettings(123L);

        // Ensure that retrieved settings match the original settings
        assertEquals(settings.textId, retrievedSettings.textId);
        assertEquals(settings.voiceId, retrievedSettings.voiceId);
        assertEquals(settings.roleId, retrievedSettings.roleId);
        assertEquals(settings.getVolume(), retrievedSettings.getVolume());
        assertEquals(settings.getDefaultPlaylist(), retrievedSettings.getDefaultPlaylist());
        assertEquals(settings.getRepeatMode(), retrievedSettings.getRepeatMode());
        assertEquals(settings.getPrefix(), retrievedSettings.getPrefix());
        assertEquals(settings.getSkipRatio(), retrievedSettings.getSkipRatio(), 0.01);
        assertEquals(settings.getQueueType(), retrievedSettings.getQueueType());
    }

    // Add more tests to cover other functionalities of SettingsManager

}
