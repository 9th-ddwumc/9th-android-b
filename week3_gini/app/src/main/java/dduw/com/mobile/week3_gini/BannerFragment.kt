package dduw.com.mobile.week3_gini

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dduw.com.mobile.week3_gini.databinding.FragmentBannerBinding //데이터 바인딩

// 이미지 리소스 ID를 저장할 상수 정의
private const val ARG_IMAGE_RES = "image_res"

/**
 * A simple [Fragment] subclass.
 * Use the [BannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BannerFragment : Fragment() {
    //바인딩 객체 선언
    private lateinit var binding: FragmentBannerBinding

    //이미지 리소스 ID를 저장할 변수 (Int 타입)
    private var imageResId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //Bundle에서 Int 타입의 이미지 리소스 ID 가져옴
            imageResId = it.getInt(ARG_IMAGE_RES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BannerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(imageRes: Int) =
            BannerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IMAGE_RES, imageRes)
                }
            }
    }
}