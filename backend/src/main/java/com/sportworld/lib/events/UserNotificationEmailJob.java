package com.sportworld.lib.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserNotificationEmailJob {
    private int userID;

    @JsonCreator
    public UserNotificationEmailJob(@JsonProperty("userID") int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNotificationEmailJob that = (UserNotificationEmailJob) o;
        return userID == that.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }

    @Override
    public String toString() {
        return "UserNotificationEmailJob{" +
                "userID=" + userID +
                '}';
    }
}
