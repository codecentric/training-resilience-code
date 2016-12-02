package com.codecentric.resilience.hystrix.commands;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.codecentric.resilience.hystrix.service.UserService;

/**
 * @author Benjamin Wilms (xd98870)
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceCommandTest {

    @Mock
    private UserService userServiceMock;

    @Test
    public void getUserById_Goodcase() throws Exception {

        int id = 4711;

        when(userServiceMock.getUserNameById(id)).thenReturn("Bob");

        UserServiceCommand userServiceCommand = new UserServiceCommand(id, userServiceMock);

        assertThat(userServiceCommand.execute(), is("Bob"));
    }

    @Test
    public void getUserById_Fallback() throws Exception {

        int id = 4711;

        when(userServiceMock.getUserNameById(id)).thenThrow(new RuntimeException("Sehr schlimmer Fehler"));

        UserServiceCommand userServiceCommand = new UserServiceCommand(id, userServiceMock);

        String expectedFallback = "Der User mit der Id " + id + " wurde nicht gefunden";

        assertThat(userServiceCommand.execute(), is(expectedFallback));
    }
}