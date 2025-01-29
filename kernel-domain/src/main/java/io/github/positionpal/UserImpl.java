package io.github.positionpal;

/**
 * A record that implements the User interface.
 *
 * @param id the unique identifier of the user
 * @param name the first name of the user
 * @param surname the last name of the user
 * @param email the email address of the user
 */
public record UserImpl(String id, String name, String surname, String email) implements User { }
