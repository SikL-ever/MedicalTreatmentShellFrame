package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.activity.ZxxqActivity;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.VH> {

    Context context;
    String[] split;
    private View inflate;
    private int iii;

    public GridAdapter(Context context, String[] strings, int id) {
        this.context = context;
        this.split = strings;
        this.iii = id;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (split.length==1){
            inflate = LayoutInflater.from(context).inflate(R.layout.grid_adapter, null, false);
        }else if (split.length % 2 == 0){
            inflate = LayoutInflater.from(context).inflate(R.layout.grid_adapter2, null, false);
        }else if (split.length % 2 == 1 && split.length != 1){
            inflate = LayoutInflater.from(context).inflate(R.layout.grid_adapter3, null, false);
        }
        return new VH(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        if (split.length == 1){
            vh.imageView.setImageURI(split[i]);
        }else if (split.length % 2 == 0){
            vh.imageView.setImageURI(split[i]);
      //      vh.imageView1.setImageURI(split[i]);

        }else if (split.length % 2 == 1 && split.length != 1){
            vh.imageView.setImageURI(split[i]);
         /*   vh.imageView1.setImageURI(split[i]);
            vh.imageView2.setImageURI(split[i]);*/
        }
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(context, ""+iii, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ZxxqActivity.class);
                intent.putExtra("idxq",iii);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return split.length;
    }
    public class VH extends RecyclerView.ViewHolder{
       // SimpleDraweeView simpleDraweeView;
        SimpleDraweeView imageView,imageView1,imageView2;
        public VH(@NonNull View itemView) {
            super(itemView);
            if (split.length==1){
                imageView =itemView.findViewById(R.id.image);
            }else if (split.length % 2 == 0){
                imageView =itemView.findViewById(R.id.image);
           //     imageView1 =itemView.findViewById(R.id.image1);

            }else if (split.length % 2 == 1 && split.length != 1){
                imageView =itemView.findViewById(R.id.image);
               /* imageView1 =itemView.findViewById(R.id.image1);
                imageView2 =itemView.findViewById(R.id.image2);*/
            }


        }
    }
}
