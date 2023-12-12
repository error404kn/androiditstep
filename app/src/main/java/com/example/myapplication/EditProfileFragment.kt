package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.nameET.setText(viewModel.name)
        binding.updateBtn.setOnClickListener{
            viewModel.name = binding.nameET.text.toString()
            navigate()
        }
        binding.back.setOnClickListener{
            navigate()
        }
    }

    private fun navigate(){
        findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment6)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}