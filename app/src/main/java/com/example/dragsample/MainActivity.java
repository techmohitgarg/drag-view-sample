package com.example.dragsample;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnDragListener {
    int[] drawables = {R.drawable.officer,
            R.drawable.teamwork,
            R.drawable.worker,
            R.drawable.piggy,
            R.drawable.online,
            R.drawable.notification};
    private ImageView imgRightTop, imgLeftTop, imgLeftBottom, imgRightBottom;
    private RecyclerView recyclerView;
    private List<ListModal> listModalList;
    private AdapterListing adapterListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        imgLeftTop = findViewById(R.id.imgLeftTop);
        imgRightTop = findViewById(R.id.imgRightTop);
        imgLeftBottom = findViewById(R.id.imgLeftBottom);
        imgRightBottom = findViewById(R.id.imgRightBottom);
        recyclerView = findViewById(R.id.listing);
        listModalList = new ArrayList<>();


        setDataToAdapter();
        initContainer();
    }

    private void setDataToAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        adapterListing = new AdapterListing(drawables);
        recyclerView.setAdapter(adapterListing);
    }

    private void initContainer() {
        imgLeftTop.setOnDragListener(this);
        imgRightTop.setOnDragListener(this);
        imgLeftBottom.setOnDragListener(this);
        imgRightBottom.setOnDragListener(this);

    }

    private void setImgIntoSelectedView(ImageView dragView, int imageData) {
        dragView.setImageResource(imageData);
    }

    @Override
    public boolean onDrag(View v, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.e("TAG --- ", "ACTION_DRAG_ENTERED");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.e("TAG --- ", "ACTION_DRAG_EXITED");
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.e("TAG --- ", "ACTION_DRAG_ENDED");
                break;
            case DragEvent.ACTION_DROP:
                Log.e("TAG --- ", "ACTION_DROP");
                final DragData state = (DragData) dragEvent.getLocalState();
                switch (v.getId()) {
                    case R.id.imgLeftTop:
                        setImgIntoSelectedView(imgLeftTop, state.item);
                        break;
                    case R.id.imgRightTop:
                        setImgIntoSelectedView(imgRightTop, state.item);
                        break;
                    case R.id.imgLeftBottom:
                        setImgIntoSelectedView(imgLeftBottom, state.item);
                        break;
                    case R.id.imgRightBottom:
                        setImgIntoSelectedView(imgRightBottom, state.item);
                        break;
                }
            default:
                break;
        }
        return true;
    }
}