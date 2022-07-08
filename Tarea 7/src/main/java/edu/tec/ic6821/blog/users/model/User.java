package edu.tec.ic6821.blog.users.model;

import java.util.Objects;

public final class User {
    private final Long integrationId;
    private final String username;
    private final String name;
    private final String email;
    private final String zipCode;

    public User(final Long integrationId,
                final String username,
                final String name,
                final String email,
                final String zipCode) {
        
        this.integrationId = integrationId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.zipCode = zipCode;
    }

    public Long getIntegrationId() {
        return integrationId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return integrationId.equals(user.integrationId) && username.equals(user.username) && name.equals(user.name)
            && email.equals(user.email) && zipCode.equals(user.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integrationId, username, name, email, zipCode);
    }

    @Override
    public String toString() {
        return "User{"
            + "integrationId=" + integrationId
            + ", username='" + username + '\''
            + ", name='" + name + '\''
            + ", email='" + email + '\''
            + ", zipCode='" + zipCode + '\''
            + '}';
    }
}

