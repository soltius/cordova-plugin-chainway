package com.pactera.hifm.uhf;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.view.KeyEvent;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

class BarcodeDataReceiver extends BroadcastReceiver {

  CordovaInterface cordova;
  CallbackContext callbackContext;

  public BarcodeDataReceiver(CordovaInterface cordova, CallbackContext callbackContext) {
    this.cordova = cordova;
    this.callbackContext = callbackContext;
  }

  @Override
  public void onReceive(Context context, Intent intent) {

    String barCode = intent.getStringExtra("data");
    if (barCode != null && !barCode.equals("")) {
      PluginResult pluginResult = new PluginResult(Status.OK, barCode);
      pluginResult.setKeepCallback(true);
      this.callbackContext.sendPluginResult(pluginResult);
    } else {
      PluginResult pluginResult = new PluginResult(Status.ERROR, "Scan returned no results");
      pluginResult.setKeepCallback(true);
      this.callbackContext.sendPluginResult(pluginResult);
    }
  }
  
  // --------------------------------------------------------------------------
  // LOCAL METHODS
  // --------------------------------------------------------------------------
  
}

