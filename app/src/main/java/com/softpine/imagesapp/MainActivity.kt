package com.softpine.imagesapp

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.softpine.imagesapp.ui.ImageData
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var files:Array<File>
    private lateinit var filesList: ArrayList<Any?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        getData()

    }


    fun getMsj(mensaje: String): Boolean{

        return true
    }

    private fun setMsj(mss: String): String? {
        return null
    }

    private fun getData(): ArrayList<Any?>? {
        var f: ImageData
        //ubicacion
        val targetPath: String = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Pictures"
        val targetDirector = File(targetPath)
        files = targetDirector.listFiles()
        if (files == null) {
//            noImageText.setVisibility(View.INVISIBLE);
        }
        try {
            Arrays.sort(files) { p0, p1 ->
                if ((p0 as File).lastModified() > (p1 as File).lastModified()) {
                    -1
                } else if ((p0 as File).lastModified() < (p1 as File).lastModified()) {
                    +1
                } else {
                    0
                }
            }

            for (i in files.indices) {
                val file: File = files.get(i)
                f = ImageData()
                f.name = ""
                f.uri = Uri.fromFile(file)
                f.path = files[i].absolutePath
                f.filename = file.name
                filesList.add(f)

                img_hola.setImageURI(f.uri)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return filesList
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

//ciclo de vida del activity