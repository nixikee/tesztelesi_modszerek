package com.jagrosh.jmusicbot.sajat.playlist;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.jagrosh.jmusicbot.BotConfig;
import com.jagrosh.jmusicbot.entities.Prompt;
import com.jagrosh.jmusicbot.playlist.PlaylistLoader;
import org.junit.Before;
import org.junit.Test;

public class PlaylistLoaderTest {

    private static final String PLAYLISTS_FOLDER = "test_playlists";
    private PlaylistLoader playlistLoader;

    @Before
    public void setUp() {
        playlistLoader = new PlaylistLoader(new BotConfig(new Prompt(PLAYLISTS_FOLDER) ));
    }

    @Test
    public void testCreateFolder() {
        try{
        assertFalse(Files.exists(Paths.get(PLAYLISTS_FOLDER)));
        playlistLoader.createFolder();
        assertTrue(Files.exists(Paths.get(PLAYLISTS_FOLDER)));
    } catch (Exception e) {
            throw new RuntimeException(e);
        }}

        @Test
    public void testGetPlaylistNames() throws IOException {
        try{
        Files.createDirectories(Paths.get(PLAYLISTS_FOLDER));
        Files.createFile(Paths.get(PLAYLISTS_FOLDER, "playlist1.txt"));
        Files.createFile(Paths.get(PLAYLISTS_FOLDER, "playlist2.txt"));

        List<String> playlistNames = playlistLoader.getPlaylistNames();
        assertEquals(2, playlistNames.size());
        assertTrue(playlistNames.contains("playlist1"));
        assertTrue(playlistNames.contains("playlist2"));
    } catch (IOException e) {
            throw new RuntimeException(e);
        }}

            // Add more tests for other methods as needed

}

