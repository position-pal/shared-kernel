package io.github.positionpal;

import io.github.positionpal.commands.CoMembersPushNotification;
import io.github.positionpal.commands.GroupWisePushNotification;
import io.github.positionpal.entities.GroupId;
import io.github.positionpal.entities.NotificationMessage;
import io.github.positionpal.entities.User;
import io.github.positionpal.entities.UserId;
import io.github.positionpal.events.AddedMemberToGroup;
import io.github.positionpal.events.GroupCreated;
import io.github.positionpal.events.GroupDeleted;
import io.github.positionpal.events.RemovedMemberToGroup;
import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.DecoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * A serializer class for serializing and deserializing User and Group events using Avro.
 */
public final class AvroSerializer implements EventSerializer {

    private static final Schema USER_ID_SCHEMA = AvroUserId.getClassSchema();
    private static final Schema GROUP_ID_SCHEMA = AvroGroupId.getClassSchema();
    private static final Schema USER_SCHEMA = AvroUser.getClassSchema();
    private static final Schema ADD_MEMBER_EVENT = AddedMemberToGroupEvent.getClassSchema();
    private static final Schema REMOVE_MEMBER_EVENT = RemovedMemberToGroupEvent.getClassSchema();
    private static final Schema GROUP_CREATED_EVENT = GroupCreatedEvent.getClassSchema();
    private static final Schema GROUP_DELETED_EVENT = GroupDeletedEvent.getClassSchema();
    private static final Schema NOTIFICATION_MESSAGE = AvroNotificationMessage.getClassSchema();
    private static final Schema GROUP_WISE_PUSH_NOTIFICATION = AvroGroupWisePushNotification.getClassSchema();
    private static final Schema CO_MEMBERS_PUSH_NOTIFICATION = AvroCoMembersPushNotification.getClassSchema();

    /**
     * Generic method to serialize an Avro object into a byte array.
     * @param object the Avro object to serialize
     * @param schema the schema of the Avro object
     * @return a byte array representing the serialized object
     * @throws IOException if an I/O error occurs during serialization
     * @param <T> the type of the Avro object
     */
    private <T> byte[] serialize(final T object, final Schema schema) throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        final SpecificDatumWriter<T> writer = new SpecificDatumWriter<>(schema);
        writer.write(object, encoder);
        System.out.println("Something");
        encoder.flush();
        return outputStream.toByteArray();
    }

    /**
     * Generic method to deserialize a byte array into an Avro object.
     * @param data the byte array to deserialize
     * @param schema the schema of the Avro object
     * @return the deserialized Avro object
     * @throws IOException if an I/O error occurs during deserialization
     * @param <T> the type of the Avro object
     */
    private <T> T deserialize(final byte[] data, final Schema schema) throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        final Decoder decoder = DecoderFactory.get().binaryDecoder(inputStream, null);
        final SpecificDatumReader<T> reader = new SpecificDatumReader<>(schema);
        return reader.read(null, decoder);
    }

    @Override
    public UserId deserializeUserId(final byte[] data) throws IOException {
        return AvroConverter.toUserId(deserialize(data, USER_ID_SCHEMA));
    }

    @Override
    public byte[] serializeUserId(final UserId userId) throws IOException {
        return serialize(AvroConverter.toAvroUserId(userId), USER_ID_SCHEMA);
    }

    @Override
    public GroupId deserializeGroupId(final byte[] data) throws IOException {
        return AvroConverter.toGroupId(deserialize(data, GROUP_ID_SCHEMA));
    }

    @Override
    public byte[] serializeGroupId(final GroupId groupId) throws IOException {
        return serialize(AvroConverter.toAvroGroupId(groupId), GROUP_ID_SCHEMA);
    }

    @Override
    public byte[] serializeUser(final User user) throws IOException {
        return serialize(AvroConverter.toAvroUser(user), USER_SCHEMA);
    }

    @Override
    public User deserializeUser(final byte[] data) throws IOException {
        return AvroConverter.toUser(deserialize(data, USER_SCHEMA));
    }

    @Override
    public byte[] serializeAddedMemberToGroup(final AddedMemberToGroup event) throws IOException {
        return serialize(AvroConverter.toAvroAddedMemberToGroup(event), ADD_MEMBER_EVENT);
    }

    @Override
    public AddedMemberToGroup deserializeAddedMemberToGroup(final byte[] data) throws IOException {
        return AvroConverter.toAddedMemberToGroup(deserialize(data, ADD_MEMBER_EVENT));
    }

    @Override
    public byte[] serializeRemovedMemberToGroup(final RemovedMemberToGroup event) throws IOException {
        return serialize(AvroConverter.toAvroRemovedMemberToGroup(event), REMOVE_MEMBER_EVENT);
    }

    @Override
    public RemovedMemberToGroup deserializeRemovedMemberToGroup(final byte[] data) throws IOException {
        return AvroConverter.toRemovedMemberToGroup(deserialize(data, REMOVE_MEMBER_EVENT));
    }

    @Override
    public byte[] serializeGroupCreated(final GroupCreated event) throws IOException {
        return serialize(AvroConverter.toAvroGroupCreated(event), GROUP_CREATED_EVENT);
    }

    @Override
    public GroupCreated deserializeGroupCreated(final byte[] data) throws IOException {
        return AvroConverter.toGroupCreated(deserialize(data, GROUP_CREATED_EVENT));
    }

    @Override
    public byte[] serializeGroupDeleted(final GroupDeleted event) throws IOException {
        return serialize(AvroConverter.toAvroGroupDeleted(event), GROUP_DELETED_EVENT);
    }

    @Override
    public GroupDeleted deserializeGroupDeleted(final byte[] data) throws IOException {
        return AvroConverter.toGroupDeleted(deserialize(data, GROUP_DELETED_EVENT));
    }

    @Override
    public byte[] serializeGroupWiseNotification(final GroupWisePushNotification command) throws IOException {
        return serialize(AvroConverter.toAvroGroupWisePushNotification(command), GROUP_WISE_PUSH_NOTIFICATION);
    }

    @Override
    public GroupWisePushNotification deserializeGroupWiseNotification(final byte[] data) throws IOException {
        return AvroConverter.toGroupWisePushNotification(deserialize(data, GROUP_WISE_PUSH_NOTIFICATION));
    }

    @Override
    public CoMembersPushNotification deserializeCoMembersNotification(final byte[] data) throws IOException {
        return AvroConverter.toCoMembersPushNotification(deserialize(data, CO_MEMBERS_PUSH_NOTIFICATION));
    }

    @Override
    public byte[] serializeCoMembersNotification(final CoMembersPushNotification command) throws IOException {
        return serialize(AvroConverter.toAvroCoMembersPushNotification(command), CO_MEMBERS_PUSH_NOTIFICATION);
    }

    @Override
    public byte[] serializeNotificationMessage(final NotificationMessage message) throws IOException {
        return serialize(AvroConverter.toAvroNotificationMessage(message), NOTIFICATION_MESSAGE);
    }

    @Override
    public NotificationMessage deserializeNotificationMessage(final byte[] data) throws IOException {
        return AvroConverter.toNotificationMessage(deserialize(data, NOTIFICATION_MESSAGE));
    }
}
