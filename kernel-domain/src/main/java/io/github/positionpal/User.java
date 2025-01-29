package io.github.positionpal;

/**
 * Interface representing a user.
 */
public interface User extends Event {

    /**
     * Factory method to create an instance of User.
     *
     * @param id the ID of the user
     * @param name the name of the user
     * @param surname the surname of the user
     * @param email the email of the user
     * @return a new instance of User
     */
    static User create(final String id,
                       final String name,
                       final String surname,
                       final String email) {
        return new UserImpl(id, name, surname, email);
    }

    /**
     * Gets the ID of the user.
     *
     * @return the user ID
     */
    String id();

    /**
     * Gets the name of the user.
     *
     * @return the user name
     */
    String name();

    /**
     * Gets the surname of the user.
     *
     * @return the user surname
     */
    String surname();

    /**
     * Gets the email of the user.
     *
     * @return the user email
     */
    String email();
}
