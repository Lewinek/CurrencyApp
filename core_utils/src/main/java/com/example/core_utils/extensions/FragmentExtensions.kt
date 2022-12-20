package com.example.core_utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(msgResId: String) {
    Toast.makeText(requireContext(), msgResId, Toast.LENGTH_SHORT).show()
}