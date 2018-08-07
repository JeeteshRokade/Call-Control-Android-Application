package com.example.felix.callcontrol;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.felix.callcontrol.adapters.CallogAdapter;
import com.example.felix.callcontrol.adapters.ContactsAdapter;

import java.util.List;


public class Fragment1 extends Fragment {

    ListView listview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_fragment1, container, false);
        listview=(ListView) view.findViewById(R.id.calloglist);

        try {
            Cursor cursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
            cursor.moveToFirst();
            CallogAdapter callogAdapter=new CallogAdapter(getContext(),cursor);
            listview.setAdapter(callogAdapter);
        }
        catch(SecurityException e)
        {
            e.printStackTrace();
        }
        // Inflate the layout for this fragment
        return view;
    }

}
