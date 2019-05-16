package com.mortuza.digitalsquaremed.database.roomDao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


@Dao
public interface UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertUser(ModelUsers modelUser);
//
//    @Query("DELETE  FROM users")
//    void deleteAllUser();
//
//    @Query("SELECT * FROM users ORDER BY EmpID DESC LIMIT 1")
//    ModelUsers getUser();
}
