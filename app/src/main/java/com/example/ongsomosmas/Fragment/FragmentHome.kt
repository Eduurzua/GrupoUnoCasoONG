package com.example.ongsomosmas.Fragment

import android.R
import android.R.menu
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ongsomosmas.databinding.ActivityHomeBinding


class FragmentHome : Fragment() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_popup , menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId;

        if (id == R.id.nav_home){
            Toast.makeText(context,"Redirigiendo al menu principal", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.nav_staff){
            Toast.makeText(context,"Redirigiendo a contactos", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.nav_news){
            Toast.makeText(context,"Redirigiendo a noticias", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.nav_contact){
            Toast.makeText(context,"Redirigiendo a contacto", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}