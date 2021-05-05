---
title: UHF
description: A cordova plugin for Chainway C72 and RodinBell Orca50 UHF module
---
<!--
# license: Licensed to the Apache Software Foundation (ASF) under one
#         or more contributor license agreements.  See the NOTICE file
#         distributed with this work for additional information
#         regarding copyright ownership.  The ASF licenses this file
#         to you under the Apache License, Version 2.0 (the
#         "License"); you may not use this file except in compliance
#         with the License.  You may obtain a copy of the License at
#
#           http://www.apache.org/licenses/LICENSE-2.0
#
#         Unless required by applicable law or agreed to in writing,
#         software distributed under the License is distributed on an
#         "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#         KIND, either express or implied.  See the License for the
#         specific language governing permissions and limitations
#         under the License.
-->


# cordova-plugin-uhf

This plugin defines a global `uhf` object, which provides some member methods to work on UHF module.
<br/>
Although the object is in the global scope, it is not available until after the `deviceready` event.
<br/>
此插件定义了一个全局对象`uhf`，提供了一些操纵UHF模块的成员函数。
尽管该对象是全局的，但`uhf`直到`deviceready`事件接收后才生效。


## Installation 安装

    cordova plugin add git+http://code.essocloud.com/dependencies/cordova-plugin-uhf.git

## Valid android.os.Build.MODEL 支持的设备型号

"C72"
<br/>
"C76"
<br/>
"P810"
<br/>
"A8"
<br/>
"common"

## Member methods 成员函数

- uhf.init(success, error)
- uhf.free(success, error)
- uhf.getPower(success, error)
- uhf.setPower(iPower, success, error)
- uhf.inventorySingleTag(success, error)
- uhf.startInventoryTag(flagAnti, initQ, success, error)
- uhf.startInventoryTagCnt(flagAnti, initQ, cnt, success, error)
- uhf.readEPCsFromBuffer(success, error)
- uhf.readTagFromBuffer(success, error)
- uhf.stopInventory(success, error)
- uhf.convertUiiToEPC(uii, success, error)
- uhf.Barcode2DWithSoftOpen(success, error)
- uhf.Barcode2DWithSoftClose(success, error)
- uhf.Barcode2DWithSoftIsPowerOn(success, error)
- uhf.Barcode2DWithSoftScan(success, error)
- uhf.Barcode2DWithSoftStopScan(success, error)

## uhf.init(success, error)

Initialize UHF module(Support all modules).
<br/>
初始化UHF模块,适应所有模块。

