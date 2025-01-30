package io.github.positionpal.events;

import io.github.positionpal.entities.GroupId;

/**
 * Interface representing an event where a group is deleted.
 */
public interface GroupDeleted extends Event {

    /**
     * Factory method to create an instance of GroupDeleted.
     * @param groupId the ID of the group
     * @return a new instance of GroupDeleted
     */
    static GroupDeleted create(final GroupId groupId) {
        return new GroupDeletedImpl(groupId);
    }

    /**
     * Gets the ID of the group.
     * @return the group ID
     */
    GroupId groupId();
}

record GroupDeletedImpl(GroupId groupId) implements GroupDeleted { }
