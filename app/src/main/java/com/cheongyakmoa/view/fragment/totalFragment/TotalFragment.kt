package com.cheongyakmoa.view.fragment.totalFragment

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheongyakmoa.view.databinding.FragmentTotalBinding
import kotlinx.android.synthetic.main.fragment_total.*


class totalFragment : Fragment(){

    private lateinit var viewModel: TotalItemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return FragmentTotalBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = requireActivity().obtainViewModel(TotalItemViewModel::class.java)
            vm = viewModel
            recyclerView.apply {
                setHasFixedSize(true)
                addOnScrollListener(TotalRecyclerViewAdapter.InfiniteScrollListener(viewModel))
                adapter = TotalRecyclerViewAdapter(arrayListOf())
                layoutManager = LinearLayoutManager(requireActivity())
            }
        }.root


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.onStart()
    }



    fun <T : ViewModel> FragmentActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProvider(viewModelStore,
            ViewModelFactory.getInstance(
                application
            )
        ).get(viewModelClass)
}


class ViewModelFactory private constructor()
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(TotalItemViewModel::class.java) ->
                    TotalItemViewModel()
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                        )
                            .also { INSTANCE = it }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}