Call init() to switch on the device before operating the device.
<br/>
在操作设备前需要调用 init() 打开设备。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.init(function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.free(success, error)

Switch off UHF module.
<br/>
关闭UHF模块。

Call free() to switch off device after using.
<br/>
使用完后调用 free() 关闭设备。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.free(function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.getPower(success, error)

Read power of module, returns an int.
<br/>
读取模块的功率，返回一个整型。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.getPower(function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.setPower(iPower, success, error)

Setup module power.
<br/>
设置模块的功率。

iPower is an int no less than 5.
<br/> 
iPower为不小于5的整型。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.setPower(30, function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.inventorySingleTag(success, error)

This formula identify tag in single step, return a String UII for only one time.
<br/> 
该函数单步识别标签，一次只返回一个字符串UII。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.inventorySingleTag(function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.startInventoryTag(flagAnti, initQ, success, error)

Activate identification Tag circulation, upload the identified tag number to buffer zone. After starting the circular identification, the module will respond stopInventory() formula.
<br/>
启动识别Tag循环，只是开启识别Tag循环，之后将识别到的标签号上传到缓冲区，开启循环识别之后，模块只能响应stopInventory() 函数。

flagAnti is an int which uses anti-collision identification function or not，default 0.
<br/>
flagAnti为整型，判断是否使用防碰撞识别功能,默认使用0。

initQ is an int Initial Q value of anti-collision identification process, it will be valid if flagAnti is 1.
<br/>
initQ为防碰撞识别过程的初始整型Q值，flagAnti为1时有效。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.startInventoryTag(0, 0, function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.startInventoryTagCnt(flagAnti, initQ, cnt, success, error)

Activate identification Tag circulation, upload the identified tag number to buffer zone. After starting the circular identification, the module will respond stopInventory() formula.
<br/>
启动识别Tag循环，只是开启识别Tag循环，之后将识别到的标签号上传到缓冲区，开启循环识别之后，模块只能响应stopInventory() 函数。

flagAnti is an int which uses anti-collision identification function or not，default 0.
<br/>
flagAnti为整型，判断是否使用防碰撞识别功能,默认使用0。

initQ is an int Initial Q value of anti-collision identification process, it will be valid if flagAnti is 1.
<br/>
initQ为防碰撞识别过程的初始整型Q值，flagAnti为1时有效。

cnt is an int TID length, unit is 'byte'.
<br/>
cnt为TID的整型长度，单位为“字”。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.startInventoryTag(0, 0, 6, function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.readEPCsFromBuffer(success, error)

Returns a JSONArray of all returned EPC in buffer zone. null means reading failed.
<br/>
读取缓冲区所有的EPC（不重复）。返回一个JSONArray，null为读取失败。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.readEPCsFromBuffer(function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```
## uhf.readTagFromBuffer(success, error)

Read returned TID and UII infor of tag in buffer zone. Returns a JSONArray of which index 0 means TID infor, index 1 means UII, index 2 means RSSI (if it is not supported, it will return N/A), null means reading failed.
<br/>
读取缓冲区返回的标签的TID和UII。返回一个JSONArray，索引0为TID信息，索引1为UII，索引2为RSSI(如果不支持则返回N/A)，null为读取失败。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.readTagFromBuffer(function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.stopInventory(success, error)

Stop circular identification.
<br/>
停止循环识别。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.stopInventory(function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.convertUiiToEPC(uii, success, error)

UII transform to EPC.
<br/>
UII转EPC。

uii is a String UII data.
<br/>
uii为字符串UII数据。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.convertUiiToEPC('A1234', function (callback) {
                alert('Success: ' + callback);
            }, function (callback) {
                alert('Error: ' + callback);
            });
```

## uhf.Barcode2DWithSoftOpen(success, error)

Switch on 2D scanning device.
<br/>
打开二维扫描设备。

Call Barcode2DWithSoftOpen() to switch on the device before using.
<br/>
在操作设备前需要调用Barcode2DWithSoftOpen()打开设备。

return power-on status.
<br/>
返回上电状态。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.Barcode2DWithSoftOpen(function (callbackValue) {
    alert("uhf.Barcode2DWithSoftOpen() succeeds: " + callbackValue);
}, function (callbackValue) {
    alert("uhf.Barcode2DWithSoftOpen() fails: " + callbackValue);
    });
```

## uhf.Barcode2DWithSoftClose(success, error)

Switch off 2D barcode scannning device.
<br/>
关闭二维扫描设备。

Call Barcode2DWithSoftClose() to switch off device after using.
<br/>
在操作设备后需要调用Barcode2DWithSoftClose()关闭打开设备。

true means success, false means failure
<br/>
返回true成功/false失败

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.Barcode2DWithSoftClose(function (callbackValue) {
    alert("uhf.Barcode2DWithSoftClose() succeeds: " + callbackValue);
}, function (callbackValue) {
    alert("uhf.Barcode2DWithSoftClose() fails: " + callbackValue);
    });
```

## uhf.Barcode2DWithSoftIsPowerOn(success, error)

Determine whether the device is powered on.
<br/>
判断设备是否上电。

return power-on status.
<br/>
返回上电状态。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.Barcode2DWithSoftIsPowerOn(function (callbackValue) {
    alert("uhf.Barcode2DWithSoftIsPowerOn() succeeds: " + callbackValue);
}, function (callbackValue) {
    alert("uhf.Barcode2DWithSoftIsPowerOn() fails: " + callbackValue);
    });
```

## uhf.Barcode2DWithSoftScan(success, error)

Trigger 2D barcode scanning function.
<br/>
触发二维条码扫描。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.Barcode2DWithSoftScan(function (callbackValue) {
    alert("uhf.Barcode2DWithSoftScan() succeeds: " + callbackValue);
}, function (callbackValue) {
    alert("uhf.Barcode2DWithSoftScan() fails: " + callbackValue);
    });
```

## uhf.Barcode2DWithSoftStopScan(success, error)

Scanning terminated.
<br/>
终止扫描。

Parameter success and error are both callback functions.
<br/>
参数success与error都是回调函数。

### Supported Platforms 支持平台

- Android

### Quick Example 示例
```js
uhf.Barcode2DWithSoftStopScan(function (callbackValue) {
    alert("uhf.Barcode2DWithSoftStopScan() succeeds: " + callbackValue);
}, function (callbackValue) {
    alert("uhf.Barcode2DWithSoftStopScan() fails: " + callbackValue);
    });
```

