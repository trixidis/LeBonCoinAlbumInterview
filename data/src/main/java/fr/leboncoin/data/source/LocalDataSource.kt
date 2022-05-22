package fr.leboncoin.data.source

import android.content.Context
import androidx.room.Room
import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.room.AlbumsDatabase
import fr.leboncoin.data.room.LocalStorageFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(context: Context) : TitleDataSource, LocalStorageFeature {

    private var db: AlbumsDatabase = Room.inMemoryDatabaseBuilder(
        context, AlbumsDatabase::class.java
    ).build()

    override suspend fun fetchTitles(): Flow<TitleEntity> {
        return flow {
            db.getTilesDao().getEveryTitles().onEach {
                emit(it)
            }
        }
    }

    override suspend fun isTitleAlreadyPresentOnStorage(id: Int): Boolean {
        return db.getTilesDao().getTitleWithId(id).isEmpty()
    }

    override suspend fun addTitleToLocalStorage(title: TitleEntity) {
        db.getTilesDao().addTitle(title)
    }
}