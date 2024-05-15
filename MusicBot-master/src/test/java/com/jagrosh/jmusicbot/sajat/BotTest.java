package com.jagrosh.jmusicbot.sajat;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.BotConfig;
import com.jagrosh.jmusicbot.settings.SettingsManager;
import com.jagrosh.jmusicbot.gui.GUI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BotTest {

    private Bot bot;
    private BotConfig config;
    private EventWaiter waiter;
    private SettingsManager settings;
    private JDA jda;
    private GUI gui;

    @Before
    public void setUp() {
        waiter = mock(EventWaiter.class);
        config = mock(BotConfig.class);
        settings = mock(SettingsManager.class);
        jda = mock(JDA.class);
        gui = mock(GUI.class);

        bot = new Bot(waiter, config, settings);
        bot.setJDA(jda);
        bot.setGUI(gui);
    }

    @Test
    public void testGetConfig() {
        assertEquals(config, bot.getConfig());
    }

    @Test
    public void testGetSettingsManager() {
        assertEquals(settings, bot.getSettingsManager());
    }

    @Test
    public void testGetWaiter() {
        assertEquals(waiter, bot.getWaiter());
    }

    @Test
    public void testGetThreadpool() {
        assertNotNull(bot.getThreadpool());
    }

    @Test
    public void testGetPlayerManager() {
        assertNotNull(bot.getPlayerManager());
    }

    @Test
    public void testGetPlaylistLoader() {
        assertNotNull(bot.getPlaylistLoader());
    }

    @Test
    public void testGetNowplayingHandler() {
        assertNotNull(bot.getNowplayingHandler());
    }

    @Test
    public void testGetAloneInVoiceHandler() {
        assertNotNull(bot.getAloneInVoiceHandler());
    }

    @Test
    public void testGetJDA() {
        assertEquals(jda, bot.getJDA());
    }

    @Test
    public void testResetGame() {
        Activity game = Activity.playing("test");
        when(config.getGame()).thenReturn(game);
        when(jda.getPresence()).thenReturn(mock(net.dv8tion.jda.api.managers.Presence.class));

        bot.resetGame();

        verify(jda.getPresence()).setActivity(game);
    }
}

