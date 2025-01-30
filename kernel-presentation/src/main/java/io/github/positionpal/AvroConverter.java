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

/** Utility class to convert objects to and from Avro objects. */
final class AvroConverter {

    private AvroConverter() { }

    static AvroUserId toAvroUserId(final UserId userId) {
        return AvroUserId.newBuilder().setUsername(userId.value()).build();
    }

    static UserId toUserId(final AvroUserId avroUserId) {
        return UserId.create(avroUserId.getUsername());
    }

    static AvroUser toAvroUser(final User user) {
        return AvroUser.newBuilder()
            .setId(toAvroUserId(user.id()))
            .setName(user.name())
            .setSurname(user.surname())
            .setEmail(user.email())
            .build();
    }

    static User toUser(final AvroUser avroUser) {
        return User.create(
            toUserId(avroUser.getId()),
            avroUser.getName(),
            avroUser.getSurname(),
            avroUser.getEmail()
        );
    }

    static AvroGroupId toAvroGroupId(final GroupId groupId) {
        return AvroGroupId.newBuilder().setValue(groupId.value()).build();
    }

    static GroupId toGroupId(final AvroGroupId avroGroupId) {
        return GroupId.create(avroGroupId.getValue());
    }

    static AddedMemberToGroupEvent toAvroAddedMemberToGroup(final AddedMemberToGroup addedMemberToGroup) {
        return AddedMemberToGroupEvent.newBuilder()
            .setGroupId(toAvroGroupId(addedMemberToGroup.groupId()))
            .setAddedMember(toAvroUser(addedMemberToGroup.addedMember()))
            .build();
    }

    static AddedMemberToGroup toAddedMemberToGroup(final AddedMemberToGroupEvent avroAddedMemberToGroup) {
        return AddedMemberToGroup.create(
            toGroupId(avroAddedMemberToGroup.getGroupId()),
            toUser(avroAddedMemberToGroup.getAddedMember())
        );
    }

    static RemovedMemberToGroupEvent toAvroRemovedMemberToGroup(final RemovedMemberToGroup removedMemberToGroup) {
        return RemovedMemberToGroupEvent.newBuilder()
            .setGroupId(toAvroGroupId(removedMemberToGroup.groupId()))
            .setRemovedMember(toAvroUser(removedMemberToGroup.removedMember()))
            .build();
    }

    static RemovedMemberToGroup toRemovedMemberToGroup(final RemovedMemberToGroupEvent avroRemovedMemberToGroup) {
        return RemovedMemberToGroup.create(
            toGroupId(avroRemovedMemberToGroup.getGroupId()),
            toUser(avroRemovedMemberToGroup.getRemovedMember())
        );
    }

    static GroupCreatedEvent toAvroGroupCreated(final GroupCreated groupCreated) {
        return GroupCreatedEvent.newBuilder()
            .setGroupId(toAvroGroupId(groupCreated.groupId()))
            .setCreatedBy(toAvroUser(groupCreated.createdBy()))
            .build();
    }

    static GroupCreated toGroupCreated(final GroupCreatedEvent avroGroupCreated) {
        return GroupCreated.create(toGroupId(avroGroupCreated.getGroupId()), toUser(avroGroupCreated.getCreatedBy()));
    }

    static GroupDeletedEvent toAvroGroupDeleted(final GroupDeleted groupDeleted) {
        return GroupDeletedEvent.newBuilder()
            .setGroupId(toAvroGroupId(groupDeleted.groupId()))
            .build();
    }

    static GroupDeleted toGroupDeleted(final GroupDeletedEvent avroGroupDeleted) {
        return GroupDeleted.create(toGroupId(avroGroupDeleted.getGroupId()));
    }

    static AvroNotificationMessage toAvroNotificationMessage(final NotificationMessage notificationMessage) {
        return AvroNotificationMessage.newBuilder()
            .setTitle(notificationMessage.title())
            .setBody(notificationMessage.body())
            .build();
    }

    public static NotificationMessage toNotificationMessage(final AvroNotificationMessage avroNotificationMessage) {
        return NotificationMessage.create(avroNotificationMessage.getTitle(), avroNotificationMessage.getBody());
    }

    static AvroGroupWisePushNotification toAvroGroupWisePushNotification(final GroupWisePushNotification notification) {
        return AvroGroupWisePushNotification.newBuilder()
            .setRecipient(toAvroGroupId(notification.recipient()))
            .setSender(toAvroUserId(notification.sender()))
            .setMessage(toAvroNotificationMessage(notification.message()))
            .build();
    }

    static GroupWisePushNotification toGroupWisePushNotification(final AvroGroupWisePushNotification notification) {
        return GroupWisePushNotification.of(
            toGroupId(notification.getRecipient()),
            toUserId(notification.getSender()),
            toNotificationMessage(notification.getMessage())
        );
    }

    static AvroCoMembersPushNotification toAvroCoMembersPushNotification(final CoMembersPushNotification notification) {
        return AvroCoMembersPushNotification.newBuilder()
            .setReferenceUser(toAvroUserId(notification.referenceUser()))
            .setSender(toAvroUserId(notification.sender()))
            .setMessage(toAvroNotificationMessage(notification.message()))
            .build();
    }

    static CoMembersPushNotification toCoMembersPushNotification(final AvroCoMembersPushNotification notification) {
        return CoMembersPushNotification.of(
            toUserId(notification.getReferenceUser()),
            toUserId(notification.getSender()),
            toNotificationMessage(notification.getMessage())
        );
    }
}
