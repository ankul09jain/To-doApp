package com.appshaala.sitepoint.to_do_app

import android.view.View
import org.jetbrains.anko.*

class Demo : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        return verticalLayout{
            imageView(R.drawable.anko_logo).
                    lparams(width= matchParent) {
                        padding = dip(20)
                        margin = dip(15)
            }
            button("Tap to Like") {
                    onClick { toast("Thanks for the love!") }
            }
        }
        }
}