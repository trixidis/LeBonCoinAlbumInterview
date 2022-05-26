package fr.leboncoin.albuminterview.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fr.leboncoin.albuminterview.R
import fr.leboncoin.albuminterview.databinding.FragmentListTitlesBinding
import fr.leboncoin.albuminterview.ui.adapter.title.TitleAdapter
import fr.leboncoin.albuminterview.ui.adapter.title.TitleItem
import fr.leboncoin.presentation.AlbumListViewModel
import fr.leboncoin.presentation.model.TitleUiModel
import fr.leboncoin.presentation.ui.AlbumUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListTitlesFragment : Fragment() {

    private val viewModel: AlbumListViewModel by viewModels()
    private var _binding: FragmentListTitlesBinding? = null
    private val binding get() = _binding!!
    private val adapter = TitleAdapter()
    private val args: ListTitlesFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListTitlesBinding.inflate(inflater, container, false)
        return binding.root
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
        lifecycleScope.launch {
            viewModel.albumsFlow.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED).collectLatest {
                when(it){
                    is AlbumUiState.Error -> view?.let { it1 -> Snackbar.make(it1,
                        R.string.error_common, Snackbar.LENGTH_LONG).show() }
                    is AlbumUiState.Loading -> {
                        binding.progressBarTitles.visibility = View.VISIBLE
                    }
                    is AlbumUiState.Success ->{
                        binding.progressBarTitles.visibility = View.GONE
                        binding.recyclerViewTitles.visibility = View.VISIBLE
                        adapter.submitList(it.albums.filter { it.id == args.idAlbum }.flatMap {
                            it.titles
                        }.map {
                            TitleItem(it)
                        })
                    }
                }
            }
        }
    }
}