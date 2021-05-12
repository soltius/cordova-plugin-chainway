/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package com.pactera.hifm.uhf;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.TimeZone;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;
import android.provider.Settings;
import android.view.View;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;
import android.view.*;

// import com.rscja.deviceapi.Barcode1D;
import com.zebra.adc.decoder.Barcode2DWithSoft;

public class UhfInv extends CordovaPlugin {

  Barcode2DWithSoft barcode2DWS;
  private CallbackContext cordovaCallbackContext = null;
  BarcodeDataReceiver receiver;
  private String cwBarcode;

  /**
   * Constructor.
   */
  public UhfInv() {
  }
  
  public void callback(org.apache.cordova.PluginResult.Status status, String msg) {
    PluginResult pluginResult = new PluginResult(status, msg);
    pluginResult.setKeepCallback(true);   
    this.cordovaCallbackContext.sendPluginResult(pluginResult);
  }
  
  public Barcode2DWithSoft.ScanCallback ScanBack = new Barcode2DWithSoft.ScanCallback() {
    @Override
    public void onScanComplete(int i, int length, byte[] bytes) {
      if (length < 1) {
        if (length == -1) {
          callback(Status.ERROR, "ScanBack: Scan cancel");
        } else if (length == 0) {
          callback(Status.ERROR, "ScanBack: Scan Timeout");
        } else {
          callback(Status.ERROR, "ScanBack: Scan fail");
        }
      } else {
        try {
          cwBarcode = new String(bytes, 0, length, "ASCII");
          callback(Status.OK, "ScanBack: " + cwBarcode);
        } catch (UnsupportedEncodingException ex) {
          callback(Status.ERROR, "ScanBack: UnsupportedEncodingException ex: " + ex);
        }

      }
    }
  };

  /**
   * Sets the context of the Command. This can then be used to do things like get
   * file paths associated with the Activity.
   *
   * @param cordova The context of the main Activity.
   * @param webView The CordovaWebView Cordova is running in.
   */
  public void initialize(CordovaInterface cordova, CordovaWebView webView, final CallbackContext callbackContext) {
    super.initialize(cordova, webView); 
  }

  public void pluginInitialize() {
    super.pluginInitialize();  
  }

