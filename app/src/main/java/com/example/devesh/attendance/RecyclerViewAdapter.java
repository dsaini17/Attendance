package com.example.devesh.attendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by devesh on 5/9/16.
 */

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ReyclerViewHolder> {

        ArrayList<Info> mList;
        public static final String TAG = "Adapter";

    private LayoutInflater layoutInflater;
        private HashSet<Integer> expandedPositionSet;
        private Context context;

        public RecyclerViewAdapter(Context context,ArrayList<Info> List) {
            this.layoutInflater = LayoutInflater.from(context);
            expandedPositionSet = new HashSet<>();
            this.context = context;
            mList = List;
        }


    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ExpandableView ExpandableView;
        private TextView showInfo;
        private TextView code,teacher,past,percent,name;


        private ReyclerViewHolder(final View view) {
            super(view);
            ExpandableView = (ExpandableView) view.findViewById(R.id.expandable_layout);
            showInfo = (TextView) view.findViewById(R.id.show_info);
            code = (TextView) view.findViewById(R.id.sub_code);
            teacher = (TextView) view.findViewById(R.id.sub_teacher);
            past = (TextView) view.findViewById(R.id.sub_att);
            name = (TextView) view.findViewById(R.id.sub_name);
            percent = (TextView) view.findViewById(R.id.sub_per);
        }

        private void updateItem(final int position) {
            ExpandableView.setOnExpandListener(new ExpandableView.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position, showInfo);
                }
            });
            ExpandableView.setExpand(expandedPositionSet.contains(position));

        }
    }


    @Override
        public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = layoutInflater.inflate(R.layout.list_item , parent, false);
        Log.d(TAG,"OnCReate");
            return new ReyclerViewHolder(item);
        }

        @Override
        public void onBindViewHolder(ReyclerViewHolder holder, int position) {
            holder.updateItem(position);

            Log.d(TAG,"OnBind Countof list : - " + mList.size());

            Info info = mList.get(position);

            holder.name.setText(info.getSubject());
            holder.code.setText(info.getSubject_Code());
            holder.teacher.setText(info.getTeacher());
            holder.past.setText(info.getPast());


            float fm ;

            if(info.getTotal()==0)
                fm=0;
            else
                fm= (info.getAttended()/info.getTotal())*100;

            holder.percent.setText(String.valueOf(fm));
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return mList.size() ;
        }


        private void registerExpand(int position, TextView textView) {
            if (expandedPositionSet.contains(position)) {
                removeExpand(position);

                textView.setText("Show description");
                Toast.makeText(context, "Position: " + position + " collapsed!", Toast.LENGTH_SHORT).show();
            } else {
                addExpand(position);

                textView.setText("Hide description");
                Toast.makeText(context, "Position: " + position + " expanded!", Toast.LENGTH_SHORT).show();
            }
        }

        private void removeExpand(int position) {
            expandedPositionSet.remove(position);
        }

        private void addExpand(int position) {
            expandedPositionSet.add(position);
        }
    }
