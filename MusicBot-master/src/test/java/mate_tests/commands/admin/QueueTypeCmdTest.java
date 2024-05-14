package mate_tests.commands.admin;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.admin.QueueTypeCmd;
import com.jagrosh.jmusicbot.settings.QueueType;
import com.jagrosh.jmusicbot.settings.Settings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.managers.AudioManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QueueTypeCmdTest {

    @Mock
    private CommandEvent mockEvent;

    @Mock
    private Bot mockBot;

    @Mock
    private Settings mockSettings;

    @Mock
    private Guild mockGuild;

    @Mock
    private AudioManager mockAudioManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(mockEvent.getGuild()).thenReturn(mockGuild);
        when(mockEvent.getArgs()).thenReturn("");
        when(mockEvent.getClient()).thenReturn((CommandClient) mockBot);
        when(mockGuild.getAudioManager()).thenReturn(mockAudioManager);
    }

    @Test
    public void testExecuteWithNoArgs() {
        QueueTypeCmd queueTypeCmd = new QueueTypeCmd(mockBot);
        queueTypeCmd.execute(mockEvent);
        verify(mockSettings).getQueueType();
        verify(mockEvent).reply(anyString());
        verifyNoMoreInteractions(mockSettings);
    }

    @Test
    public void testExecuteWithInvalidArgs() {
        when(mockEvent.getArgs()).thenReturn("invalid");
        QueueTypeCmd queueTypeCmd = new QueueTypeCmd(mockBot);
        queueTypeCmd.execute(mockEvent);
        verify(mockEvent).replyError(anyString());
        verifyNoMoreInteractions(mockSettings);
    }

    @Test
    public void testExecuteWithValidArgs() {
        when(mockEvent.getArgs()).thenReturn("LOOP");
        QueueTypeCmd queueTypeCmd = new QueueTypeCmd(mockBot);
        queueTypeCmd.execute(mockEvent);
        verify(mockSettings).setQueueType(QueueType.LINEAR);
        verify(mockEvent).reply(anyString());
        verify(mockGuild).getAudioManager();
        verify(mockAudioManager).getSendingHandler();
        verify(mockGuild, times(2)).getAudioManager(); // Once in constructor, once in execute
        verifyNoMoreInteractions(mockSettings);
        verifyNoMoreInteractions(mockGuild);
    }
}

