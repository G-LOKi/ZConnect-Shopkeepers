package zconnectcom.zutto.zconnectshophandle.UI.Activities.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import zconnectcom.zutto.zconnectshophandle.R;
import zconnectcom.zutto.zconnectshophandle.UI.Activities.AddItem.addItem;
import zconnectcom.zutto.zconnectshophandle.UI.Activities.Base.BaseActivity;
import zconnectcom.zutto.zconnectshophandle.Utils.FirebaseRVAdapter;

public class ShopMenu extends BaseActivity {
    DatabaseReference mMenu;
    String key;
    Bundle extras;
    RecyclerView mMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);
        extras = getIntent().getExtras();
        setToolbar();
        showBackButton();
        key = extras.getString("ShopKey");
        getSupportActionBar().setTitle(extras.getString("ShopName"));
    }


    @Override
    protected void onStart() {
        super.onStart();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postItem = new Intent(ShopMenu.this, addItem.class);
                postItem.putExtra("ShopKey", extras.getString("ShopKey"));
                postItem.putExtra("ShopName", extras.getString("ShopName"));
                postItem.putExtra("type", "Menu");
                startActivity(postItem);


            }
        });

        initRV();
        FirebaseRVAdapter menuAdapt = new FirebaseRVAdapter("Menu", mMenu, key);
        mMenuList.setAdapter(menuAdapt.showImage());


    }

    void initRV() {
        mMenu = FirebaseDatabase.getInstance().getReference().child("Shop").child("Shops").child(key).child("Menu");

        mMenuList = (RecyclerView) findViewById(R.id.menuRV);

        mMenuList.setHasFixedSize(true);
        mMenu.keepSynced(true);
        LinearLayoutManager productLinearLayout = new LinearLayoutManager(getApplicationContext());
        productLinearLayout.setReverseLayout(true);
        productLinearLayout.setStackFromEnd(true);
        mMenuList.setLayoutManager(productLinearLayout);

    }


}
