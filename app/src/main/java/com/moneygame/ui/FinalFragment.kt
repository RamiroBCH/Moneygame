package com.moneygame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.moneygame.R
import com.moneygame.databinding.FragmentFinalBinding
import com.moneygame.vm.ViewModelAll

class FinalFragment : Fragment() {
    private val viewModelAll: ViewModelAll by activityViewModels()
    private var binding: FragmentFinalBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFinalBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            //nextButton.setOnClickListener { goToNextScreen() }
            viewModel = viewModelAll
            lifecycleOwner = viewLifecycleOwner
            fragmentFinal = this@FinalFragment
        }
    }
    fun goToGame() {
        viewModelAll.reiniciar()
        findNavController().navigate(R.id.action_finalFragment_to_gameFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}