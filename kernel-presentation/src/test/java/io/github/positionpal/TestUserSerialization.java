package io.github.positionpal;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TestUserSerialization {

    @Test
    void testUserSerialization() {
        final User toSerialize = User.create("testUser", "test", "user", "test@user.it");
        final AvroSerializer serializer = new AvroSerializer();
        User deserializedObject = null;
        try {
            final byte[] serializedObject = serializer.serializeUser(toSerialize);
            deserializedObject = serializer.deserializeUser(serializedObject);
        } catch (IOException e) {
            fail();
        }
        assertEquals(toSerialize, deserializedObject);
    }
}
