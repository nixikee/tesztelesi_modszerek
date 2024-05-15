package com.jagrosh.jmusicbot.sajat;

import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.BotConfig;
import com.jagrosh.jmusicbot.Listener;
import com.jagrosh.jmusicbot.settings.SettingsManager;
import net.dv8tion.jda.api.events.ShutdownEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ListenerTest {

    private Bot bot;
    private BotConfig config;
    private SettingsManager settings;
    private Listener listener;

    @Before
    public void setup() {
        bot = Mockito.mock(Bot.class);
        config = Mockito.mock(BotConfig.class);
        settings = Mockito.mock(SettingsManager.class);
        listener = new Listener(bot);
    }

    @Test
    public void testOnShutdown() {
        ShutdownEvent event = Mockito.mock(ShutdownEvent.class);
        listener.onShutdown(event);
        verify(bot).shutdown();
    }
}

