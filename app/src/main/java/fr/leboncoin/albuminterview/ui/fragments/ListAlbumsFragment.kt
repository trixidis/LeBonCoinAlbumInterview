package fr.leboncoin.albuminterview.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fr.leboncoin.albuminterview.R
import fr.leboncoin.albuminterview.databinding.FragmentListAlbumsBinding
import fr.leboncoin.albuminterview.ui.adapter.album.AlbumAdapter
import fr.leboncoin.albuminterview.ui.adapter.album.AlbumItem
import fr.leboncoin.albuminterview.ui.adapter.album.OnAlbumClickListener
import fr.leboncoin.albuminterview.ui.utils.Utils
import fr.leboncoin.presentation.AlbumListViewModel
import fr.leboncoin.presentation.ui.AlbumUiState
import kotlinx.coroutines.launch


private const val DEFAULT_SPAN_COUNT = 3


@AndroidEntryPoint
class ListAlbumsFragment : Fragment(), OnAlbumClickListener {


    private val viewModel: AlbumListViewModel by viewModels()
    private var _binding: FragmentListAlbumsBinding? = null
    private val binding get() = _binding!!
    private val adapter = AlbumAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListAlbumsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Here if we are able to determine a number of columns depending on the width
        // of the screen we set it or we set the default value
        binding.recyclerView.layoutManager = GridLayoutManager(
            context,
            context?.let { Utils.calculateNoOfColumns(it) } ?: DEFAULT_SPAN_COUNT)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.albumsFlow.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED)
                .collect{
                when (it) {
                    is AlbumUiState.Error -> view?.let { it1 ->
                        Snackbar.make(
                            it1,
                            R.string.error_common, Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is AlbumUiState.Loading -> {
                        binding.progressBar.visibility = VISIBLE
                    }
                    is AlbumUiState.Success -> {
                        if(it.albums.isEmpty()){
                            binding.emptyView.visibility = VISIBLE
                        }else{
                            binding.emptyView.visibility = GONE
                            adapter.submitList(it.albums.map { AlbumItem(it) })
                        }
                        binding.progressBar.visibility = GONE

                    }
                }
            }
        }
    }

    override fun onclick(albumId: Int) {
        val action =
            ListAlbumsFragmentDirections.actionListAlbumsFragmentToListTitlesFragment(albumId)
        binding.root.findNavController().navigate(action)
    }

}