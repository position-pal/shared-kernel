package io.github.positionpal.entities;

record GroupIdImpl(String value) implements GroupId { }

record NotificationMessageImpl(String title, String body) implements NotificationMessage { }

record UserIdImpl(String value) implements UserId { }

record UserImpl(UserId id, String name, String surname, String email) implements User { }
