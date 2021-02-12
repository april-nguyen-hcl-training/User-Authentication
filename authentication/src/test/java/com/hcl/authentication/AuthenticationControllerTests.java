package com.hcl.authentication;

import com.hcl.authentication.model.User;
import com.hcl.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AuthenticationControllerTests {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private AuthenticationService service;

  @Test
  public void validLoginReturnOk() throws Exception {
    User user = mockUser();
    given(service.authenticateUser(user.getUsername(), user.getPassword())).willReturn(true);

    mvc.perform(post("/authenticate")
      .param("username", "DummyUser")
      .param("password", "password123"))
      .andExpect(status().isOk());
  }

  @Test
  public void invalidLoginReturnUnauthorized() throws Exception {
    User user = mockUser();
    given(service.authenticateUser(user.getUsername(), "password")).willReturn(false);

    mvc.perform(post("/authenticate")
      .param("username", "DummyUser")
      .param("password", "password"))
      .andExpect(status().is(401));
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
