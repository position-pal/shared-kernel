package io.github.positionpal;

import io.github.positionpal.entities.GroupId;
import io.github.positionpal.entities.User;
import io.github.positionpal.entities.UserId;
import io.github.positionpal.events.AddedMemberToGroup;
import io.github.positionpal.events.GroupCreated;
import io.github.positionpal.events.GroupDeleted;
import io.github.positionpal.events.RemovedMemberToGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGroupEventsSerialization {

    private AvroSerializer avroSerializer;
    private User testUser;
    private GroupId testGroup;

    @BeforeEach
    void setUp() {
        avroSerializer = new AvroSerializer();
        testUser = User.create(UserId.create("testUser"), "test", "user", "test@user.it");
        testGroup = GroupId.create("group123");
    }

    @Test
    void testSerializeAndDeserializeAddedMemberToGroup() throws IOException {
        final AddedMemberToGroup event = AddedMemberToGroup.create(testGroup, testUser);
        final byte[] serializedData = avroSerializer.serializeAddedMemberToGroup(event);
        final AddedMemberToGroup deserializedEvent = avroSerializer.deserializeAddedMemberToGroup(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
        assertEquals(event.addedMember(), deserializedEvent.addedMember());
    }

    @Test
    void testSerializeAndDeserializeRemovedMemberToGroup() throws IOException {
        final RemovedMemberToGroup event = RemovedMemberToGroup.create(testGroup, testUser);
        final byte[] serializedData = avroSerializer.serializeRemovedMemberToGroup(event);
        final RemovedMemberToGroup deserializedEvent = avroSerializer.deserializeRemovedMemberToGroup(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
        assertEquals(event.removedMember(), deserializedEvent.removedMember());
    }

    @Test
    void testSerializeAndDeserializeGroupCreated() throws IOException {
        final GroupCreated event = GroupCreated.create(testGroup, testUser);
        final byte[] serializedData = avroSerializer.serializeGroupCreated(event);
        final GroupCreated deserializedEvent = avroSerializer.deserializeGroupCreated(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
        assertEquals(event.createdBy(), deserializedEvent.createdBy());
    }

    @Test
    void testSerializeAndDeserializeGroupDeleted() throws IOException {
        final GroupDeleted event = GroupDeleted.create(testGroup);
        final byte[] serializedData = avroSerializer.serializeGroupDeleted(event);
        final GroupDeleted deserializedEvent = avroSerializer.deserializeGroupDeleted(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
    }
}

