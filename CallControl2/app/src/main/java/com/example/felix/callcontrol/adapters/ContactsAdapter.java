package com.example.felix.callcontrol.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felix.callcontrol.R;

import static android.R.attr.name;

/**
 * Created by Felix on 10/15/2016.
 */

public class ContactsAdapter extends CursorAdapter {

    ListView listview;
    TextView textview1;
    TextView textview2;
    ImageView concallimg;

    public ContactsAdapter(Context context, Cursor cursor) {
        super(context,cursor,true);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        textview1=(TextView) view.findViewById(R.id.contextview1);
        textview2=(TextView) view.findViewById(R.id.contextview2);
        concallimg=(ImageView) view.findViewById(R.id.concallimg);
        String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        textview1.setText(name);
        textview2.setText(number);

        concallimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                Toast.makeText(context,"You clicked on position:"+position,Toast.LENGTH_LONG).show();
                try {


                    Cursor cursor = context.getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+ " ASC");
                    cursor.moveToPosition(position);

                    String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Toast.makeText(context,"Phone:"+phone,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+phone ));
                    context.startActivity(intent);



                }
                catch(SecurityException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return  inflater.inflate(R.layout.contacts_row, parent,false);
    }


}
