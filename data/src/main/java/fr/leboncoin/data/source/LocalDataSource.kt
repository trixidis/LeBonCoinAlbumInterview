package fr.leboncoin.data.source

import android.content.Context
import android.util.Log
import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.TitleEntity
import fr.leboncoin.data.database.AlbumsDatabase
import fr.leboncoin.data.database.LocalStorageFeature
import fr.leboncoin.data.database.entity.AlbumWithTitles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(context: Context, private val db : AlbumsDatabase) : DataSource,
    LocalStorageFeature {


    override fun fetchTitles(): Flow<Result<List<TitleEntity>>> {
        return flow {
            emit(Result.success(db.getTitlesDao().getEveryTitles()))
        }
    }

    override suspend fun addTitleToLocalStorage(title: TitleEntity) {
        db.getTitlesDao().addTitle(title)
    }

    override suspend fun addAlbumToLocalStorage(album: AlbumEntity) {
        db.getAlbumsDao().addAlbum(album)
    }

    override suspend fun hasData(): Boolean {
        return db.getAlbumsDao().getEveryAlbums().count() > 0
    }

    override fun fetchAlbumsWithTitles(): Flow<Result<List<AlbumWithTitles>>> {
        return flow {
            emit(Result.success(db.getAlbumsDao().getAlbumsWithTitles()))
        }

    }
}