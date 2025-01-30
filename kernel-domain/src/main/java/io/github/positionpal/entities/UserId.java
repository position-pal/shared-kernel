package io.github.positionpal.entities;

/**
 * A value object representing a user identifier.
 */
public interface UserId {

    /**
     * @return the value of the user identifier.
     */
    String value();

    /**
     * Factory method to create an instance of UserId.
     * @param username the username of the user
     * @return a new instance of UserId
     */
    static UserId create(final String username) {
        return new UserIdImpl(username);
    }
}
