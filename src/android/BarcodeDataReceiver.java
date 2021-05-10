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
//    PluginResult pluginResult = new PluginResult(Status.OK, "BarcodeDataReceiver constructor: Keep the Cordova callbackContext");
//    pluginResult.setKeepCallback(true);   
//    this.callbackContext.sendPluginResult(pluginResult);
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    String barCode = intent.getStringExtra("data");
    if (barCode != null && !barCode.equals("")) {
    } else {
      barCode="Scan fail";
    }
    //if(iBarcodeResult!=null)
    //    iBarcodeResult.getBarcode(barCode);
    //SoundManage.PlaySound(context, SoundManage.SoundType.SUCCESS);
    toastMessage("Barcode: " + barCode);

    PluginResult pluginResult = new PluginResult(Status.OK, "Barcode: " + barCode);
    pluginResult.setKeepCallback(true);   
    this.callbackContext.sendPluginResult(pluginResult);
   
//    this.callbackContext.success(barCode);
  }
  
  // --------------------------------------------------------------------------
  // LOCAL METHODS
  // --------------------------------------------------------------------------

  private void toastMessage(final String msg) {
    cordova.getActivity().runOnUiThread(new Runnable() {
      public void run() {
        Toast.makeText(cordova.getActivity(), msg, Toast.LENGTH_SHORT).show();
      }
    });
  }
  
}

