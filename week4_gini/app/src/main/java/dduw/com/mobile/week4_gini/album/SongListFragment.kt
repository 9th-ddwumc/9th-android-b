package dduw.com.mobile.week4_gini.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dduw.com.mobile.week4_gini.R
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import dduw.com.mobile.week4_gini.data.AlbumFragmentData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SongListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var isToggele = false
    private val ON_DRAWABLE = R.drawable.btn_toggle_on
    private val OFF_DRAWABLE = R.drawable.btn_toggle_off

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
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    // 뷰가 완전히 생성된 후 여기에 로직 작성
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰 찾기
        val songMixTextView: TextView = view.findViewById(R.id.song_mix_text_bg)
        val toggleIcon: ImageView = view.findViewById(R.id.song_mix_toggle_icon)

        songMixTextView.setOnClickListener {
            isToggele = !isToggele

            val newIconId = if (isToggele) {
                ON_DRAWABLE
            } else {
                OFF_DRAWABLE
            }
            toggleIcon.setImageResource(newIconId)
        }

        val albumDataList = mutableListOf<AlbumFragmentData>().apply {
            add(AlbumFragmentData("1", "라일락(LILAC)", "아이유(IU)"))
            add(AlbumFragmentData("2", "Flu", "아이유(IU)"))
            add(AlbumFragmentData("3", "Coin", "아이유(IU)"))
            add(AlbumFragmentData("4", "봄 안녕 봄", "아이유(IU)"))
            add(AlbumFragmentData("5", "Celebrity", "아이유(IU)"))
            add(AlbumFragmentData("6", "돌림노래 (Feat. DEAN)", "아이유(IU)"))
            add(AlbumFragmentData("7", "빈 컵 (Empty Cup)", "아이유(IU)"))
            add(AlbumFragmentData("8", "아이와 나의 바다", "아이유(IU)"))
            add(AlbumFragmentData("9", "어푸 (Ah puh)", "아이유(IU)"))
            add(AlbumFragmentData("10", "에필로그", "아이유(IU)"))
        }

        val albumAdapter = AlbumRecycleAdapter(albumDataList)

        val recyclerView: RecyclerView = view.findViewById(R.id.list_item_song_recyclerview)

        recyclerView.adapter = albumAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SongListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}