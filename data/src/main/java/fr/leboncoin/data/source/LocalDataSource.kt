package fr.leboncoin.data.source

import android.content.Context
import android.util.Log
import fr.leboncoin.data.entity.AlbumEntity
import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.database.AlbumsDatabase
import fr.leboncoin.data.database.LocalStorageFeature
import fr.leboncoin.data.entity.AlbumWithTitles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(context: Context) : AlbumDataSource, TitleDataSource,
    LocalStorageFeature {

    private var db: AlbumsDatabase = AlbumsDatabase.invoke(context)

    override suspend fun fetchTitles(): Flow<Result<List<TitleEntity>>> {
        return flow {
            emit(Result.success(db.getTitlesDao().getEveryTitles()))
        }
    }

    override suspend fun isAlbumAlreadyPresentOnStorage(id: Int): Boolean {
        val res = db.getAlbumsDao().getAlbumWithId(id).isNotEmpty()
        Log.d("DB","album deja present == $res")
        return res
    }

    override suspend fun isTitleAlreadyPresentOnStorage(id: Int): Boolean {
        val res = db.getTitlesDao().getTitleWithId(id).isNotEmpty()
        Log.d("DB","titre deja present == $res")
        return res
    }

    override suspend fun addTitleToLocalStorage(title: TitleEntity) {
        Log.d("DB","title to add $title")
        db.getTitlesDao().addTitle(title)
    }

    override suspend fun addAlbumToLocalStorage(album: AlbumEntity) {
        db.getAlbumsDao().addAlbum(album)
    }

    override suspend fun hasData(): Boolean {
        val res = db.getAlbumsDao().getEveryAlbums().count()>0
        Log.d("DB", "hasData == $res")
       return res
    }

    override fun fetchAlbums(): Flow<List<AlbumEntity>> {
        return flow {
            emit(db.getAlbumsDao().getEveryAlbums())
        }
    }

    override fun fetchAlbumsWithTitles(): Flow<List<AlbumWithTitles>> {
        return flow {
            emit(db.getAlbumsDao().getAlbumsWithTitles())
        }

    }
}