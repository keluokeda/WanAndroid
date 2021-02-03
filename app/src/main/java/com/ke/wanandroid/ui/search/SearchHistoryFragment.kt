package com.ke.wanandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.wanandroid.R
import com.ke.wanandroid.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHistoryFragment : BaseFragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewbind()
    private val searchHistoryViewModel: SearchHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private val searchHistoryAdapter = SearchHistoryAdapter(onClickListener = {
        setKeyword(it)

    }, onDeleteAllButtonClickListener = {
        searchHistoryViewModel.deleteAllHistory()
    }, onDeleteButtonClickListener = {
        searchHistoryViewModel.deleteSearchHistory(it)
    }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = searchHistoryAdapter

        searchHistoryViewModel.historyList.observe(viewLifecycleOwner) {
            searchHistoryAdapter.setHistoryList(it)
        }
        searchHistoryViewModel.hotKeyList.observe(viewLifecycleOwner) {
            searchHistoryAdapter.setHotKeyList(it)
        }

        val editText = view.findViewById<EditText>(R.id.content)

        view.findViewById<View>(R.id.search).setOnClickListener {
            setKeyword(editText.text.toString())
//            activity?.recreate()
        }

        view.findViewById<View>(R.id.back).setOnClickListener {
            onBackPressed()
        }

        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setKeyword(editText.text.toString())
            }

            return@setOnEditorActionListener true
        }
    }

    private fun setKeyword(keyword: String) {

        if (keyword.isEmpty()) {

            return
        }

        val searchResultFragment = SearchResultFragment().apply {
            arguments = bundleOf(SearchActivity.EXTRA_KEY_KEYWORD to keyword)
        }
        activity?.apply {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                searchResultFragment
            ).addToBackStack(null)
                .commit()
        }


    }
}