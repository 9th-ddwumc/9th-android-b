package dduw.com.mobile.week6_gini.locker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week6_gini.R
import dduw.com.mobile.week6_gini.data.LockerSavedAlbumData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SaveAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SaveAlbumFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_save_album, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val AlbumDataList = mutableListOf<LockerSavedAlbumData>().apply {
            add(LockerSavedAlbumData("라일락(LILAC)", "아이유(IU)"," 2021.3.25 | 정규 | KPOP", R.drawable.img_album_exp2))
            add(LockerSavedAlbumData("butter (megan thee stallion remix)", "방탄소년단",  "2021.8.27 | 싱글 | 한국 댄스/일렉트로닉, K팝",R.drawable.img_album_exp))
        }

        lateinit var albumAdapter: LockerSavedAlbumRecycleAdapter
        albumAdapter = LockerSavedAlbumRecycleAdapter(AlbumDataList) { position ->
            albumAdapter.deleteItem(position)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.save_song_recyclerview)

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
         * @return A new instance of fragment SaveAlbumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SaveAlbumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}