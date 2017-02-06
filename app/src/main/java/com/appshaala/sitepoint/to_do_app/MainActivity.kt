package com.appshaala.sitepoint.to_do_app

/**
 * Created by Ankul on 28-Jan-17.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import org.jetbrains.anko.*;
import java.util.*


class MainActivity : AppCompatActivity() {

    val task_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            val arrayList = savedInstanceState.get("ToDoList")
            task_list.addAll(arrayList as List<String>)
        }
        var adapter=TodoAdapter(task_list)
        var ui = MainUI(adapter)
        ui.setContentView(this)
//        Demo().setContentView(this)

    }
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putStringArrayList("ToDoList", task_list)
        super.onSaveInstanceState(outState)
    }
}
