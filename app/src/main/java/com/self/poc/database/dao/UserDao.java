package com.self.poc.database.dao;

import android.arch.persistence.room.Dao;

import com.self.poc.database.entity.User;

/**
 * Created by paragsarkar on 03/11/17.
 */


@Dao
public interface UserDao {



    void save(User user);
}
