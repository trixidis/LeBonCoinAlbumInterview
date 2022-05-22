package fr.leboncoin.data.source

import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.exceptions.ApiErrorOperationException
import fr.leboncoin.data.network.AlbumService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val albumService: AlbumService) : TitleDataSource {

    override suspend fun fetchTitles(): Flow<TitleEntity> {
        return flow {
            albumService.getAlbums().map {
                emit(it)
            }
        }
    }

//    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
//        return try {
//            val result = request.invoke()
//            if (result.isSuccessful && result.body() != null) {
//                return Result.success(result.body()!!)
//            } else {
//                Result.failure(ApiErrorOperationException())
//            }
//        } catch (e: Throwable) {
//            Result.failure( e)
//        }
//    }


}