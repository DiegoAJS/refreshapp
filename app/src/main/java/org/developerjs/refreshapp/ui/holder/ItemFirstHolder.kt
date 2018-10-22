package org.developerjs.refreshapp.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_first.view.*
import org.developerjs.refreshapp.interfaces.ItemClickListener
import org.developerjs.refreshapp.pojo.ItemFirst
import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView

class ItemFirstHolder(itemView: View, var listener: ItemClickListener):RecyclerView.ViewHolder(itemView), View.OnClickListener {


    init {
        itemView.setOnClickListener(this)
        itemView.animation_view_like.playAnimation()
    }

    override fun onClick(v: View?) {
        listener.onItemClick(v,adapterPosition)

    }

    fun bindNoticia(itemFirst: ItemFirst?) {
        with(itemFirst!!) {

            itemView.tvTituloItemBannerNoticia.text = itemFirst.title;

        }
    }
    private fun animation(animation: LottieAnimationView){
        animation.addAnimatorListener(object: Animator.AnimatorListener{

            override fun onAnimationRepeat(p0: Animator?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationEnd(p0: Animator?) {
                //itemView.animation_view_like.visibility = View.GONE
            }

            override fun onAnimationCancel(p0: Animator?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationStart(p0: Animator?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}