/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplerest.repositories;

import com.mycompany.simplerest.Services.bd.StaticTemp;
import com.mycompany.simplerest.entities.User;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author java01
 */
public class UserRepository {
    public List<User> findAll() {
        StaticTemp<User> staticTemp = new StaticTemp();
        return staticTemp.find(User.class);
    }
    
    public User findOne(String id) {
        StaticTemp<User> staticTemp = new StaticTemp();
        return staticTemp.findOne(id);
        }
    
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
        }
   
    StaticTemp<User> staticTemp = new StaticTemp();
    staticTemp.save(user);
    }
    }
