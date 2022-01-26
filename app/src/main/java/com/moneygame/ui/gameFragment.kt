package com.moneygame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.moneygame.R
import com.moneygame.R.id.action_gameFragment_to_finalFragment
import com.moneygame.databinding.FragmentGameBinding
import com.moneygame.vm.ViewModelAll


class GameFragment : Fragment() {
    private val viewModelAll: ViewModelAll by activityViewModels()
    private var binding: FragmentGameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentGameBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = viewModelAll
            lifecycleOwner = viewLifecycleOwner
            fragmentGame = this@GameFragment
        }
    }

    fun goToScore() {
        findNavController().navigate(action_gameFragment_to_finalFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}