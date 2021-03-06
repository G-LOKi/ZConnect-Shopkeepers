package zconnectcom.zutto.zconnectshophandle.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import zconnectcom.zutto.zconnectshophandle.R;


public class ImageViewHolder extends RecyclerView.ViewHolder {
    Button mDel;
    View mView;
    String ShopKey;
    String Type;

    public ImageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setData(String shopKey, String type) {
        ShopKey = shopKey;
        Type = type;
    }

    public void setDelButton(final String key) {

        mDel = (Button) mView.findViewById(R.id.btnDel);
        mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Shop/Shops").child(ShopKey).child(Type).child(key).removeValue();
            }
        });

    }

    public void setImage(final String url) {
        Picasso.with(mView.getContext()).load(url).into((ImageView) mView.findViewById(R.id.imgDisplay), new Callback() {
            @Override
            public void onSuccess() {
                mDel.setVisibility(View.VISIBLE);
                mView.findViewById(R.id.imgDisplay).setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                
            }
        });
    }

}
