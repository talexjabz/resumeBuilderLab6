package com.miu.mdp.resumebuildermary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.miu.mdp.resumebuildermary.databinding.FragmentLoginBinding

lateinit var dataSource: ResumeDataSource
var user: User? = null

class LoginFrag : Fragment() {

    private lateinit var loginFragBinder: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginFragBinder = FragmentLoginBinding.inflate(layoutInflater, container, false)
        setLogo()
        return loginFragBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSource = ResumeDataSource(requireContext())

        loginFragBinder.login.setOnClickListener {
            dataSource.also { resumeDataSource ->
                user = resumeDataSource.getUser(
                    loginFragBinder.email.text.toString()
                )

                if (user != null &&
                    loginFragBinder.password.text.toString() == "123"
                ) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                } else Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        dataSource.saveUser("Mary G Talemwa", "tjabz@gmail.com")
    }

    private fun setLogo() {
        Glide.with(requireContext())
            .load(
                "https://play-lh.googleusercontent.com/" +
                        "LTFZdtNXaVlhPpIq0lK26sMeYVFV9xm4WA89dMX3_pwsg2sE5dgefyQNbFLUwDKX9PS2"
            )
            .into(loginFragBinder.imageLogo)
    }
}
