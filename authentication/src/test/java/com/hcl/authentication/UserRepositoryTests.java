package com.hcl.authentication;

import com.hcl.authentication.model.User;
import com.hcl.authentication.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  UserRepository userRepository;

  @Test
  public void whenExistsReturnTrue() {
    User user = mockUser();
    entityManager.persist(user);

    boolean exists = userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());

    assertThat(exists).isTrue();
  }

  @Test
  public void whenNotExistsReturnFalse() {
    User user = mockUser();
    entityManager.persist(user);

    boolean exists = userRepository.existsByUsernameAndPassword(user.getUsername(), "password");

    assertThat(exists).isFalse();
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
