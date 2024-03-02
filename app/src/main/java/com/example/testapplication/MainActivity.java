package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapplication.annotate.BindHelper;
import com.example.testapplication.annotate.BindView;
import com.example.testapplication.annotate.ContentView;
import com.example.testapplication.annotate.OnClickEvent;
import com.example.testapplication.annotate.OnLongClickEvent;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getSimpleName();

    /*
    Resource IDs will be non-final by default in Android Gradle Plugin version 8.0, avoid using them as annotation attributes
    Android Gradle团队在8.0版本对构建系统做出优化和改进（Resource IDs will be non-final），从而导致如此声明resId的框架被弃用
     */
    @BindView(R.id.tvTop)
    private Button tvTop;

    @BindView(R.id.tvBottom)
    private Button tvBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindHelper.bind(this);
        tvTop.setText("Hello World");
        Log.d(TAG, "tvCenter修改后的值:" +  tvTop.getText());
    }

    @OnClickEvent(R.id.tvTop)
    private void topClick() {
        Toast.makeText(this, "topClick", Toast.LENGTH_SHORT).show();
    }

    @OnLongClickEvent(R.id.tvBottom)
    private boolean bottomLongClick() {
        Toast.makeText(this, "bottomLongClick", Toast.LENGTH_SHORT).show();
        return false;
    }
}