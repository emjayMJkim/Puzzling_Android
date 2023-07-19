package com.puzzling.puzzlingaos.presentation.invitationCode

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentInputCodeBinding
import com.puzzling.puzzlingaos.util.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class InputCodeFragment : BaseFragment<FragmentInputCodeBinding>(R.layout.fragment_input_code) {

    lateinit var viewModel: InvitationCodeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
        )[InvitationCodeViewModel::class.java]

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        isCodeSuccess()
    }

    private fun isCodeSuccess() {
        lifecycleScope.launch {
            viewModel.isCodeSuccess.collect {
                if (it != null && it) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_invitation_main, InputProfileFragment())
                        .commit()
                }
            }
        }
    }
}
