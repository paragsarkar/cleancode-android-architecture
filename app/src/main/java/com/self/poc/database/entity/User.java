package com.self.poc.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by paragsarkar on 29/10/17.
 */

@Entity
public class User {

    @PrimaryKey
    int id;

    @ColumnInfo(name = "username")
    private String name;

    @ColumnInfo(name = "address")
    private String address;


}
