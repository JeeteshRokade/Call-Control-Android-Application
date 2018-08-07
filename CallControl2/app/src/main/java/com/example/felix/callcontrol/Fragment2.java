package com.example.felix.callcontrol;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.felix.callcontrol.adapters.CallogAdapter;
import com.example.felix.callcontrol.adapters.ContactsAdapter;


public class Fragment2 extends Fragment {

    ListView listview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_fragment2, container, false);
        listview=(ListView) view.findViewById(R.id.contactslist);

        try {
            Cursor cursor = getActivity().getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+ " ASC");
            cursor.moveToFirst();
            ContactsAdapter contactsAdapter=new ContactsAdapter(getContext(),cursor);
            listview.setAdapter(contactsAdapter);
        }
        catch(SecurityException e)
        {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return view;
    }

}