/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.User;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EBI
 */
@DataSourceDefinition (
    className="org.apache.derby.jdbc.ClientDataSource",
    name="java:app/jdbc/tpgrin",
    serverName="localhost",
    portNumber=1527,
    user="tpgrin",
    password="tpgrin",
    databaseName="tpgrin"
)
@Stateless
@LocalBean
public class UserManager {

    @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;

    public void save(Object object) {
        em.persist(object);
    }
    
    public void update(Object object){
        em.merge(object);
    }
    
    public void createUser(User user){
        save(user);
    }
    
    public void updateUser(User user){
        update(user);
    }
}
