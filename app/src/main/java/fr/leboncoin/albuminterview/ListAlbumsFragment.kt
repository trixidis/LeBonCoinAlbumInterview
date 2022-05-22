package fr.leboncoin.albuminterview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import fr.leboncoin.presentation.AlbumListViewModel
import fr.leboncoin.presentation.ui.AlbumUiState

private const val TAG = "FragmentDisplayTitles"

@AndroidEntryPoint
class ListAlbumsFragment : Fragment() {


    private val viewModel : AlbumListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_albums, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenResumed {
            viewModel.albumsFlow.collect{
                when(it){
                    is AlbumUiState.Error -> TODO()
                    is AlbumUiState.Loading -> print("loading")
                    is AlbumUiState.Success -> Log.d(TAG,"on a un nouveau title qui vient d'arriver ${it.titles.count()}")
                }

                //react to ui state change

                //TODO : give it to the list
            }
        }
    }

}