package com.example.neverforgetxx;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MySizeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MySizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MySizeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     *
     * @param context
     * @return
     */
    public static Fragment newInstance(Context context){
        MySizeFragment f = new MySizeFragment();
        return f;
    }
    /*
    public MySizeFragment() {
        // Required empty public constructor
    }
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_my_size,null);
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences prefs = this.getActivity().getSharedPreferences("mysize", Context.MODE_PRIVATE);
        int neck = prefs.getInt("neck", 0);
        int sleeve = prefs.getInt("sleeve", 0);
        int waist = prefs.getInt("waist", 0);
        int insideLeg = prefs.getInt("insideLeg", 0);
        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        if (neck != 0) {
            edText1.setText(Integer.toString(neck));
        }
        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        if (sleeve != 0) {
            edText2.setText(Integer.toString(sleeve));
        }
        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        if (waist != 0) {
            edText3.setText(Integer.toString(waist));
        }
        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);
        if (insideLeg != 0) {
            edText4.setText(Integer.toString(insideLeg));
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);
        int neck;
        // ここで例外をキャッチして抜ける
        try {
            neck = Integer.parseInt(edText1.getText().toString());
        }
        catch (NumberFormatException e) {
            neck = 0;
        }
        int sleeve;
        try {
            sleeve = Integer.parseInt(edText2.getText().toString());
        }
        catch (NumberFormatException e) {
            sleeve = 0;
        }
        int waist;
        try {
            waist = Integer.parseInt(edText3.getText().toString());
        }
        catch (NumberFormatException e) {
            waist = 0;
        }
        int insideLeg;
        try {
            insideLeg = Integer.parseInt(edText4.getText().toString());
        }
        catch (NumberFormatException e) {
            insideLeg = 0;
        }

        // 保存
        SharedPreferences prefs = this.getActivity().getSharedPreferences("mysize", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("neck",neck);
        editor.putInt("sleeve",sleeve);
        editor.putInt("waist",waist);
        editor.putInt("insideLeg",insideLeg);
        //editor.commit();
        editor.apply();     // commitの非同期
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
