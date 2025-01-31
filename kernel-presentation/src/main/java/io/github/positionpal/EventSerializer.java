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

import java.io.IOException;

/**
 * Interface for serializing and deserializing commands and events to byte arrays and back using Avro.
 * Implementations of this interface allow for efficient, binary serialization for transport or storage.
 */
public interface EventSerializer {

    /**
     * Deserializes a byte array into a {@link UserId} object.
     * @param data the byte array to deserialize
     * @return the deserialized {@link UserId} object
     * @throws IOException if an I/O error occurs during deserialization
     */
    UserId deserializeUserId(byte[] data) throws IOException;

    /**
     * Serializes a {@link UserId} object into a byte array.
     * @param userId the {@link UserId} object to serialize
     * @return a byte array representing the serialized {@link UserId} object
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeUserId(UserId userId) throws IOException;

    /**
     * Deserializes a byte array into a {@link GroupId} object.
     * @param data the byte array to deserialize
     * @return the deserialized {@link GroupId} object
     * @throws IOException if an I/O error occurs during deserialization
     */
    GroupId deserializeGroupId(byte[] data) throws IOException;

    /**
     * Serializes a {@link GroupId} object into a byte array.
     * @param groupId the {@link GroupId} object to serialize
     * @return a byte array representing the serialized {@link GroupId} object
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeGroupId(GroupId groupId) throws IOException;

    /**
     * Serializes a User object into a byte array.
     * @param user the User object to serialize
     * @return a byte array representing the serialized User object
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeUser(User user) throws IOException;

    /**
     * Deserializes a byte array into a User object.
     * @param data the byte array to deserialize
     * @return the deserialized User object
     * @throws IOException if an I/O error occurs during deserialization
     */
    User deserializeUser(byte[] data) throws IOException;

    /**
     * Serializes an AddedMemberToGroup event object into a byte array.
     * @param event the AddedMemberToGroup event object to serialize
     * @return a byte array representing the serialized AddedMemberToGroup event
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeAddedMemberToGroup(AddedMemberToGroup event) throws IOException;

    /**
     * Deserializes a byte array into an AddedMemberToGroup event object.
     * @param data the byte array to deserialize
     * @return the deserialized AddedMemberToGroup event object
     * @throws IOException if an I/O error occurs during deserialization
     */
    AddedMemberToGroup deserializeAddedMemberToGroup(byte[] data) throws IOException;

    /**
     * Serializes a RemovedMemberToGroup event object into a byte array.
     * @param event the RemovedMemberToGroup event object to serialize
     * @return a byte array representing the serialized RemovedMemberToGroup event
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeRemovedMemberToGroup(RemovedMemberToGroup event) throws IOException;

    /**
     * Deserializes a byte array into a RemovedMemberToGroup event object.
     * @param data the byte array to deserialize
     * @return the deserialized RemovedMemberToGroup event object
     * @throws IOException if an I/O error occurs during deserialization
     */
    RemovedMemberToGroup deserializeRemovedMemberToGroup(byte[] data) throws IOException;

    /**
     * Serializes a GroupCreated event object into a byte array.
     * @param event the GroupCreated event object to serialize
     * @return a byte array representing the serialized GroupCreated event
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeGroupCreated(GroupCreated event) throws IOException;

    /**
     * Deserializes a byte array into a GroupCreated event object.
     * @param data the byte array to deserialize
     * @return the deserialized GroupCreated event object
     * @throws IOException if an I/O error occurs during deserialization
     */
    GroupCreated deserializeGroupCreated(byte[] data) throws IOException;

    /**
     * Serializes a GroupDeleted event object into a byte array.
     * @param event the GroupDeleted event object to serialize
     * @return a byte array representing the serialized GroupDeleted event
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeGroupDeleted(GroupDeleted event) throws IOException;

    /**
     * Deserializes a byte array into a GroupDeleted event object.
     * @param data the byte array to deserialize
     * @return the deserialized GroupDeleted event object
     * @throws IOException if an I/O error occurs during deserialization
     */
    GroupDeleted deserializeGroupDeleted(byte[] data) throws IOException;

    /**
     * Deserializes a byte array into a {@link GroupWisePushNotification} object.
     * @param data the byte array to deserialize
     * @return the deserialized {@link GroupWisePushNotification} object
     * @throws IOException if an I/O error occurs during deserialization
     */
    GroupWisePushNotification deserializeGroupWiseNotification(byte[] data) throws IOException;

    /**
     * Serializes a {@link GroupWisePushNotification} object into a byte array.
     * @param command the {@link GroupWisePushNotification} object to serialize
     * @return a byte array representing the serialized {@link GroupWisePushNotification} object
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeGroupWiseNotification(GroupWisePushNotification command) throws IOException;

    /**
     * Deserializes a byte array into a {@link CoMembersPushNotification} object.
     * @param data the byte array to deserialize
     * @return the deserialized {@link CoMembersPushNotification} object
     * @throws IOException if an I/O error occurs during deserialization
     */
    CoMembersPushNotification deserializeCoMembersNotification(byte[] data) throws IOException;

    /**
     * Serializes a {@link CoMembersPushNotification} object into a byte array.
     * @param command the {@link CoMembersPushNotification} object to serialize
     * @return a byte array representing the serialized {@link CoMembersPushNotification} object
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeCoMembersNotification(CoMembersPushNotification command) throws IOException;

    /**
     * Deserializes a byte array into a {@link NotificationMessage} object.
     * @param data the byte array to deserialize
     * @return the deserialized {@link NotificationMessage} object
     * @throws IOException if an I/O error occurs during deserialization
     */
    NotificationMessage deserializeNotificationMessage(byte[] data) throws IOException;

    /**
     * Serializes a {@link NotificationMessage} object into a byte array.
     * @param message the {@link NotificationMessage} object to serialize
     * @return a byte array representing the serialized {@link NotificationMessage} object
     * @throws IOException if an I/O error occurs during serialization
     */
    byte[] serializeNotificationMessage(NotificationMessage message) throws IOException;
}
