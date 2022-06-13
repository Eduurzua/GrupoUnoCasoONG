package com.example.ongsomosmas.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ongsomosmas.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding : /*añadir activity login*/;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)    /*añadir activity login*/
        setContentView(binding.root)

        /*el boton comienza desactivado*/
        binding./*botonlogin*/.isEnabled = false

        val email /*añadir campo email en login*/;
        val password /*añadir campo password en login*/;
        val result: Boolean


        binding./*botonologin*/.setOnClickListener{
            val intent = Intent(this, /* dirigir al main*/::class.java)

        }

        setObservers()
        setListeners(email, password)

    }

    private fun setListeners(email: String, password: String) {
        binding./*boton login*/.setOnClickListener{
            viewModel.validateAll(email,password)
        }
    }

    /*de momento solo muestra toast, a futuro debe redirigir al menu principal*/
    private fun setObservers() {
        viewModel.showToast.observe(this){ value ->
            if(value){
                Toast.makeText(this,"Login exitoso", Toast.LENGTH_SHORT).show()
            }

        }
    }
}