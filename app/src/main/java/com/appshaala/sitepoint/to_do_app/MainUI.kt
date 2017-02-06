package com.appshaala.sitepoint.to_do_app

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ListView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.floatingActionButton
/**
 * Created by Ankul on 28-Jan-17.
 */

class MainUI(val todoAdapter: TodoAdapter) : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        return relativeLayout {
            var todoList : ListView? =null

            //textView displayed when there is no task
            val hintListView = textView("What's your Todo List for today?") {
                textSize = 20f
            }.lparams {
                centerInParent()
            }

            //function to show or hide above textView
            fun showHideHintListView(listView: ListView) {
                if (getTotalListItems(listView)>0) {
                    hintListView.visibility = View.GONE
                } else {
                    hintListView.visibility = View.VISIBLE
                }
            }

            //layout to display ListView
            verticalLayout {
                todoList=listView {
                    adapter = todoAdapter
                    onItemLongClick { adapterView, view, i, l ->
                        val options = listOf("Completed","In Progress","Not Started","Delete")
                        selector("Task Options", options) { j ->
                            if (j == 3) {
                                var task=adapter.getItem(i)
                                todoAdapter?.delete(i)
                                showHideHintListView(this@listView)
                                longToast("Task ${task} has been deleted")
                            }else{
                                longToast("Task ${adapter.getItem(i).toString()} has been marked as \"${options[j]}\"")
                            }
                        }
                        true
                    }
                }
            }.lparams {
                    margin = dip(5)
            }

            //Add task FloatingActionButton at bottom right
            floatingActionButton {
                imageResource = android.R.drawable.ic_input_add
                onClick {
                    val adapter = todoList?.adapter as TodoAdapter
                    alert {
                        customView {
                            verticalLayout {
                                toolbar {
                                    id = R.id.dialog_toolbar
                                    lparams(width = matchParent, height = wrapContent)
                                    backgroundColor = ContextCompat.getColor(ctx, R.color.colorAccent)
                                    title = "What's your next milestone?"
                                    setTitleTextColor(ContextCompat.getColor(ctx, android.R.color.white))
                                }
                                val task = editText {
                                    hint = "To do task "
                                    padding = dip(20)
                                }
                                positiveButton("Add") {
                                    if(task.text.toString().isEmpty()) {
                                        toast("Oops!! Your task says nothing!")
                                    }
                                    else {
                                        adapter.add(task.text.toString())
                                        showHideHintListView(todoList!!)
                                    }
                                }
                            }
                        }
                    }.show()
                }
            }.lparams {
                //setting button to bottom right of the screen
                margin = dip(10)
                alignParentBottom()
                alignParentEnd()
                alignParentRight()
                gravity = Gravity.BOTTOM or Gravity.END
            }
        }.apply {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                    .apply {
                        leftMargin = dip(5)
                        rightMargin = dip(5)
                    }
        }

    }

    //function to get total number of items in list
    fun getTotalListItems(list: ListView?) = list?.adapter?.count ?: 0
}

