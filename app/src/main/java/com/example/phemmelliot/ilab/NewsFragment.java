package com.example.phemmelliot.ilab;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private View rootView  = null;
    private ArrayList<News> NewsList;
    private NewsAdapter adapter;
    private ListView listView;

    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        NewsList = new ArrayList<>();
        adapter = new NewsAdapter(getActivity(), NewsList);
        prepareNews();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.drawer_list, container, false);

//        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }
    private void prepareNews() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        News a = new News("True Romance", "Is it really a true romance, well if you say so so shall it be", "www.google.com", covers[0]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[1]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[2]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[3]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[4]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[5]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[6]);
        NewsList.add(a);
        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[7]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[8]);
        NewsList.add(a);

        a = new News("Kotlin Replaces Java", "There was an outburst on wednesday when google announced that kotlin can now officially replace java in android development" +
                "Which according to people happens because oracle was trying to sue google for using their language", "http:google.com",covers[9]);
        NewsList.add(a);






        adapter.notifyDataSetChanged();

    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
     public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
