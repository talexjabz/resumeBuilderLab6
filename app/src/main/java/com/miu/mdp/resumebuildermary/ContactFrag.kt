package com.miu.mdp.resumebuildermary

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.mdp.resumebuildermary.databinding.ContactItemBinding
import com.miu.mdp.resumebuildermary.databinding.FragmentEducationBinding
import java.util.*

class ContactFrag : Fragment() {
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
        educationBinding.sectionTitle.text = getString(R.string.contact)
        educationBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ContactsAdapter(dataSource.getContactDetails())
        }
    }

    class ContactsAdapter(private val contacts: List<Contact>): RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {
        private lateinit var binding: ContactItemBinding

        override fun getItemCount(): Int = contacts.size

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            holder.bindTo(contacts[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context))
            return ContactViewHolder(binding)
        }

        inner class ContactViewHolder(private val binding: ContactItemBinding): RecyclerView.ViewHolder(binding.root) {
            fun bindTo(contact: Contact) {
                binding.uriDetail.text = contact.contactType.name.lowercase().replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                binding.uriDesc.text = contact.contactData

                binding.layout.setOnClickListener {

                    val intent: Intent = when(contact.contactType) {
                        ContactType.PHONE -> {
                            Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${contact.contactData}")
                            }
                        }
                        ContactType.EMAIL -> {
                            Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:")
                                putExtra(Intent.EXTRA_EMAIL, contact.contactData)
                                putExtra(Intent.EXTRA_SUBJECT, "Mary G Talemwa Resume")
                            }
                        }
                        ContactType.LINK -> {
                            Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(contact.contactData)
                            }
                        }
                    }
                    val context = binding.root.context
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "No app to open this", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
