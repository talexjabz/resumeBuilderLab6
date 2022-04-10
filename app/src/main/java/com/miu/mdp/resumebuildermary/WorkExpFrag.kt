package com.miu.mdp.resumebuildermary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miu.mdp.resumebuildermary.databinding.FragmentEducationBinding
import com.miu.mdp.resumebuildermary.databinding.WorkExpItemBinding

class WorkExpFrag : Fragment() {

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
        educationBinding.sectionTitle.text = getString(R.string.work_experience)
        educationBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = WorkExperienceAdapter(dataSource.getExperience().experience)
        }
    }
}

class WorkExperienceAdapter(private val experiences: List<Experience>) : RecyclerView.Adapter<WorkExperienceAdapter.ExperienceViewHolder>() {
    lateinit var workExpItemBinding: WorkExpItemBinding

    class ExperienceViewHolder(private val binding: WorkExpItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(experience: Experience) {
            binding.companyName.text = experience.company
            binding.companyLocation.text = experience.location
            binding.roles.text = experience.description
            binding.yearsOfService.text = experience.duration
            Glide.with(binding.root.context)
                .load(experience.companyLogo)
                .into(binding.companyLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        workExpItemBinding = WorkExpItemBinding.inflate(LayoutInflater.from(parent.context))
        return ExperienceViewHolder(workExpItemBinding)
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        holder.bindTo(experiences[position])
    }

    override fun getItemCount(): Int {
        return experiences.size
    }

}
