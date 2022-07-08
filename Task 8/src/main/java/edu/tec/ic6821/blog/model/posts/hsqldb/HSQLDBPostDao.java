package edu.tec.ic6821.blog.model.posts.hsqldb;
import edu.tec.ic6821.blog.model.posts.Post;
import edu.tec.ic6821.blog.model.posts.PostDao;
import edu.tec.ic6821.blog.model.posts.PostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HSQLDBPostDao extends NamedParameterJdbcDaoSupport implements PostDao {

    private PostRowMapper postRowMapper;

    @Autowired
    public HSQLDBPostDao(final DataSource dataSource, final PostRowMapper postRowMapper) {
        super.setDataSource(dataSource);
        this.postRowMapper = postRowMapper;

    }

    @Override
    public Post create(Post post) {
        final String sql =
            "INSERT INTO posts"
                + "(integration_id, user_integration_id, user_id, title, body, ext_id, created_on, last_updated_on)"
                + " VALUES "
                + "(:integrationId, :userIntegrationId, :userId, :title, :body, :extId, :createdOn, :lastUpdatedOn)";

        final String extId = UUID.randomUUID().toString();
        final LocalDateTime createdOn = LocalDateTime.now();
        final LocalDateTime lastUpdatedOn = LocalDateTime.now();

        final KeyHolder holder = new GeneratedKeyHolder();
        final SqlParameterSource params = new MapSqlParameterSource(Map.of(
            "integrationId", post.getIntegrationId(),
            "userIntegrationId", post.getUserIntegrationId(),
            "userId", post.getUserId(),
            "title", post.getTitle(),
            "body", post.getBody(),
            "extId", extId,
            "createdOn", createdOn,
            "lastUpdatedOn", lastUpdatedOn
        ));

        getNamedParameterJdbcTemplate().update(sql, params, holder);

        return new Post(
            holder.getKey().longValue(),
            post.getIntegrationId(),
            post.getUserIntegrationId(),
            post.getUserId(),
            post.getTitle(),
            post.getBody(),
            extId,
            createdOn,
            lastUpdatedOn
        );
    }

    @Override
    public int create(List<Post> posts) {
        final String sql =
            "INSERT INTO posts"
                + "(integration_id, user_integration_id, user_id, title, body, ext_id, created_on, last_updated_on)"
                + " VALUES "
                + "(:integrationId, :userIntegrationId, :userId, :title, :body, :extId, :createdOn, :lastUpdatedOn)";

        final SqlParameterSource[] params = posts.stream().map(post -> {
            final String extId = UUID.randomUUID().toString();
            final LocalDateTime createdOn = LocalDateTime.now();
            final LocalDateTime lastUpdatedOn = LocalDateTime.now();

            return new MapSqlParameterSource(Map.of(
                "integrationId", post.getIntegrationId(),
                "userIntegrationId", post.getUserIntegrationId(),
                "userId", post.getUserId(),
                "title", post.getTitle(),
                "body", post.getBody(),
                "extId", extId,
                "createdOn", createdOn,
                "lastUpdatedOn", lastUpdatedOn
            ));
        }).toArray(SqlParameterSource[]::new);

        final int[] counts = getNamedParameterJdbcTemplate().batchUpdate(sql, params);
        return Arrays.stream(counts).filter(i -> i > 0).sum();
    }

    @Override
    public Optional<Post> findById(Long id) {
        final String sql = "SELECT * FROM posts WHERE id = :id";
        final Post post = getNamedParameterJdbcTemplate().query(sql,
            Collections.singletonMap("id", id),
            resultSet -> resultSet.next() ? postRowMapper.mapRow(resultSet, 1) : null);
        return Optional.ofNullable(post);
    }

    @Override
    public Optional<Post> findByExtId(String extId) {
        final String sql = "SELECT * FROM posts WHERE ext_id = :extId";
        final Post post = getNamedParameterJdbcTemplate().query(sql,
            Collections.singletonMap("extId", extId),
            resultSet -> resultSet.next() ? postRowMapper.mapRow(resultSet, 1) : null);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> getAll() {
        final String sql = "SELECT * FROM posts";
        final SqlParameterSource queryParams = new MapSqlParameterSource();
        return getNamedParameterJdbcTemplate().query(sql, queryParams, postRowMapper);
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        final String sql = "SELECT * FROM posts WHERE user_id = :user_id";
        final SqlParameterSource query = new MapSqlParameterSource("user_id", userId);
        return getNamedParameterJdbcTemplate().query(sql, query, postRowMapper);
    }

}
