package com.sportworld.lib.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportworld.core.models.User;

import java.time.LocalDateTime;
import java.util.Objects;

public final class UserCreatedEvent {
    public final User user;
    public final LocalDateTime created;

    @JsonCreator
    public UserCreatedEvent(
            @JsonProperty("user") User user,
            @JsonProperty("created") LocalDateTime created) {
        this.user = user;
        this.created = created;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserCreatedEvent) obj;
        return Objects.equals(this.user, that.user) &&
                Objects.equals(this.created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, created);
    }

    @Override
    public String toString() {
        return "Message[" +
                "user=" + user + ", " +
                "created=" + created + ']';
    }
}
