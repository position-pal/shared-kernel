package io.github.positionpal;

/**
 * Enum representing different types of messages.
 */
public enum MessageType {
    /**
     * Message type for when a group is created.
     */
    GROUP_CREATED,

    /**
     * Message type for when a group is deleted.
     */
    GROUP_DELETED,

    /**
     * Message type for when a member is added to a group.
     */
    MEMBER_ADDED,

    /**
     * Message type for when a member is removed from a group.
     */
    MEMBER_REMOVED,

    /**
     * Message type for a {@link io.github.positionpal.commands.GroupWisePushNotification} command.
     */
    GROUP_WISE_NOTIFICATION,

    /**
     * Message type for a {@link io.github.positionpal.commands.CoMembersPushNotification} command.
     */
    CO_MEMBERS_NOTIFICATION
}
