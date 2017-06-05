package com.vanticus.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vanticus.model.ItemData;
import com.vanticus.R;
import com.vanticus.model.Item;

/**
 * Created by Raghuram on 26-05-2017.
 */

// 1
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    Context mContext;
    OnItemClickListener mItemClickListener;

    // 2
    public ItemListAdapter(Context context) {
        this.mContext = context;
    }

    // 3
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout itemHolder;
        public LinearLayout itemNameHolder;
        public TextView itemName;
        public ImageView itemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemNameHolder = (LinearLayout) itemView.findViewById(R.id.itemNameHolder);
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            itemHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }

    // 1
    @Override
    public int getItemCount() {
        return new ItemData().itemList().size();
    }

    // 2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    // 3
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item = new ItemData().itemList().get(position);
        holder.itemName.setText(item.name);
        Picasso.with(mContext).load(item.getImageResourceId(mContext)).into(holder.itemImage);
        Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), item.getImageResourceId(mContext));

        Bitmap myDisplayBitmap = BitmapFactory.decodeResource(mContext.getResources(),  item.getImageResourceId(mContext));
        if (myDisplayBitmap != null && !myDisplayBitmap.isRecycled())
        {
            Palette palette = Palette.from(myDisplayBitmap).generate();
            Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();

    /*If vibrantSwatch is null then return 0 otherwise :-) */
            int opaqueDarkVibrantColor = vibrantSwatch != null ? vibrantSwatch.getRgb() : 0;

     /*Call the method that returns alpha color */
            int transparentRGBInt = getColorWithAplha(opaqueDarkVibrantColor, 0.5f);
            holder.itemNameHolder.setBackgroundColor(transparentRGBInt);

            // prints something like -2146428888
            Log.i("info", String.valueOf(transparentRGBInt));

        }
    }
    private int getColorWithAplha(int color, float ratio)
    {
        int transColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        transColor = Color.argb(alpha, r, g, b);
        return transColor ;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
