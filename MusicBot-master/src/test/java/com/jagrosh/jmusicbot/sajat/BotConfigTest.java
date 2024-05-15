package com.jagrosh.jmusicbot.sajat;

import com.jagrosh.jmusicbot.entities.Prompt;
import com.jagrosh.jmusicbot.BotConfig;
import com.typesafe.config.Config;
import net.dv8tion.jda.api.OnlineStatus;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BotConfigTest {

    private BotConfig botConfig;
    private Prompt prompt;
    private Config config;

    @Before
    public void setUp() {
        prompt = mock(Prompt.class);
        botConfig = new BotConfig(prompt);
        config = mock(Config.class);
    }

    @Test
    public void testGetStatus() {
        OnlineStatus status = OnlineStatus.ONLINE;
        when(config.getString("status")).thenReturn("ONLINE");
        botConfig.load();
        assertEquals(status, botConfig.getStatus());
    }

    @Test
    public void testGetHelp() {
        when(config.getString("help")).thenReturn("help");
        botConfig.load();
        assertEquals("help", botConfig.getHelp());
    }

    @Test
    public void testUseUpdateAlerts() {
        when(config.getBoolean("updatealerts")).thenReturn(true);
        botConfig.load();
        assertTrue(botConfig.useUpdateAlerts());
    }
}

