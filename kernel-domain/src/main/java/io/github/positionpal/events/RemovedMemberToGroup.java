package io.github.positionpal.events;

import io.github.positionpal.entities.GroupId;
import io.github.positionpal.entities.User;

/**
 * Interface representing an event where a member is removed from a group.
 */
public interface RemovedMemberToGroup extends Event {

    /**
     * Factory method to create an instance of RemovedMemberToGroup.
     * @param groupId the ID of the group
     * @param removedMember the user who was removed from the group
     * @return a new instance of RemovedMemberToGroup
     */
    static RemovedMemberToGroup create(final GroupId groupId, final User removedMember) {
        return new RemovedMemberToGroupImpl(groupId, removedMember);
    }

    /**
     * Gets the ID of the group.
     *
     * @return the group ID
     */
    GroupId groupId();

    /**
     * Gets the user who was removed from the group.
     * @return the removed user
     */
    User removedMember();
}

record RemovedMemberToGroupImpl(GroupId groupId, User removedMember) implements RemovedMemberToGroup { }
