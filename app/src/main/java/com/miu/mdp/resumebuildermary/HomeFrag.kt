package com.miu.mdp.resumebuildermary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.miu.mdp.resumebuildermary.databinding.FragmentHomeBinding

class HomeFrag : Fragment() {

    lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(user?.profilePic)
            .into(homeBinding.profilePic)

        with(homeBinding) {
            name.text = user?.fullName
            profession.text = user?.profession
            description.text = user?.description
            tools.text = user?.tools?.map { entry ->
                entry.key + ": "+entry.value
            }?.reduce { a, b ->
                a +"\n" + b + "\n"
            }
        }
    }
}
