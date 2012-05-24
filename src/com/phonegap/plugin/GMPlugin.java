package com.phonegap.plugin;

import org.json.JSONArray;

import android.content.Intent;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;

public class GMPlugin extends Plugin {
	
	public static int GET_GEONAME_URL = 0;
	public static int RESULT_OK = 0;
	private String callbackId;

	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		PluginResult result = null;
		this.callbackId = callbackId;
		if(action.compareTo("startGM") == 0) {
			Intent intent = new Intent(ctx.getContext(), GMActivity.class);
			ctx.startActivityForResult((Plugin) this, intent, GET_GEONAME_URL);
            result = new PluginResult(PluginResult.Status.NO_RESULT);
            result.setKeepCallback(true);
		}
		return result;
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if(requestCode == GET_GEONAME_URL && intent != null) {
			if(resultCode == RESULT_OK) {
				Log.d("NearMePlugin", intent.getExtras().getString("wikipediaUrl"));
				this.success(new PluginResult(PluginResult.Status.OK, intent.getExtras().getString("wikipediaUrl")), callbackId);
			}
		} else {
			this.success(new PluginResult(PluginResult.Status.NO_RESULT), callbackId);
		}
	}

}
