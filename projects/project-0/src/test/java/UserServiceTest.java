import com.ecommerce.models.User;
import com.ecommerce.repos.UserDAOImpl;
import com.ecommerce.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    private UserService userService;

    private  UserDAOImpl mockUserDAOImpl;

    @Before
    public void setup() {
        mockUserDAOImpl = Mockito.mock(UserDAOImpl.class);
        userService = new UserService(mockUserDAOImpl);
    }

    @Test
    public void registerNewUserShouldReturnUser() {
        String firstName = "Speedy";
        String lastName = "Gonzalez";
        String email = "speedy@gonzalez.com";
        String password = "Password";

        Mockito.when(mockUserDAOImpl.create(any(User.class)))
                .thenAnswer(invocation -> {
                    User user = invocation.getArgument(0);
                    return new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
                });

        User createdUser = userService.registerNewUser(firstName, lastName, email, password);

        assertNotNull(createdUser);
        assertEquals(firstName, createdUser.getFirstName());
        assertEquals(lastName, createdUser.getLastName());
        assertEquals(email, createdUser.getEmail());
    }
}
