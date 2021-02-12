package com.hcl.authentication.service;

import com.hcl.authentication.model.User;
import com.hcl.authentication.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private static final Logger log = LogManager.getLogger(AuthenticationService.class);

  @Autowired
  private UserRepository userRepository;

  public boolean authenticateUser(String username, String password) {
    log.debug("Checking if username and password exists...");
    return this.userRepository.existsByUsernameAndPassword(username, password);
  }

}
