package com.example.myapplication

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentProfileBinding
import org.intellij.lang.annotations.Language
import java.util.Locale
import kotlin.math.log

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAutoComplete()
        init()
    }

    private fun initAutoComplete(){
        val items = listOf("English(US)", "Georgia")
        val autoComplete: AutoCompleteTextView = binding.autoCompleteTV
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedLanguage = items[position]
                setLocale(selectedLanguage)

            }
    }

   private fun setLocale(selectedLanguange: String){
       val resourceKeyMapping = getResourceKeyMapping(selectedLanguange)
       val editProfileResourceId = resourceKeyMapping["edit_profile"] ?: R.string.edit_profile_en
       val changeLanguageResourceId = resourceKeyMapping["change_language"] ?: R.string.change_languange_en
       val logoutResourceId = resourceKeyMapping["logout"] ?: R.string.change_languange_en
       val profileChangeId = resourceKeyMapping["profile"] ?: R.string.change_languange_en

       val editProfile = getString(editProfileResourceId)
       val changeLanguage = getString(changeLanguageResourceId)
       val logout = getString(logoutResourceId)
       val profile = getString(profileChangeId)


       binding.editProfileTV.text = editProfile
       binding.languangeChange.text = changeLanguage
       binding.logoutChange.text = logout
       binding.profileChange.text = profile

   }

    private fun getResourceKeyMapping(language: String): Map<String, Int> {
        return when (language) {
            "Georgia" -> mapOf(
                "edit_profile" to R.string.edit_profile_ka,
                "change_language" to R.string.change_languange_ka,
                "logout" to R.string.logout_ka,
                "profile" to R.string.profile_ka
            )
            else -> mapOf(
                "edit_profile" to R.string.edit_profile_en,
                "change_language" to R.string.change_languange_en,
                "logout" to R.string.logout_en,
                "profile" to R.string.profile_en
            )
        }
    }



    private fun init(){
        binding.name.text = viewModel.name

        binding.editProfileLL.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment8)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}