/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 *
 * @author asus
 */
@ApplicationScoped
@FacesConfig
@DatabaseIdentityStoreDefinition(
  dataSourceLookup = "jdbc/tpgrin",
  callerQuery = "select password from member where firstname=?",
  groupsQuery = "select usertype_id from member where firstname=?"
)
@CustomFormAuthenticationMechanismDefinition(
  loginToContinue = @LoginToContinue(
    loginPage = "/login/index.xhtml",
    errorPage = ""
  )
)
//@BasicAuthenticationMechanismDefinition(realmName="user-realm")
public class config {
    
}
