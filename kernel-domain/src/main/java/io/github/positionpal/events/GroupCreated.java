package io.github.positionpal.events;

import io.github.positionpal.entities.GroupId;
import io.github.positionpal.entities.User;

/**
 * Interface representing an event where a group is created.
 */
public interface GroupCreated extends Event {

    /**
     * Gets the ID of the group.
     * @return the group ID
     */
    GroupId groupId();

    /**
     * Gets the user who created the group.
     * @return the user who created the group
     */
    User createdBy();

    /**
     * Factory method to create an instance of GroupCreated.
     * @param groupId the ID of the group
     * @param createdBy the user who created the group
     * @return a new instance of GroupCreated
     */
    static GroupCreated create(final GroupId groupId, final User createdBy) {
        return new GroupCreatedImpl(groupId, createdBy);
    }
}

record GroupCreatedImpl(GroupId groupId, User createdBy) implements GroupCreated { }
