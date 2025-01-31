package io.github.positionpal;

import io.github.positionpal.entities.User;
import io.github.positionpal.entities.UserId;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TestUserSerialization {

    @Test
    void testUserSerialization() {
        final User toSerialize = User.create(UserId.create(UUID.randomUUID().toString()), "test", "user", "test@user.it");
        final AvroSerializer serializer = new AvroSerializer();
        try {
            final byte[] serializedObject = serializer.serializeUser(toSerialize);
            final User deserializedObject = serializer.deserializeUser(serializedObject);
            assertEquals(toSerialize, deserializedObject);
        } catch (IOException e) {
            fail();
        }
    }
}
