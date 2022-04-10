package com.miu.mdp.resumebuildermary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miu.mdp.resumebuildermary.databinding.EducItemBinding
import com.miu.mdp.resumebuildermary.databinding.FragmentEducationBinding

class EducationFrag : Fragment() {

    private lateinit var educationBinding: FragmentEducationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        educationBinding = FragmentEducationBinding.inflate(layoutInflater, container, false)
        return educationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        educationBinding.sectionTitle.text = getString(R.string.education)
        educationBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = EducAdapter(dataSource.getEducation().schools)
        }
    }

    class EducAdapter(private val schools: List<School>): RecyclerView.Adapter<EducAdapter.EducViewHolder>() {
        lateinit var educItemBinding: EducItemBinding

        override fun getItemCount(): Int {
            return schools.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducViewHolder {
            educItemBinding = EducItemBinding.inflate(LayoutInflater.from(parent.context))
            return EducViewHolder(educItemBinding)
        }

        override fun onBindViewHolder(holder: EducViewHolder, position: Int) {
            val school = schools[position]
            holder.bindTo(school)

        }

        inner class EducViewHolder(private val binding: EducItemBinding): RecyclerView.ViewHolder(binding.root) {
            fun bindTo(school: School) {
                Glide.with(binding.root.context)
                    .load(school.logo)
                    .into(binding.schoolLogo)
                binding.courseTaken.text = school.courseTaken
                binding.schoolName.text = school.name
            }
        }
    }
}
