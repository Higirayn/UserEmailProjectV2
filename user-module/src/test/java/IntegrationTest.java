import org.example.UserService;
import org.example.service.UserServiceProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserService.class)
public class IntegrationTest {

    @Autowired
    private UserServiceProducer userService;

    @Test
    public void publishUserCreated() {
        String assertions = "testmail@mail.ru";
        String result = userService.publishUserCreated(assertions);

        Assertions.assertEquals(result, "CREATE: " + assertions);
    }


}
