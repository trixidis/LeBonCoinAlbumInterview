package fr.leboncoin.data.source

import android.accounts.NetworkErrorException
import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.data.database.entity.TitleEntity
import fr.leboncoin.data.network.AlbumService
import kotlinx.coroutines.flow.*

import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val albumService: AlbumService) :
    DataSource {

    override fun fetchAlbumsWithTitles(): Flow<Result<List<AlbumWithTitles>>> {

        return fetchTitles().
            map {
                it.getOrThrow()
            }
            .transform<List<TitleEntity>,List<AlbumWithTitles>> { titles ->
                val temp  = mutableListOf<AlbumWithTitles>()
                titles.forEach { title ->
                    val albumTemp = temp.firstOrNull {
                        it.album.id == title.albumId
                    }
                    if(albumTemp == null){
                        val albumWithTitles = AlbumWithTitles(AlbumEntity(title.albumId), mutableListOf(title))
                        temp.add(albumWithTitles)
                    }else{
                        val index = temp.indexOf(albumTemp)
                        temp[index].titles.add(title)
                    }
                }
                emit(temp)
            }
            .map {
                Result.success(it)
            }
            .catch {
                emit(Result.failure(it))
            }
    }

    override fun fetchTitles(): Flow<Result<List<TitleEntity>>> {

        return flow {
            emit(
                getResponse(
                    request = { albumService.getAlbums() },
                    defaultErrorMessage = "An error occured during the Album fetch"
                )
            )
        }
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful && result.body() != null) {
                return Result.success(result.body()!!)
            } else {
                Result.failure(NetworkErrorException())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }


}