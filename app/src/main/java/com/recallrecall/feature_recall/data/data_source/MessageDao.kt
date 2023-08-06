package com.recallrecall.feature_recall.data.data_source

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.recallrecall.feature_recall.domain.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

  @Query(value = "SELECT * FROM db_message") fun getAll(): LiveData<List<Message>?>

  @Query(value = "SELECT * FROM db_message WHERE id IN (:userIds)")
  fun loadByIds(userIds: IntArray?): List<Message>?

  @Query(
      value =
          "SELECT * FROM db_message WHERE name LIKE :name AND date BETWEEN :startDate AND :endDate ")
  fun loadByDateAndName(startDate: String?, endDate: String?, name: String?): List<Message>?

  @Query(value = "SELECT * FROM db_message WHERE name LIKE :name ORDER BY id DESC")
  fun loadByName(name: String?): LiveData<List<Message>?>

  @Query(value = "SELECT * FROM db_message WHERE name LIKE :name AND recalled LIKE :recalled")
  fun loadByNameAndRecalled(name: String?, recalled: Boolean): List<Message>?

  @Query(value = "SELECT DISTINCT name FROM db_message")
  fun loadAllName(): PagingSource<Int, String>

  @Query(
      value =
          "SELECT * FROM db_message WHERE name = :name and id = (SELECT max(id) FROM db_message WHERE name = :name)")
  fun loadLatestByName(name: String?): Flow<Message>

  @Insert fun insertAll(vararg messages: Message?)

  @Delete fun delete(vararg message: Message?)

  @Update(onConflict = OnConflictStrategy.REPLACE) fun update(vararg message: Message)
}
