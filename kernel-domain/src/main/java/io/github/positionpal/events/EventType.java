package io.github.positionpal.events;

/**
 * Enum representing different types of events.
 */
public enum EventType {
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
}
