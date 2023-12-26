package com.example.quizapp.presentation.ui.fragments.question

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.R
import com.example.databinding.FragmentQuestionBinding
import com.example.quizapp.presentation.base.BaseFragment
import com.example.quizapp.presentation.ui.adapters.QuestionsAdapter
import com.example.quizapp.presentation.ui.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val binding by viewBinding(FragmentQuestionBinding::bind)
    override val viewModel: QuestionViewModel by viewModels()
    private val args by navArgs<QuestionFragmentArgs>()
    private val questionsAdapter = QuestionsAdapter(this::onItemClick)
    private var correctAns: Int = 0

    override fun initialize() {
        setupRecycler()
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(
                QuestionFragmentDirections.actionQuestionFragmentToHomeFragment()
            )
        }
    }

    private fun setupRecycler() = with(binding.rvQuiz) {
        layoutManager = object : LinearLayoutManager(requireContext(), VERTICAL, false) {
            override fun canScrollVertically() = false
        }
        adapter = questionsAdapter
        setItemViewCacheSize(1)
    }

    override fun setupRequests() {
        viewModel.fetchCategory(args.category, args.difficulty, args.amount)
    }

    override fun setupSubscribes() {
        subscribeToQuestions()
    }

    private fun subscribeToQuestions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoryState.collect {
                    when (it) {
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                        is UIState.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        is UIState.Success -> {
                            it.data.let { data ->
                                questionsAdapter.submitList(data)
                                binding.progressBar.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onItemClick(
        position: Int,
        question: Int,
        correctAnswers: Boolean,
        category: String,
        difficulty: String
    ) {
        if (position == questionsAdapter.currentList.lastIndex) {
            viewLifecycleOwner.lifecycleScope.launch {
                if (correctAnswers) {
                    correctAns++
                }
                val resultPercent = (correctAns.toDouble() / args.amount.toDouble()) * 100
                val result = "${resultPercent.toInt()}%"
                delay(900)
                findNavController().navigate(
                    QuestionFragmentDirections.actionQuestionFragmentToResultFragment(
                        question + 1,
                        correctAns,
                        result,
                        category,
                        difficulty
                    )
                )
            }
        } else {
            viewLifecycleOwner.lifecycleScope.launch {
                if (correctAnswers) {
                    correctAns++
                }
                delay(900)
                binding.rvQuiz.scrollToPosition(position + 1)
            }
        }
    }
}