  /**
   * Executes the request and returns PluginResult.
   *
   * @param action          The action to execute.
   * @param args            JSONArry of arguments for the plugin.
   * @param callbackContext The callback id used when calling back into
   *                        JavaScript.
   * @return True if the action was valid, false if not.
   */
  public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
      throws JSONException {
        
    if (this.cordovaCallbackContext == null) {
      this.cordovaCallbackContext = callbackContext;        
      callback(Status.OK, "cordovaCallbackContext instantiated");
    }
    
    try {

      if ("test".equals(action)) {
        callback(Status.OK, "callbackContext.success: new test method successful");
        return true;
      }
        
      if ("deviceInfo".equals(action)) {
        final String msg = "UhfInv.action.equals(\"deviceInfo\")";
        cordova.getThreadPool().execute(new Runnable() {
          public void run() {            
          callback(Status.OK, 
              "\nMODEL: " + android.os.Build.MODEL
            + "\nSERIAL: " + android.os.Build.SERIAL
            + "\nPRODUCT: " + android.os.Build.PRODUCT
            + "\nMANUFACTURER: " + android.os.Build.MANUFACTURER
            + "\nHARDWARE: " + android.os.Build.HARDWARE
            + "\nFINGERPRINT: " + android.os.Build.FINGERPRINT);
            callback(Status.OK, msg);
          }
        });
        return true;
      }
     
      if ("C66".equals(android.os.Build.MODEL) 
       || "P700".equals(android.os.Build.MODEL) 
       || "C71".equals(android.os.Build.MODEL) 
       || "P800".equals(android.os.Build.MODEL)
       || "C72".equals(android.os.Build.MODEL) 
       || "P810".equals(android.os.Build.MODEL) 
       || "C76".equals(android.os.Build.MODEL) 
       || "P820".equals(android.os.Build.MODEL)) {
          
        if ("startBarcodeDataReceiver".equals(action)) {
          cordova.getThreadPool().execute(new Runnable() {
            public void run() {
              startBarcodeDataReceiver();
            }
          });
          return true;
        }
        
        if (action.contains("Barcode2DWithSoft")) {
          
          if ("Barcode2DWithSoftOpen".equals(action)) {
            if (null != barcode2DWS) {
              callback(Status.ERROR, "Failure: barcode2DWS is NOT null!");
              return false;
            } else {
              final int way = args.getInt(0);
              barcode2DWS = Barcode2DWithSoft.getInstance();
              cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                  Barcode2DWithSoftOpen(way);
                }
              });
              return true;
            }
            
          } else if ("Barcode2DWithSoftClose".equals(action)) {
            if (null == barcode2DWS) {
              callback(Status.ERROR, "Failure: barcode2DWS is null already!");
            } else {
              cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                  Barcode2DWithSoftClose();
                }
              });
            }
            return true;
          }

          if (null != barcode2DWS) {
            if ("Barcode2DWithSoftIsPowerOn".equals(action)) {
              cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                  Barcode2DWithSoftIsPowerOn();
                }
              });
              return true;
            }

            if (barcode2DWS.isPowerOn()) {
              
              if ("Barcode2DWithSoftScan".equals(action)) {
                cordova.getThreadPool().execute(new Runnable() {
                  public void run() {
                    Barcode2DWithSoftScan();
                  }
                });
                return true;
                
              } else if ("Barcode2DWithSoftStopScan".equals(action)) {
                cordova.getThreadPool().execute(new Runnable() {
                  public void run() {
                    Barcode2DWithSoftStopScan();
                  }
                });
                return true;
                
              } else {
                callback(Status.ERROR, "Invalid action");
                return false;
              }
            } else {
              callback(Status.ERROR, "Failure: barcode2DWS.isPowerOn() is false");
              return false;
            }
          } else {
            callback(Status.ERROR, "Failure: barcode2DWS is null");
            return false;
          }
        } else {
        }
      } else {
        callback(Status.ERROR, "Invalid device");
        return false;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      callback(Status.ERROR, "Exception: " + ex.getMessage());
      return false;
    }
    return true;
  }

  // --------------------------------------------------------------------------
  // LOCAL METHODS
  // --------------------------------------------------------------------------

  private void startBarcodeDataReceiver() {
    try {
      if (this.receiver == null) {
        this.receiver = new BarcodeDataReceiver(cordova, cordovaCallbackContext);
      }
      webView.getContext().registerReceiver(receiver, new IntentFilter("com.scanner.broadcast"));
      callback(Status.OK, "Barcode Data Receiver started");
      
    } catch (Exception e) {
      callback(Status.ERROR, e.getMessage());
    }
  }
  
  private void Barcode2DWithSoftOpen(int way) {
    
    try {
      barcode2DWS.stopScan();
      barcode2DWS.close();
    } catch (Exception e) {

    }
     
    try {
      if (barcode2DWS.open(cordova.getActivity())) {
        barcode2DWS.stopScan();
        if (way == 1) { 
          barcode2DWS.setParameter(293, 0);
        }
        if (way == 2) { 
          barcode2DWS.disableAllCodeTypes();
          barcode2DWS.setParameter(293, 1);
        }
        callback(Status.OK, "Barcode Reader opened");
      } else {
        callback(Status.ERROR, "barcode2DWS.open() return false");
      }
    } catch (Exception e) {
      callback(Status.ERROR, e.getMessage());
    }
  }

  private void Barcode2DWithSoftScan() {
    try {
      cwBarcode = null;
      barcode2DWS.scan();
    } catch (Exception e) {
      callback(Status.ERROR, e.getMessage());
    }
  }

  private void Barcode2DWithSoftStopScan() {
    try {
      barcode2DWS.stopScan();
      cwBarcode = null;
      callback(Status.OK, "barcode2DWS.stopScan() success");
    } catch (Exception e) {
      callback(Status.ERROR, e.getMessage());
    }
  }

  private void Barcode2DWithSoftIsPowerOn() {
    try {
      callback(Status.OK, barcode2DWS.isPowerOn() + "");
    } catch (Exception e) {
      callback(Status.ERROR, e.getMessage());
    }
  }

  private void Barcode2DWithSoftClose() {
    try {
      cwBarcode = null;
      barcode2DWS.stopScan();
      if (barcode2DWS.close()) {
        barcode2DWS = null;
        callback(Status.OK, "Barcode Reader Closed");
      } else {
        barcode2DWS = null;
        callback(Status.ERROR, "Failed to close Barcode Reader, may be already closed");
      }
    } catch (Exception e) {
      callback(Status.ERROR, e.getMessage());
    }
  }
}