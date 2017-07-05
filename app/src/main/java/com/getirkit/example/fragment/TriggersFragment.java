package com.getirkit.example.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.getirkit.example.R;
import com.getirkit.example.activity.MainActivity;
import com.getirkit.example.adapter.TriggerListAdapter;
import com.getirkit.irkit.IRKit;

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

    private TriggersFragmentListener listener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_triggers, container, false);
        ListView TriggersListView = (ListView) rootView.findViewById(R.id.triggers__listview);
        MainActivity mainActivity = (MainActivity) getActivity();
        TriggerListAdapter triggerListAdapter = new TriggerListAdapter(mainActivity, IRKit.sharedInstance().peripherals);
        TriggersListView.setAdapter(triggerListAdapter);
        mainActivity.setTriggerListAdapter(triggerListAdapter);
        TriggersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (listener != null) {
                    listener.onTriggerClick(position);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));

        try {
            listener = (TriggersFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(("Activity must implement TriggersFragmentListener"));
        }
    }

}
