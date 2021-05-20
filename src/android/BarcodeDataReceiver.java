package com.pactera.hifm.uhf;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


class BarcodeDataReceiver extends BroadcastReceiver {

  private CordovaInterface cordova;
  private CallbackContext callbackContext;

  public BarcodeDataReceiver(CordovaInterface cordova, CallbackContext callbackContext) {
    this.cordova = cordova;
    this.callbackContext = callbackContext;
  }

  @Override
  public void onReceive(Context context, Intent intent) {

    byte[] dataMatrix = intent.getByteArrayExtra("dataBytes");
    if (dataMatrix != null) {
      PluginResult pluginResult = new PluginResult(Status.OK, dataMatrix);
      pluginResult.setKeepCallback(true);
      this.callbackContext.sendPluginResult(pluginResult);
      SoundManage.PlaySound(context, SoundManage.SoundType.SUCCESS);
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

