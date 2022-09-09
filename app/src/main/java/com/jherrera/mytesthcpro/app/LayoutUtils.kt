package com.jherrera.mytesthcpro.app

import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.jherrera.mytesthcpro.R

class LayoutUtils {
    companion object {
        //Show information box on the screen
        fun showSnack(view: View, message: String) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.setTextColor(ResourcesCompat.getColor(view.context.resources, R.color.white,null))
            snackbar.setBackgroundTint(ResourcesCompat.getColor(view.context.resources, R.color.primary,null))
            snackbar.show()
        }
    }
}