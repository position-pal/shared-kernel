package io.github.positionpal;

import io.github.positionpal.commands.CoMembersPushNotification;
import io.github.positionpal.commands.GroupWisePushNotification;
import io.github.positionpal.entities.GroupId;
import io.github.positionpal.entities.NotificationMessage;
import io.github.positionpal.entities.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestNotificationCommandsSerialization {

    private final AvroSerializer serializer = new AvroSerializer();

    @Test
    void testSerializeAndDeserializeGroupWiseNotifications() {
        final GroupWisePushNotification originalCommand = GroupWisePushNotification.of(
            GroupId.create("group123"),
            UserId.create("testUser"),
            NotificationMessage.create("A sample title", "A sample very very long message")
        );
        assertDoesNotThrow(() -> {
            final byte[] serializedData = serializer.serializeGroupWiseNotification(originalCommand);
            final var deserializedCommand = serializer.deserializeGroupWiseNotification(serializedData);
            assertEquals(originalCommand, deserializedCommand);
        });
    }

    @Test
    void testSerializeAndDeserializeCoMembersNotifications() {
        final var originalCommand = CoMembersPushNotification.of(
            UserId.create("testUser"),
            UserId.create("anotherUser"),
            NotificationMessage.create("A sample title", "A sample very very long message")
        );
        assertDoesNotThrow(() -> {
            final byte[] serializedData = serializer.serializeCoMembersNotification(originalCommand);
            final var deserializedCommand = serializer.deserializeCoMembersNotification(serializedData);
            assertEquals(originalCommand, deserializedCommand);
        });
    }
}
