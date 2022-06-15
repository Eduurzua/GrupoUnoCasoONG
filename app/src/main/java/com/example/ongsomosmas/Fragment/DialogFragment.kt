package com.example.ongsomosmas.Fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.ongsomosmas.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogFragment(message: String) : DialogFragment(){

    private val alertMessage = message
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.tituloError))
            .setMessage(alertMessage)
            .setPositiveButton(getString(R.string.botonError)) { _,_ -> }
            .create()
    }
    companion object {
        const val TAG = "AlertDialog"
    }
}