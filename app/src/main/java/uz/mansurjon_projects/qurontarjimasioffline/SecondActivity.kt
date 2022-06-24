package uz.mansurjon_projects.qurontarjimasioffline

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.mansurjon_projects.qurontarjimasioffline.adapter.RvAdapter2
import uz.mansurjon_projects.qurontarjimasioffline.databinding.BottomSheetBinding
import uz.mansurjon_projects.qurontarjimasioffline.models.quran.Quran
import uz.mansurjon_projects.qurontarjimasioffline.models.quran.QuranX
import uz.mansurjon_projects.qurontarjimasioffline.models.quranInfo.Chapter

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.hide()

        val chapterList = intent.getSerializableExtra("chapter") as Chapter

        val jsonFileString = getJsonDataFromAsset(applicationContext, "quran_uzb.json")!!
        val jsonFileString2 = getJsonDataFromAsset(applicationContext, "quran_original.json")!!

        val gson = Gson()

        val lisType = object : TypeToken<Quran>() {}.type

        val quranTranslatedText: Quran = gson.fromJson(jsonFileString, lisType)
        val quranText2: Quran = gson.fromJson(jsonFileString2, lisType)

        val list2 = ArrayList<QuranX>()
        val list3 = ArrayList<QuranX>()

        val count = chapterList.verses.size
        var count2 = 0

        for (i in quranTranslatedText.quran) {
            if (chapterList.chapter == i.chapter) {
                Log.d("Looping", "$count2 => $count")
                list2.add(i)
                if (count2 == count) break else count2 += 1
            }
        }

        val count3 = chapterList.verses.size
        var count4 = 0

        for (i in quranText2.quran) {
            if (chapterList.chapter == i.chapter) {
                Log.d("Looping2", "$count4 => $count3")
                list3.add(i)
                if (count4 == count3) break else count4 += 1
            }
        }

        findViewById<TextView>(R.id.name).text = chapterList.name

        findViewById<RecyclerView>(R.id.rv2).adapter =
            RvAdapter2(list2, list3, object : RvAdapter2.rvClickArabic {
                @SuppressLint("SetTextI18n")
                override fun clickArabic(chapter: QuranX, position: Int) {
                    val d = BottomSheetDialog(this@SecondActivity)

                    val i = BottomSheetBinding.inflate(layoutInflater)
                    i.textAyah.text = list3[position].text
                    i.detail.text =
                        "${chapter.verse} - آية, ${chapterList.verses[position].juz} - جُزْءْ"

                    i.rootBottomSheet.setOnClickListener {
                        copyText("${list3[position].text.trim()} \n(${chapterList.arabicname}, ${chapter.verse}-آية)")
                    }

                    d.setContentView(i.root)
                    d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    d.setCancelable(true)
                    d.show()

                }

            }, object : RvAdapter2.rvClickUzbek {
                @SuppressLint("SetTextI18n")
                override fun clickUzbek(chapter: QuranX, position: Int) {
                    val d = BottomSheetDialog(this@SecondActivity)

                    val i = BottomSheetBinding.inflate(layoutInflater)
                    i.textAyah.text = chapter.text
                    i.detail.text =
                        "${chapter.verse} - Оят , ${chapterList.verses[position].juz} - Жуз"

                    i.rootBottomSheet.setOnClickListener {
                        copyText("${chapter.text.trim()} \n(${chapterList.name}, ${chapter.verse}-Оят)")
                    }

                    d.setContentView(i.root)
                    d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    d.setCancelable(true)
                    d.show()
                }

            })

    }

    @SuppressLint("ServiceCast")
    private fun copyText(copyText: String) {
        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", copyText)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Нусха кўчирилди", Toast.LENGTH_SHORT).show()
    }

}