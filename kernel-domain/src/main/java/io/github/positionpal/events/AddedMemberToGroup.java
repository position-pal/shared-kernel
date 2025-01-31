package io.github.positionpal.events;

import io.github.positionpal.entities.GroupId;
import io.github.positionpal.entities.User;

/**
 * Interface representing an event where a member is added to a group.
 */
public interface AddedMemberToGroup extends Event {

    /**
     * Gets the ID of the group.
     * @return the group ID
     */
    GroupId groupId();

    /**
     * Gets the user added to the group.
     * @return the added member
     */
    User addedMember();

    /**
     * Factory method to create an instance of AddedMemberToGroup.
     * @param groupId the ID of the group
     * @param addedMember the user who was added to the group
     * @return a new instance of AddedMemberToGroup
     */
    static AddedMemberToGroup create(final GroupId groupId, final User addedMember) {
        return new AddedMemberToGroupImpl(groupId, addedMember);
    }
}

record AddedMemberToGroupImpl(GroupId groupId, User addedMember) implements AddedMemberToGroup { }
