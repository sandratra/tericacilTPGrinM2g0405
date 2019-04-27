/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.utils;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author asus
 */
@Named(value = "hashMdp")
@ApplicationScoped
public class HashMdp {

    /**
     * Creates a new instance of HashMdp
     */
    @Inject
  private Pbkdf2PasswordHash passwordHash;

  @PostConstruct
  public void init() {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
    parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
    parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
    passwordHash.initialize(parameters);
  }

  /**
   * Retourne le mot de passe crypté.
   * @param mdp le mot de passe non crypté.
   */  
  public String generate(String mdp) {
    return passwordHash.generate(mdp.toCharArray());
  }
    
}
