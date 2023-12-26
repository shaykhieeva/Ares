package com.example.quizapp.presentation.ui.fragments.result

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.R
import com.example.databinding.FragmentResultBinding
import com.example.quizapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultViewModel>(R.layout.fragment_result) {

    override val binding by viewBinding(FragmentResultBinding::bind)
    override val viewModel: ResultViewModel by viewModels()
    private val args by navArgs<ResultFragmentArgs>()

    override fun setupRequests() {
        binding.tvAnswers.text = args.answers.toString()
        binding.tvCategory.text = args.category
        binding.tvDifficulty.text = args.difficulty
        binding.tvCorrectAnswers.text = args.correctAnswers.toString()
        binding.tvPercent.text = args.result
    }

    override fun setupListeners() {
        binding.btnSkip.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}