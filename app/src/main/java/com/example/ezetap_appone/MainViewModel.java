package com.example.ezetap_appone;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mathops.Constants;
import com.example.mathops.MathOperations;
import com.example.mathops.PerformOperations;

public class MainViewModel extends ObservableViewModel implements MathOperations {

    public String result;
    private PerformOperations performOperations = PerformOperations.getInstance();
    private MutableLiveData<String> operation = new MutableLiveData<>();

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChange(BR.result);
    }

    @Override
    public void addition() {
        operation.postValue(Constants.ADDITION);
    }

    @Override
    public void subtraction() {
        operation.postValue(Constants.SUBTRACTION);
    }

    public LiveData<String> performOperation(){
        return operation;
    }
}
