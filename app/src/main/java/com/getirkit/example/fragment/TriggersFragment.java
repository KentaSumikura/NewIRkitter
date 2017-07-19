package com.getirkit.example.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.getirkit.example.R;
import com.getirkit.example.activity.CallConf;
import com.getirkit.example.activity.MainActivity;

import java.sql.Time;


/**
 * Created by user on 2017/07/05.
 */

public class TriggersFragment extends Fragment{

    public static final String TAG = TriggersFragment.class.getSimpleName();
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    public interface TriggersFragmentListener {
        void onTriggerClick(int position);
    }


    //private TriggersFragmentListener listener;
    private MainActivity list;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    public static TriggersFragment newInstance(int sectionNumber) {
        TriggersFragment fragment = new TriggersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TriggersFragment() {
        
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_triggers, container, false);
        ImageButton VoiceButton = (ImageButton)rootView.findViewById(R.id.Voice_button);
        VoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.VoiceIntent();
            }
        });

        ImageButton WifiButton = (ImageButton)rootView.findViewById(R.id.Wifi_button);
        WifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.WifiIntent();
            }
        });

        ImageButton CallingButton = (ImageButton)rootView.findViewById(R.id.Calling_button);
        CallingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.CallIntent();
            }
        });

        ImageButton TimerButton = (ImageButton)rootView.findViewById(R.id.Timer_button);
        TimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.TimerIntent();
            }
        });


        ImageButton Weatherbutton = (ImageButton)rootView.findViewById(R.id.Weather_button);
        Weatherbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.WeatherIntent();
            }
        });

        ImageButton Timerbutton = (ImageButton)rootView.findViewById(R.id.Timer_button);
        Timerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.TimerIntent();
            }
        });

        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        list = (MainActivity) activity;
        super.onAttach(activity);

        }

}
