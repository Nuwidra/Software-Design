package edu.tec.ic6821.blog.posts;
import edu.tec.ic6821.blog.model.posts.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @Autowired
    public PostController(final PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/{postExtId}")
    public final PostDTO findByExtId(@PathVariable String postExtId) {
        try {
            logger.info(String.format("[findByExternalId(%s)] Retrieving post by postExternalId", postExtId));
            final Optional<Post> optionalPost = postService.findByExtId(postExtId);
            if (optionalPost.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return PostDTO.from(optionalPost.get());

        } catch (ResponseStatusException responseStatusException) {
            throw responseStatusException;
        } catch (Exception e) {
            logger.error(String.format("[findByExternalId(%s)] Unexpected error", postExtId), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                String.format("%s: %s", e.getClass().getName(), e.getMessage()));
        }
    }

}
