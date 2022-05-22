package fr.leboncoin.data.source

import android.content.Context
import androidx.room.Room
import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.room.AlbumsDatabase
import fr.leboncoin.data.room.LocalStorageFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(context: Context) : AlbumDataSource,TitleDataSource, LocalStorageFeature {

    private var db: AlbumsDatabase = Room.inMemoryDatabaseBuilder(
        context, AlbumsDatabase::class.java
    ).build()

    override suspend fun fetchTitles(): List<TitleEntity> {
            return db.getTitlesDao().getEveryTitles()
    }

    override suspend fun isAlbumAlreadyPresentOnStorage(id: Int): Boolean {
        return db.getAlbumsDao().getAlbumWithId(id).isNotEmpty()
    }

    override suspend fun isTitleAlreadyPresentOnStorage(id: Int): Boolean {
        return db.getTitlesDao().getTitleWithId(id).isNotEmpty()
    }

    override suspend fun addTitleToLocalStorage(title: TitleEntity) {
        db.getTitlesDao().addTitle(title)
    }

    override suspend fun addAlbumToLocalStorage(album: AlbumEntitiy) {
        db.getAlbumsDao().addAlbum(album)
    }

    override fun fetchAlbums(): Flow<List<AlbumEntitiy>> {
        return flow {
            emit(db.getAlbumsDao().getEveryAlbums())
        }
    }
}