package com.vanticus.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vanticus.model.Item;
import com.vanticus.model.ItemData;
import com.vanticus.model.MainData;
import com.vanticus.R;
import com.vanticus.ui.adapters.TransitionAdapter;
import com.vanticus.constants.Constants;
import com.vanticus.model.Card;
import com.vanticus.model.DataList;
import com.vanticus.ui.adapters.CardArrayAdapter;

import java.util.ArrayList;

import static com.vanticus.constants.Constants.catIndex;

public class DetailActivity extends Activity {

    public static final String EXTRA_PARAM_ID = "item_id";

    private ImageView mImageView;
    private TextView mTitle;
    private Item mItem;
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;
    int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mItem = ItemData.itemList().get(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        listView = (ListView) findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        mImageView = (ImageView) findViewById(R.id.itemImage);
        mTitle = (TextView) findViewById(R.id.textView);
        defaultColor = getResources().getColor(R.color.primary_dark);


        setUpAdapter();
        loadItem();
        windowTransition();

        getPhoto();
    }
//setting up the card type adater for listview
    private void setUpAdapter() {
        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);
        ArrayList<DataList> listData = MainData.totalData.get(catIndex);
        for (int i = 0; i < listData.size(); i++) {
            DataList list1 = listData.get(i);
            Card card = new Card(list1.getName());
            cardArrayAdapter.add(card);
        }
        listView.setAdapter(cardArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Constants.catIndex2 = position -1;
                Intent intent = new Intent(DetailActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });

    }

//setting list item
    private void loadItem() {
        mTitle.setText(mItem.name);
        mImageView.setImageResource(mItem.getImageResourceId(this));
    }

    private void windowTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getEnterTransition().addListener(new TransitionAdapter() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().getEnterTransition().removeListener(this);
                    }
                }
            });
        }
    }

    private void getPhoto() {
        Bitmap photo = BitmapFactory.decodeResource(getResources(), mItem.getImageResourceId(this));
    }
}
