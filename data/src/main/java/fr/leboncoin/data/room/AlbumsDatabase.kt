package fr.leboncoin.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.entity.TitleEntity

@Database(entities = [TitleEntity::class,AlbumEntitiy::class], version = 2)
abstract class AlbumsDatabase : RoomDatabase() {

    abstract fun getTitlesDao(): TitleDao
    abstract fun getAlbumsDao(): AlbumDao

    companion object {
        private const val DB_NAME = "Albums-Database.db"

        @Volatile
        private var instance: AlbumsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AlbumsDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}