package uz.mansurjon_projects.qurontarjimasioffline.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import uz.mansurjon_projects.qurontarjimasioffline.databinding.ItemRvBinding
import uz.mansurjon_projects.qurontarjimasioffline.models.quranInfo.Chapter

class RvAdapter(private var list: List<Chapter>, val rvClick: rv_click) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(chapter: Chapter, position: Int) {

            itemRv.name.text = chapter.name
            itemRv.pos.text = (position+1).toString()

            val location = if (chapter.revelation == "Mecca"){
                "Makkada nozil bo'lgan"
            }else{
                "Madinada nozil bo'lgan"
            }

            val countOfVerse = chapter.verses.size

            itemRv.location.text = "$location | $countOfVerse oyatdan iborat"
            itemRv.root.setOnClickListener {
                rvClick.click(chapter, position)
            }
        }
    }



    interface rv_click {
        fun click(chapter: Chapter, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
        setAnimation(viewToAnimate = holder.itemView, position=position)
    }

    override fun getItemCount(): Int = list.size
}

private fun setAnimation(viewToAnimate: View, position: Int) {
        val anim = ScaleAnimation(0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f)
        anim.duration = 200
        viewToAnimate.startAnimation(anim)

}

