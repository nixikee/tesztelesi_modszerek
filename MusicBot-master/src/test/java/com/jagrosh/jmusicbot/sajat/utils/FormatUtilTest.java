package com.jagrosh.jmusicbot.sajat.utils;

import com.jagrosh.jmusicbot.utils.FormatUtil;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FormatUtilTest {

    @Test
    public void testFormatUsernameWithDiscriminator() {
        String result = FormatUtil.formatUsername("user", "1234");
        assertEquals("user#1234", result);
    }

    @Test
    public void testFormatUsernameWithoutDiscriminator() {
        String result = FormatUtil.formatUsername("user", "0000");
        assertEquals("user", result);
    }

    @Test
    public void testFormatUsernameWithUser() {
        User user = mock(User.class);
        when(user.getName()).thenReturn("user");
        when(user.getDiscriminator()).thenReturn("1234");
        String result = FormatUtil.formatUsername(user);
        assertEquals("user#1234", result);
    }

    @Test
    public void testProgressBar() {
        String result = FormatUtil.progressBar(0.5);
        assertEquals("▬▬▬▬▬▬🔘▬▬▬▬▬", result);
    }

    @Test
    public void testVolumeIconMute() {
        String result = FormatUtil.volumeIcon(0);
        assertEquals("\uD83D\uDD07", result); // 🔇
    }

    @Test
    public void testVolumeIconLow() {
        String result = FormatUtil.volumeIcon(10);
        assertEquals("\uD83D\uDD08", result); // 🔈
    }

    @Test
    public void testVolumeIconMedium() {
        String result = FormatUtil.volumeIcon(50);
        assertEquals("\uD83D\uDD09", result); // 🔉
    }

    @Test
    public void testVolumeIconHigh() {
        String result = FormatUtil.volumeIcon(80);
        assertEquals("\uD83D\uDD0A", result); // 🔊
    }

    @Test
    public void testListOfTChannels() {
        TextChannel channel1 = mock(TextChannel.class);
        TextChannel channel2 = mock(TextChannel.class);
        when(channel1.getName()).thenReturn("channel1");
        when(channel1.getId()).thenReturn("1");
        when(channel2.getName()).thenReturn("channel2");
        when(channel2.getId()).thenReturn("2");

        List<TextChannel> list = Arrays.asList(channel1, channel2);
        String result = FormatUtil.listOfTChannels(list, "test");
        assertEquals(" Multiple text channels found matching \"test\":\n - channel1 (<#1>)\n - channel2 (<#2>)", result);
    }

    @Test
    public void testListOfVChannels() {
        VoiceChannel channel1 = mock(VoiceChannel.class);
        VoiceChannel channel2 = mock(VoiceChannel.class);
        when(channel1.getAsMention()).thenReturn("@voice1");
        when(channel1.getId()).thenReturn("1");
        when(channel2.getAsMention()).thenReturn("@voice2");
        when(channel2.getId()).thenReturn("2");

        List<VoiceChannel> list = Arrays.asList(channel1, channel2);
        String result = FormatUtil.listOfVChannels(list, "test");
        assertEquals(" Multiple voice channels found matching \"test\":\n - @voice1 (ID:1)\n - @voice2 (ID:2)", result);
    }

    @Test
    public void testListOfRoles() {
        Role role1 = mock(Role.class);
        Role role2 = mock(Role.class);
        when(role1.getName()).thenReturn("role1");
        when(role1.getId()).thenReturn("1");
        when(role2.getName()).thenReturn("role2");
        when(role2.getId()).thenReturn("2");

        List<Role> list = Arrays.asList(role1, role2);
        String result = FormatUtil.listOfRoles(list, "test");
        assertEquals(" Multiple text channels found matching \"test\":\n - role1 (ID:1)\n - role2 (ID:2)", result);
    }

    @Test
    public void testFilter() {
        String input = "@everyone @here \u202E";
        String result = FormatUtil.filter(input);
        assertEquals("@еveryone @hеre", result);
    }
}

