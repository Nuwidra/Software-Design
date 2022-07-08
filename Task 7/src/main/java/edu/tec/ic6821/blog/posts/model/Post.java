package edu.tec.ic6821.blog.posts.model;

import java.util.Objects;

public final class Post {
    private final Long integrationId;
    private final Long userIntegrationId;
    private final String title;
    private final String body;

    public Post(final Long integrationId,
                final Long userIntegrationId,
                final String title,
                final String body) {
        this.integrationId = integrationId;
        this.userIntegrationId = userIntegrationId;
        this.title = title;
        this.body = body;
    }

    public Long getIntegrationId() {
        return integrationId;
    }

    public Long getUserIntegrationId() {
        return userIntegrationId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Post post = (Post) o;
        return integrationId.equals(post.integrationId)
            && userIntegrationId.equals(post.userIntegrationId)
            && title.equals(post.title)
            && body.equals(post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integrationId, userIntegrationId, title, body);
    }

    @Override
    public String toString() {
        return "Post{"
            + "integrationId=" + integrationId
            + ", userIntegrationId=" + userIntegrationId
            + ", title='" + title + '\''
            + ", body='" + body + '\''
            + '}';
    }
}
