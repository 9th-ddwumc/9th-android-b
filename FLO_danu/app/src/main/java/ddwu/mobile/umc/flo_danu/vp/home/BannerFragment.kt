package ddwu.mobile.umc.flo_danu.vp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ddwu.mobile.umc.flo_danu.databinding.FragmentBannerBinding

class BannerFragment() : Fragment() {

    constructor(imgRes: Int) : this() {
        arguments = Bundle().apply { putInt(ARG_IMG_RES, imgRes) }
    }

    private var imgRes: Int = 0
    private lateinit var binding: FragmentBannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imgRes = arguments?.getInt(ARG_IMG_RES) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBannerBinding.inflate(inflater, container, false)
        if (imgRes != 0) {
            binding.bannerImageIv.setImageResource(imgRes)
        }
        return binding.root
    }

    companion object {
        private const val ARG_IMG_RES = "arg_img_res"

        fun newInstance(imgRes: Int): BannerFragment = BannerFragment().apply {
            arguments = Bundle().apply { putInt(ARG_IMG_RES, imgRes) }
        }
    }
}