package edu.tec.ic6821.blog.posts;
import edu.tec.ic6821.blog.model.posts.Post;
import edu.tec.ic6821.blog.model.posts.PostDao;
import edu.tec.ic6821.blog.model.users.User;
import edu.tec.ic6821.blog.model.users.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultPostService implements PostService {

    private final UserDao userDao;
    private final PostDao postDao;
    @Autowired
    public DefaultPostService(final UserDao userDao, final PostDao postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }
    @Override
    public Optional<List<Post>> findByUserExtId(String userExtId) {
        final Optional<User> user = userDao.findByExtId(userExtId);
        if (user.isPresent()) {
            final List<Post> posts = postDao.findByUserId(user.get().getId());
            return Optional.of(posts);
        }
        return Optional.empty();
    }
    @Override
    public Optional<Post> findByExtId(String postExtId) {
        return postDao.findByExtId(postExtId);
    }

}
