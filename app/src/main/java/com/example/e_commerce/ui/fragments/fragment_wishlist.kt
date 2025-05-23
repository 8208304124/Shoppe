package com.example.e_commerce.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.e_commerce.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [androidx.fragment.app.Fragment] subclass.
 * Use the [fragment_wishlist.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_wishlist : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container = view.findViewById<LinearLayout>(R.id.frameContainer)

        val userMap = mapOf(
            "sakshi" to R.drawable.circle_girl_5,
            "mansi" to R.drawable.circle_girl_2,
            "bishu" to R.drawable.circle_girl_4,
            "seema" to R.drawable.circle_girl_3,
            "priya" to R.drawable.circle_girl_1
        )

        for ((name, imageRes) in userMap) {
            val frameLayout = FrameLayout(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    resources.getDimensionPixelSize(R.dimen.img_outer_circles_size),
                    resources.getDimensionPixelSize(R.dimen.img_outer_circles_size)
                ).apply {
                    marginEnd = 35
                }
                background = ContextCompat.getDrawable(requireContext(), R.drawable.lets_get_started_ovalshape)
                elevation = 6f
            }

            val imageView = ImageView(requireContext()).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                ).apply {
                    setPadding(10, 10, 10, 10)
                }
                setImageResource(imageRes)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            frameLayout.addView(imageView)
            container.addView(frameLayout)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_wishlist.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_wishlist().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}