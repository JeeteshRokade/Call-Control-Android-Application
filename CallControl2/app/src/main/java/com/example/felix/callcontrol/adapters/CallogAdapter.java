package com.example.felix.callcontrol.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.targetActivity;
import static android.R.attr.type;

/**
 * Created by Felix on 10/15/2016.
 */

public class CallogAdapter extends CursorAdapter {

    ListView listview;
    TextView textview1;
    TextView textview2;
    TextView textview3;
    TextView textview4;
    ImageView calltype;
    ImageView callimg;

    public static final int INCOMING_TYPE=1;
    public static final int OUTGOING_TYPE=2;
    public static final int MISSED_TYPE=3;

    public CallogAdapter(Context context, Cursor cursor) {
        super(context,cursor,true);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        calltype=(ImageView) view.findViewById(R.id.calltype);
        callimg=(ImageView) view.findViewById(R.id.callimg);
        textview1=(TextView) view.findViewById(R.id.textview1);
        textview2=(TextView) view.findViewById(R.id.textview2);
        textview3=(TextView) view.findViewById(R.id.textview3);
        textview4=(TextView) view.findViewById(R.id.textview4);
        String type= cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));

        switch(Integer.parseInt(type))
        {
            case CallLog.Calls.INCOMING_TYPE:
                calltype.setImageResource(R.drawable.out);
                break;
            case CallLog.Calls.OUTGOING_TYPE:
                calltype.setImageResource(R.drawable.in);
                break;
            case CallLog.Calls.MISSED_TYPE:
                calltype.setImageResource(R.drawable.missed);
                break;
        }

        String name=cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
        String number=cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
        int secondindex = cursor.getColumnIndex(CallLog.Calls.DATE);
        long seconds=cursor.getLong(secondindex);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
        String dateString = formatter.format(new Date(seconds));

        String duration="Duration:"+cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION))+"s";
        String date = "Date:"+dateString ;



        textview1.setText(name);
        textview2.setText(number);
        textview3.setText(date);
        textview4.setText(duration);

        callimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                Toast.makeText(context,"You clicked on position:"+position,Toast.LENGTH_SHORT).show();
                try {


                    Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
                    cursor.moveToPosition(position);

                    String phone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    Toast.makeText(context,"Phone:"+phone,Toast.LENGTH_SHORT).show();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+phone));
                    context.startActivity(callIntent);


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

        return  inflater.inflate(R.layout.callog_row, parent,false);
    }

}

