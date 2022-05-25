package fr.leboncoin.albuminterview.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fr.leboncoin.albuminterview.R
import fr.leboncoin.albuminterview.databinding.FragmentListAlbumsBinding
import fr.leboncoin.albuminterview.databinding.FragmentListTitlesBinding
import fr.leboncoin.albuminterview.ui.adapter.album.AlbumAdapter
import fr.leboncoin.albuminterview.ui.adapter.album.AlbumItem
import fr.leboncoin.albuminterview.ui.utils.Utils
import fr.leboncoin.presentation.AlbumListViewModel
import fr.leboncoin.presentation.ui.AlbumUiState
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ListTitlesFragment : DialogFragment() {


    private var _binding: FragmentListTitlesBinding? = null
    private val binding get() = _binding!!
    private val adapter = AlbumAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListTitlesBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Here if we are able to determine a number of columns depending on the width
        // of the screen we set it or we set the default value
        binding.recyclerViewTitles.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTitles.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenResumed {
//            viewModel.albumsFlow.collectLatest{
//                when(it){
//                    is AlbumUiState.Error -> view?.let { it1 -> Snackbar.make(it1,
//                        R.string.error_common, Snackbar.LENGTH_LONG).show() }
//                    is AlbumUiState.Loading -> {
//                        binding.progressBar.visibility = View.VISIBLE
//                    }
//                    is AlbumUiState.Success ->{
//                        binding.progressBar.visibility = View.GONE
//                        adapter.submitList(it.albums.map { AlbumItem(it) })
//                        Log.d(TAG,"on a un nouvel album qui vient d'arriver ${it.albums.count()}")
//                    }
//                }
//            }
        }
    }
}