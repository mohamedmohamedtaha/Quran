package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.MohamedTaha.Imagine.Quran.getData.Model;
import com.MohamedTaha.Imagine.Quran.R;

import java.util.List;

/**
 * Created by MANASATT on 24/08/17.
 */

public class RecycleViewReaderAdapter extends RecyclerView.Adapter<RecycleViewReaderAdapter.RecycleViewHolder>{

        public static final String NAME ="name";
        public static final String URLLINK ="url";
        private List<Model> responses;

        Context context;

        public RecycleViewReaderAdapter(Context context,List<Model> responses){
            this.context = context;
            this.responses = responses;
        }
        @Override
        public RecycleViewReaderAdapter.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_templete,parent,false);
            RecycleViewReaderAdapter.RecycleViewHolder holder = new RecycleViewReaderAdapter.RecycleViewHolder(row);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecycleViewReaderAdapter.RecycleViewHolder holder, int position) {
            Model data = responses.get(position);
            holder.text_title.setText(data.getSora_name());


        }
        /*
        public interface ClickListener{
            void onClick(View view , int position);
            void onLongClick(View view, int position);
        }
*/
        @Override
        public int getItemCount() {
            return responses.size();
        }

        class RecycleViewHolder extends RecyclerView.ViewHolder{
            TextView text_title;
            public RecycleViewHolder(View itemView) {
                super(itemView);
                text_title = (TextView)itemView.findViewById(R.id.text_title);
             /*  text_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = null;
                            intent = new Intent(context,MainActivity.class);
//                       Intent passNameShekh = ((Activity)context).getIntent();

                        intent.putExtra(NAME,responses.get(getAdapterPosition()).getSora_name());
                        intent.putExtra(URLLINK,responses.get(getAdapterPosition()).getSora_link());

                     //   String nameShekh = intent.getStringExtra(SHEKH_NAME);
                       // intent.putExtra(SHEKH_NAME,nameShekh);
                     //   intent.putExtra(SHEKH_NAME,nameShekh);
                    //    Toast.makeText(context,"Name: "+ nameShekh, Toast.LENGTH_SHORT).show();

                      //  textNameSorActionBar = (TextView) findViewById(R.id.textNameSorActionBar);
                    //    textNameSorActionBar.setText(getIntent().getStringExtra(SHEKH_NAME));

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                    }
                });*/
            }
        }

  public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);

    }
    public static class RecycleTouchListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private RecycleViewReaderAdapter.ClickListener clickListener;

        public RecycleTouchListener(Context context, final RecyclerView recycleView, final RecycleViewReaderAdapter.ClickListener clickLstener)
        {
            this.clickListener = clickLstener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recycleView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    }