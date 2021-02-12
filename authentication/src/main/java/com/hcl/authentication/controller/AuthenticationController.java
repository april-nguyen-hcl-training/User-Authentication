package com.hcl.authentication.controller;

import com.hcl.authentication.exception.UserNotAuthenticatedException;
import com.hcl.authentication.model.User;
import com.hcl.authentication.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  private static final Logger log = LogManager.getLogger(AuthenticationController.class);

  @PostMapping("/authenticate")
  public String submitLogin(@RequestParam String username, @RequestParam String password) {
    log.debug("Trying to authenticate user...");
    if(authenticationService.authenticateUser(username, password)) {
      log.debug("User is authenticated, username and password is valid.");
      return username + " is authenticated";
    } else {
      log.debug("User failed authentication, username and password is invalid.");
      throw new UserNotAuthenticatedException("User failed authentication, username and/or password is invalid.");
    }
  }
}
