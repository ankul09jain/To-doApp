package com.appshaala.sitepoint.to_do_app

import android.view.View
import android.widget.FrameLayout
import org.jetbrains.anko.*

class Demo : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        return verticalLayout{
            id = R.id.container

            imageView(R.drawable.anko_logo).lparams {
                padding = dip(15)
            }

               button("Tap to Like") {
                   id=R.id.likeBtn
                    onClick { toast("Thanks for the love!") }
                }
        }.apply {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                    .apply {
                        leftMargin = dip(5)
                        rightMargin = dip(5)
                    }
        }
        }
}