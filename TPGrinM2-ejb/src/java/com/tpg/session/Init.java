/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.Member;
import com.tpg.entity.UserType;
import com.tpg.utils.HashMdp;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 *
 * @author EBI
 */
@Singleton
@LocalBean
@Startup
@DataSourceDefinition (
    className="org.apache.derby.jdbc.ClientDataSource",
    name="java:app/jdbc/tpgrin",
    serverName="localhost",
    portNumber=1527,
    user="tpgrin", // nom et
    password="tpgrin", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="tpgrin"
)
public class Init {
    
    @EJB
    private UserManager userManager;
    
    @EJB
    private UserTypeManager usertpManager;
    
    @Inject
    // Pour coder le mot de passe
    private HashMdp passwordHash;
        
        @Resource(lookup = "java:app/jdbc/tpgrin")
  private DataSource dataSource;
    
    /**
     *
     * @param user
     */
    @PostConstruct
    public void postInit(){
        UserType ut = new UserType();
        ut.setLabel("admin");
        long id = usertpManager.createUserType(ut);
        
        Member u = new Member();
        u.setFirstname("toto");
        u.setLastname("toto");
        u.setUserType(ut);
        String mdp = passwordHash.generate("toto");
        u.setPassword(mdp);
        userManager.createUser(u);
        
        
    }
}
