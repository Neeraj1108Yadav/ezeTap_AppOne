package com.example.mathops;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.mathops.Constants.ADDITION;
import static com.example.mathops.Constants.RESULT_ADDITION;
import static com.example.mathops.Constants.RESULT_SUBTRACTION;
import static com.example.mathops.Constants.SUBTRACTION;

public class MathOperationActivity extends AppCompatActivity {
    private final String PACKAGE_NAME = "com.example.ezetap_apptwo";
    private final String ACTIVITY_CLASS = "com.example.ezetap_apptwo.MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_operation_mediator);

        Intent intent = getIntent();
        if(isAppInstalled()){
            String ops = intent.getStringExtra(Constants.OPERATION_KEY);
            int num1 = intent.getIntExtra(Constants.NUMBER_ONE,0);
            int num2 = intent.getIntExtra(Constants.NUMBER_TWO,0);
            assert ops != null;
            openEzeTap_Two(ops,num1,num2);
        }else{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + PACKAGE_NAME)));
            finish();
        }
    }

    private boolean isAppInstalled(){
        try {
            getPackageManager().getApplicationInfo(PACKAGE_NAME, 0);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    public void openEzeTap_Two(String operation,int num1,int num2){
        Intent intent = new Intent();
        intent.putExtra(Constants.OPERATION_KEY,operation);
        intent.putExtra(Constants.NUMBER_ONE,num1);
        intent.putExtra(Constants.NUMBER_TWO,num2);
        intent.setComponent(new ComponentName(PACKAGE_NAME,ACTIVITY_CLASS));
        switch (operation)
        {
            case ADDITION:
                startActivityForResult(intent,RESULT_ADDITION);
                 break;
            case SUBTRACTION:
                startActivityForResult(intent,RESULT_SUBTRACTION);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_ADDITION){
            setResult(RESULT_ADDITION,data);
            finish();
        }else if(resultCode == RESULT_SUBTRACTION){
            setResult(RESULT_SUBTRACTION,data);
            finish();
        }
    }
}