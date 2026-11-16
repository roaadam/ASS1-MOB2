package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Button;
import android.content.Context;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";

    private String name;

    public Fragment3() {
        // Required empty public constructor
    }


    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);

        TextView textViewName = view.findViewById(R.id.textViewName);
        textViewName.setText(name);
        CheckBox checkBox = view.findViewById(R.id.checkBoxConfirm);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mListener.onCheckBoxChanged(isChecked);
        });

        return view;
    }
    public void updateButton(boolean isChecked) {

        Button button = getView().findViewById(R.id.buttonFinish);

        button.setEnabled(isChecked);

        if (isChecked) {
            button.setText("Finish");
        } else {
            button.setText("Continue");
        }
    }
    public interface OnCheckBoxChangeListener {
        void onCheckBoxChanged(boolean isChecked);
    }

    private OnCheckBoxChangeListener mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnCheckBoxChangeListener) {
            mListener = (OnCheckBoxChangeListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnCheckBoxChangeListener");
        }
    }


}
