package fr.leboncoin.albuminterview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.leboncoin.albuminterview.databinding.FragmentListAlbumsBinding
import fr.leboncoin.albuminterview.ui.AlbumAdapter
import fr.leboncoin.albuminterview.ui.AlbumItem
import fr.leboncoin.presentation.AlbumListViewModel
import fr.leboncoin.presentation.ui.AlbumUiState
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "FragmentDisplayTitles"

@AndroidEntryPoint
class ListAlbumsFragment : Fragment() {


    private val viewModel : AlbumListViewModel by viewModels()
    private var _binding: FragmentListAlbumsBinding? = null
    private val binding get() = _binding!!
    private val adapter = AlbumAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListAlbumsBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenResumed {
            viewModel.albumsFlow.collectLatest{
                when(it){
                    is AlbumUiState.Error -> TODO()
                    is AlbumUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is AlbumUiState.Success ->{
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(it.albums.map { AlbumItem(it.id) })
                        Log.d(TAG,"on a un nouvel album qui vient d'arriver ${it.albums.count()}")
                    }
                }
            }
        }
    }

}