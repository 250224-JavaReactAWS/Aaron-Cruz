import com.ecommerce.models.Role;
import com.ecommerce.models.User;
import com.ecommerce.repos.UserDAOImpl;
import com.ecommerce.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        when(mockUserDAOImpl.create(any(User.class)))
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

    // Login
    @Test
    public void correctLoginShouldReturnUser() {
        String email = "speedy@gonzalez.com";
        String password = "Password";
        User mockedUser = new User("Speedy", "Gonzalez", email, password);
        when(mockUserDAOImpl.getUserByEmail(email)).thenReturn(mockedUser);

        User returnedUser = userService.loginUser(email, password);

        assertEquals(mockedUser, returnedUser);
    }

    @Test
    public void incorrectLoginShouldReturnNull() {
        String email = "speedy@gonzalez.com";
        String password = "Password";
        String incorrectPassword = "NoRight";
        User mockedUser = new User("Speedy", "Gonzalez", email, password);
        when(mockUserDAOImpl.getUserByEmail(email)).thenReturn(mockedUser);

        User returnedUser = userService.loginUser(email, incorrectPassword);

        assertNull(returnedUser);
    }

    @Test
    public void updateUserDataShouldReturnNewData() {
        User updatedUser = new User("Speedy", "Rat-Gonzalez", "speedy0@gonzalez.com", "Password1");
        when(mockUserDAOImpl.update(any(User.class))).thenReturn(updatedUser);

        User returnedUser = userService.updateUserInfo(updatedUser);

        assertEquals(updatedUser, returnedUser);
    }

    //Purchases
}
