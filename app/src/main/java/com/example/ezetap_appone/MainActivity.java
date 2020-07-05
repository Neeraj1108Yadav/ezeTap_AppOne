package com.example.ezetap_appone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ezetap_appone.databinding.ActivityMainBinding;
import com.example.mathops.MathOperationActivity;

import static com.example.mathops.Constants.ADDITION;
import static com.example.mathops.Constants.NUMBER_ONE;
import static com.example.mathops.Constants.NUMBER_TWO;
import static com.example.mathops.Constants.OPERATION_KEY;
import static com.example.mathops.Constants.RESULT_ADDITION;
import static com.example.mathops.Constants.RESULT_SUBTRACTION;
import static com.example.mathops.Constants.SUBTRACTION;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private final int NUM_ONE = 5;
    private final int NUM_TWO = 8;

    /*private final String OPERATION_KEY = "operation";
    private final String NUM_ONE_KEY = "num1";
    private final String NUM_TWO_KEY = "num2";
    private int RESULT_ADDITION = 102;
    private int RESULT_SUBTRACTION = 103;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setEzetapOne(mainViewModel);

        mainViewModel.performOperation().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String operation) {
                switch (operation) {
                    case ADDITION:
                        initAddition();
                        break;

                    case SUBTRACTION:
                        initSubtraction();
                        break;
                }
            }
        });

    }

    private void initAddition() {
        Intent intent = new Intent(this, MathOperationActivity.class);
        intent.putExtra(OPERATION_KEY, ADDITION);
        intent.putExtra(NUMBER_ONE, NUM_ONE);
        intent.putExtra(NUMBER_TWO, NUM_TWO);
        startActivityForResult(intent, RESULT_ADDITION);
    }

    private void initSubtraction() {
        Intent intent = new Intent(this, MathOperationActivity.class);
        intent.putExtra(OPERATION_KEY, SUBTRACTION);
        intent.putExtra(NUMBER_ONE, NUM_ONE);
        intent.putExtra(NUMBER_TWO, NUM_TWO);
        startActivityForResult(intent, RESULT_SUBTRACTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_ADDITION || resultCode == RESULT_SUBTRACTION) {
            int result = data.getIntExtra("result", 0);
            binding.textViewResult.setText("Result : "+String.valueOf(result));
        }
    }
}
