package io.github.positionpal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGroupEventsSerialization {

    private static final String GROUP_ID = "group123";

    private AvroSerializer avroSerializer;
    private User testUser;

    @BeforeEach
    void setUp() {
        avroSerializer = new AvroSerializer();
        testUser = User.create("testUser", "test", "user", "test@user.it");
    }

    @Test
    void testSerializeAndDeserializeAddedMemberToGroup() throws IOException {
        final AddedMemberToGroup event = AddedMemberToGroup.create(GROUP_ID, testUser);
        final byte[] serializedData = avroSerializer.serializeAddedMemberToGroup(event);
        final AddedMemberToGroup deserializedEvent = avroSerializer.deserializeAddedMemberToGroup(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
        assertEquals(event.addedMember(), deserializedEvent.addedMember());
    }

    @Test
    void testSerializeAndDeserializeRemovedMemberToGroup() throws IOException {
        final RemovedMemberToGroup event = RemovedMemberToGroup.create(GROUP_ID, testUser);
        final byte[] serializedData = avroSerializer.serializeRemovedMemberToGroup(event);
        final RemovedMemberToGroup deserializedEvent = avroSerializer.deserializeRemovedMemberToGroup(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
        assertEquals(event.removedMember(), deserializedEvent.removedMember());
    }

    @Test
    void testSerializeAndDeserializeGroupCreated() throws IOException {
        final GroupCreated event = GroupCreated.create(GROUP_ID, testUser);
        final byte[] serializedData = avroSerializer.serializeGroupCreated(event);
        final GroupCreated deserializedEvent = avroSerializer.deserializeGroupCreated(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
        assertEquals(event.createdBy(), deserializedEvent.createdBy());
    }

    @Test
    void testSerializeAndDeserializeGroupDeleted() throws IOException {
        final GroupDeleted event = GroupDeleted.create(GROUP_ID);
        final byte[] serializedData = avroSerializer.serializeGroupDeleted(event);
        final GroupDeleted deserializedEvent = avroSerializer.deserializeGroupDeleted(serializedData);
        assertEquals(event.groupId(), deserializedEvent.groupId());
    }
}

