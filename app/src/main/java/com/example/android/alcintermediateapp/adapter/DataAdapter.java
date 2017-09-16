package com.example.android.alcintermediateapp.adapter;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.alcintermediateapp.MainActivity;
import com.example.android.alcintermediateapp.R;
import com.example.android.alcintermediateapp.helper.CircleTransform;
import com.example.android.alcintermediateapp.model.User;

import java.util.ArrayList;
import java.util.List;
import com.bumptech.glide.Glide;

import static android.media.CamcorderProfile.get;

/**
 * Created by tunde on 9/10/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private List<User> mUsers;
    private Context mContext;
    private ItemClickListener mItemClickListener;
    public DataAdapter(Context context,List<User> users){
        mUsers = users;
        mContext = context;


    }



    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

        ViewHolder viewholder = new ViewHolder(v,this.mItemClickListener);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        User user =mUsers.get(position);
        TextView textView = holder.name;
        textView.setText(user.getLogin());
        Glide.with(mContext).load(user.getAvatarUrl())
                .thumbnail(0.5f)
                .crossFade()
                .transform(new CircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;
        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            name =(TextView) itemView.findViewById(R.id.tv_name);
            image =(ImageView) itemView.findViewById(R.id.image_preview);
            mItemClickListener = itemClickListener;

        }



    }
    public User getUserByPos(int pos) {
        return mUsers.get(pos);
    }

    public void updateAnswers(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }
    public interface ItemClickListener{
    void onPostClick(int id);
    }
    private User getUser(int userPosition){return mUsers.get(userPosition);}
}
