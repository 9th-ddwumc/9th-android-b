package dduw.com.mobile.week6_gini.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week6_gini.R
import dduw.com.mobile.week6_gini.data.LockerSavedSongData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SaveSongFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SaveSongFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_save_song, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SaveSongFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SaveSongFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val SongDataList = mutableListOf<LockerSavedSongData>().apply {
            add(LockerSavedSongData("라일락(LILAC)", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("Flu", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("Coin", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("봄 안녕 봄", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("Celebrity", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("돌림노래 (Feat. DEAN)", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("빈 컵 (Empty Cup)", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("아이와 나의 바다", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("어푸 (Ah puh)", "아이유(IU)", R.drawable.img_album_exp2))
            add(LockerSavedSongData("에필로그", "아이유(IU)", R.drawable.img_album_exp2))
        }

        lateinit var albumAdapter: LockerSavedSongRecycleAdapter
        albumAdapter = LockerSavedSongRecycleAdapter(SongDataList) { position ->
            albumAdapter.deleteItem(position)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.save_song_recyclerview)

        recyclerView.adapter = albumAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
}