package com.example.liuyafei.simplelauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import java.util.List;

/**
 * Created by liuyafei on 11/11/15.
 */
public class Launcher extends Activity{
    private GridView appList;
    private List<ResolveInfo> apps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        apps=getPackageManager().queryIntentActivities(intent,0);

        setContentView(R.layout.launcher);
        appList=(GridView)findViewById(R.id.app_list);
        appList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return apps.size();
            }

            @Override
            public Object getItem(int position) {
                return apps.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView appView;
                if(convertView == null){
                    convertView = new ImageView(Launcher.this);
                }
                appView = (ImageView)convertView;
                ResolveInfo appInfo = apps.get(position);
                appView.setImageDrawable(appInfo.activityInfo.loadIcon(getPackageManager()));
                return appView;
            }
        });
        appList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo appInfo = apps.get(position);
                ComponentName app = new ComponentName(appInfo.activityInfo.packageName, appInfo.activityInfo.name);
                Intent tempIntent = new Intent();
                tempIntent.setComponent(app);
                startActivity(tempIntent);

            }
        });
    }
}
