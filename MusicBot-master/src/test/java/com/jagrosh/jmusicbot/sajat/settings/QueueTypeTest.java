package com.jagrosh.jmusicbot.sajat.settings;
import static org.junit.Assert.*;
import java.util.List;

import com.jagrosh.jmusicbot.queue.AbstractQueue;
import com.jagrosh.jmusicbot.queue.LinearQueue;
import com.jagrosh.jmusicbot.settings.QueueType;
import org.junit.Test;

    public class QueueTypeTest {

        @Test
        public void testGetNames() {
            List<String> names = QueueType.getNames();
            assertNotNull(names);
            assertEquals(2, names.size());
            assertTrue(names.contains("linear"));
            assertTrue(names.contains("fair"));
        }

        @Test
        public void testCreateInstance() {
            AbstractQueue<?> previousQueue = null; // You can provide a mock or a real previous queue here
            AbstractQueue<?> createdQueue = QueueType.LINEAR.createInstance(previousQueue);
            assertNotNull(createdQueue);
            assertTrue(createdQueue instanceof LinearQueue);
            // Add more assertions based on the behavior of the created queue
        }

        // Add more tests for other methods and functionalities of QueueType enum

    }

