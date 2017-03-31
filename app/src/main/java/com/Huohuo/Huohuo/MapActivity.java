package com.Huohuo.Huohuo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityMapBinding;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JinBo on 2017/3/27.
 */

public class MapActivity extends BaseActivity<ActivityMapBinding> {
    public LocationClient mLocationClient;

    private TextView positionText;

    private MapView mapView;

    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        showContentView();
        setTitle("地图");
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        positionText = (TextView) findViewById(R.id.position_text_view);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MapActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }
    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            // Toast.makeText(this, "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT).show();
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.
                Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }
    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        //option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // StringBuilder currentPosition = new StringBuilder();
            //currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
            // currentPosition.append("经线：").append(location.getLongitude()).append("\n");
            // currentPosition.append("国家：").append(location.getCountry()).append("\n");
            // currentPosition.append("省：").append(location.getProvince()).append("\n");
            // currentPosition.append("市：").append(location.getCity()).append("\n");
            // currentPosition.append("区：").append(location.getDistrict()).append("\n");
            // currentPosition.append("街道：").append(location.getStreet()).append("\n");
            // currentPosition.append("定位方式：");
            // if (location.getLocType() == BDLocation.TypeGpsLocation) {
            //     currentPosition.append("GPS");
            //  } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
            //  currentPosition.append("网络");
            //  }
            //  positionText.setText(currentPosition);
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }
            LatLng point1 = new LatLng(30.3078750000,120.3533750000);
//构建Marker图标
            BitmapDescriptor bitmap1 = BitmapDescriptorFactory
                    .fromResource(R.drawable.start);
//构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option1 = new MarkerOptions()
                    .position(point1)
                    .icon(bitmap1);
//在地图上添加Marker，并显示
            baiduMap.addOverlay(option1);

            LatLng point2 = new LatLng(30.3126960000,120.3593290000);
//构建Marker图标
            BitmapDescriptor bitmap2 = BitmapDescriptorFactory
                    .fromResource(R.drawable.end);
//构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option2 = new MarkerOptions()
                    .position(point2)
                    .icon(bitmap2);
//在地图上添加Marker，并显示
            baiduMap.addOverlay(option2);
        }


        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

    }
    public static void start(Context context) {
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }
}
