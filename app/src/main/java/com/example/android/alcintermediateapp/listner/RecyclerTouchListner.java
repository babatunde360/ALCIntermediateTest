package com.example.android.alcintermediateapp.listner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tunde on 9/13/2017.
 */

public class RecyclerTouchListner implements RecyclerView.OnItemTouchListener {

    public interface ClickListner{
        void onClick(View view,int position);
        void onLongClick(View view, int position);
    }

    private GestureDetector mgestureDetector;
    private ClickListner mclickListener;

    public RecyclerTouchListner(Context context, final RecyclerView recyclerView, final ClickListner clickListner){
        this.mclickListener = clickListner;
        mgestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clickListner!=null){
                    clickListner.onLongClick(child,recyclerView.getChildPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && mclickListener != null && mgestureDetector.onTouchEvent(e)) {
            mclickListener.onClick(child, rv.getChildPosition(child));
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
