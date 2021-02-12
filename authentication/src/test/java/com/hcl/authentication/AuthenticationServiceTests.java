package com.hcl.authentication;

import com.hcl.authentication.model.User;
import com.hcl.authentication.repository.UserRepository;
import com.hcl.authentication.service.AuthenticationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AuthenticationServiceTests {
  @Autowired
  private AuthenticationService authenticationService;

  @MockBean
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    User user = mockUser();
    Mockito.when(userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword()))
      .thenReturn(true);
  }

  @Test
  public void whenValidAuthenticateTrue() {
    boolean authenticated = authenticationService.authenticateUser("DummyUser", "password123");

    assertThat(authenticated).isTrue();
  }

  @Test
  public void whenNotValidAuthenticateFalse() {
    boolean authenticated = authenticationService.authenticateUser("DummyUser", "password");

    assertThat(authenticated).isFalse();
  }

  private User mockUser() {
    User mockUser = new User();
    mockUser.setUsername("DummyUser");
    mockUser.setPassword("password123");
    mockUser.setFirstName("Dummy");
    mockUser.setLastName("User");
    mockUser.setEmail("dummyuser@mail.com");
    Calendar date = Calendar.getInstance();
    date.add(Calendar.YEAR, -20);
    mockUser.setBirthday(date.getTime());
    return mockUser;
  }

}
