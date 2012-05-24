package com.phonegap.plugin;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import io.cordova.R;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
    
    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
    private Context mContext;
    private static String TAG = "MyItemizedOverlay";

    public MyItemizedOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
    }
    
    public MyItemizedOverlay(Drawable defaultMarker, Context context) {
        this(defaultMarker);
        mContext = context;
    }
    
    public void addOverlay(OverlayItem overlay) {
        mOverlays.add(overlay);
        populate();
    }
    
    @Override
    protected OverlayItem createItem(int i) {
        return mOverlays.get(i);
    }
    
    @Override
    public int size() {
        return mOverlays.size();
    }
    
    @Override
    protected boolean onTap(int index) {
        // Weirdest thing ever! index is always 0 and mOverlays size is 1
        Log.d(TAG, "Index " + index + " Overlay title " + mOverlays.get(index).getTitle());
        final GMActivity ma = (GMActivity) mContext;
//        // find geoname
        final GeoName geoname = ma.getGeoName(mOverlays.get(index).getTitle());
        if(geoname != null) {
            final Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.geoname_dialog);
            dialog.setTitle(geoname.getTitle());
            TextView summary = (TextView) dialog.findViewById(R.id.summary);
            summary.setText(geoname.getSummary());
            
            ImageView gotoicon = (ImageView) dialog.findViewById(R.id.gotoicon);
            gotoicon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent i = new Intent();
                    Bundle b = new Bundle();
                    b.putString("wikipediaUrl", geoname.getUrl());
                    Log.d(TAG, "Overlay URL "+geoname.getUrl());
                    i.putExtras(b);
                    ma.setResult(GMPlugin.RESULT_OK, i);
                    ma.finish();
                }
            });
            dialog.show();
        } else {
            Log.d(TAG, "Could not find geopoint");
        }
        
        return true;
    }

}
