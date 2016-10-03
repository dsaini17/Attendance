package com.example.devesh.attendance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devesh.attendance.Models.DatabaseTable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener{

    public static final String TAG = "MainActivity";

    // Declaration
    RecyclerView myView;
    SQLiteDatabase myDatabase;
    ArrayList<Info> myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // List View Initialization


        // List Initialization
        myList = new ArrayList<>();


        // Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myView.setLayoutManager(linearLayoutManager);


        performUpdation();



    }

    public void performUpdation(){

        myList.clear();

        String proj[] = {
                DatabaseTable.Columns.SUBJECT_CODE,
                DatabaseTable.Columns.SUBJECT,
                DatabaseTable.Columns.TEACHER,
                DatabaseTable.Columns.ATTENDED,
                DatabaseTable.Columns.TOTAL,
                DatabaseTable.Columns.PAST
        };

        myDatabase = Database.getReadable(MainActivity.this);

        Cursor c = myDatabase.query(DatabaseTable.TABLE_NAME,proj,null,null,null,null,null);
        Log.d(TAG,"Count :- "+c.getCount());

        while (c.moveToNext()){

            String subj = c.getString(c.getColumnIndexOrThrow(DatabaseTable.Columns.SUBJECT));
            String subj_code = c.getString(c.getColumnIndexOrThrow(DatabaseTable.Columns.SUBJECT_CODE));
            String teacher = c.getString(c.getColumnIndexOrThrow(DatabaseTable.Columns.TEACHER));
            String pastrecord = c.getString(c.getColumnIndexOrThrow(DatabaseTable.Columns.PAST));
            int attended = c.getInt(c.getColumnIndexOrThrow(DatabaseTable.Columns.ATTENDED));
            int total = c.getInt(c.getColumnIndexOrThrow(DatabaseTable.Columns.TOTAL));
            int id = c.getInt(c.getColumnIndexOrThrow(DatabaseTable.Columns.ID));

            myList.add(new Info(subj_code,subj,teacher,pastrecord,attended,total,id));
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            createDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void createDialog(){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(),"XYZ");
    }

    @Override
    public void PositiveClick(Bundle args) {
        String s1 = args.getString("Subject_Code");
        String s2 = args.getString("Subject_Name");
        String s3 = args.getString("Teacher");

        Log.d(TAG,s1+" "+s2+" "+s3);

        myDatabase = Database.getWritable(MainActivity.this);

        ContentValues values = new ContentValues();
        values.put(DatabaseTable.Columns.SUBJECT_CODE,s1);
        values.put(DatabaseTable.Columns.SUBJECT,s2);
        values.put(DatabaseTable.Columns.TEACHER,s3);
        values.put(DatabaseTable.Columns.ATTENDED,0);
        values.put(DatabaseTable.Columns.TOTAL,0);
        values.put(DatabaseTable.Columns.PAST,"");

        myDatabase.insert(DatabaseTable.TABLE_NAME,null,values);

        performUpdation();

    }

    @Override
    public void NegativeClick() {
        Log.d(TAG,"Negative Callback");
    }

    public class ListViewAdapter extends BaseAdapter{

        private ArrayList<Info> mList;

        public ListViewAdapter(ArrayList<Info> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Info getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();

            view = layoutInflater.inflate(R.layout.list_item,null,false);

            TextView subj,sub_code,teacher,past,percent,details;
            Button add,remove,sub;

            details = (TextView) view.findViewById(R.id.show_info);
            subj = (TextView) view.findViewById(R.id.subj_name);
            percent = (TextView) view.findViewById(R.id.sub_per);

            add = (Button) view.findViewById(R.id.add);
            sub = (Button) view.findViewById(R.id.sub);
            remove = (Button) view.findViewById(R.id.remove);

            final Info info = getItem(i);

            subj.setText(info.getSubject());

            float fm;

            if(info.getTotal()==0)
                fm=0;
            else
                fm = (info.getAttended()/info.getTotal())*100;

            percent.setText(String.format("%.2f",fm));


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int UniqueID = info.getId();
                    myDatabase = Database.getWritable(getApplicationContext());
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseTable.Columns.ATTENDED,info.getAttended()+1);
                    contentValues.put(DatabaseTable.Columns.TOTAL,info.getTotal()+1);
                    String s=info.getPast()+"1";
                    contentValues.put(DatabaseTable.Columns.PAST,s);
                    myDatabase.update(DatabaseTable.TABLE_NAME,contentValues,DatabaseTable.Columns.ID+"="+UniqueID,null);
                    performUpdation();
                }
            });

            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int UniqueID = info.getId();
                    myDatabase = Database.getWritable(getApplicationContext());
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseTable.Columns.TOTAL,info.getTotal()+1);
                    String s=info.getPast()+"0";
                    contentValues.put(DatabaseTable.Columns.PAST,s);
                    myDatabase.update(DatabaseTable.TABLE_NAME,contentValues,DatabaseTable.Columns.ID+"="+UniqueID,null);
                    performUpdation();
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int UniqueID = info.getId();
                    myDatabase = Database.getWritable(getApplicationContext());
                    if(info.getTotal()==0)
                        Toast.makeText(getApplicationContext(),"Invalid Selection",Toast.LENGTH_SHORT);
                    else{
                        ContentValues contentValues = new ContentValues();
                        String s=info.getPast();
                        if(s.endsWith("1")){
                            contentValues.put(DatabaseTable.Columns.ATTENDED,info.getAttended()-1);
                            contentValues.put(DatabaseTable.Columns.TOTAL,info.getTotal()-1);
                            s=s.substring(0,s.length()-1);
                            contentValues.put(DatabaseTable.Columns.PAST,s);
                        }
                        else{
                            contentValues.put(DatabaseTable.Columns.TOTAL,info.getTotal()-1);
                            s=s.substring(0,s.length()-1);
                            contentValues.put(DatabaseTable.Columns.PAST,s);
                        }
                        myDatabase.update(DatabaseTable.TABLE_NAME,contentValues,DatabaseTable.Columns.ID+"="+UniqueID,null);
                        performUpdation();
                    }
                }
            });

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                }
            });

            return view;
        }
    }
}
