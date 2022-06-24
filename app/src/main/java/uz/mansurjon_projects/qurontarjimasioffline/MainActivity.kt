package uz.mansurjon_projects.qurontarjimasioffline

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.mansurjon_projects.qurontarjimasioffline.adapter.RvAdapter
import uz.mansurjon_projects.qurontarjimasioffline.models.quranInfo.Chapter
import uz.mansurjon_projects.qurontarjimasioffline.models.quranInfo.QuranInfo
import java.io.IOException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val jsonFileString = getJsonDataFromAsset(applicationContext, "names.json")!!
        Log.i("data", jsonFileString)
        val gson = Gson()
        val lisType = object : TypeToken<QuranInfo>() {}.type
        val quranNames: QuranInfo = gson.fromJson(jsonFileString, lisType)

        findViewById<RecyclerView>(R.id.rv).adapter =
            RvAdapter(quranNames.chapters, object : RvAdapter.rv_click {
                override fun click(chapter: Chapter, position: Int) {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra("chapter", chapter)
                    startActivity(intent)
                }
            })

        /* findViewById<androidx.appcompat.widget.SearchView>(R.id.search).setOnQueryTextListener(object : SearchView.OnQueryTextListener,
             androidx.appcompat.widget.SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(p0: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(p0: String?): Boolean {

                  return false
             }

         })*/

    }


}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}


