package com.example.myapplication.data.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Application
import com.example.myapplication.EXTRA_DOG_BREED
import com.example.myapplication.EXTRA_DOG_NAME
import com.example.myapplication.R
import com.example.myapplication.data.model.Breed
import com.example.myapplication.data.presentation.ui.BreedsAdapter
import com.example.myapplication.data.presentation.ui.DetailsActivity

private const val TAG = "BreedsFragment"

class BreedsFragment : Fragment(){
    private val viewModel by viewModels<BreedsViewModel>() {
        BreedsViewModelFactory((requireActivity().application as Application).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
        viewModel.loadDogs()
    }

    private fun setup() {
        requireView().findViewById<RecyclerView>(R.id.rv_breeds).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BreedsAdapter(::openDetailsScreen, viewModel::favBreed)
        }

        viewModel.dogsLiveData.observe(viewLifecycleOwner) {
            val adapter =
                requireView().findViewById<RecyclerView>(R.id.rv_breeds).adapter as BreedsAdapter
            adapter.submitList(it)
        }
    }

    private fun openDetailsScreen(breed: Breed) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra(EXTRA_DOG_NAME, breed.name)
        intent.putExtra(EXTRA_DOG_BREED, breed.id)
        startActivity(intent)
    }
}