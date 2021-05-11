package com.islam.calculatorbyjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton ZeroButton, OneButton , TwoButton , ThreeButton , FourButton , FiveButton ,
            SixButton , SevenButton , EightButton , NineButton , ACButton , CelsiusButton ,
            DivisionButton , MultiplyButton , SubtractionButton , SumButton , EqualButton ,
            PointButton ;

    ImageButton DeleteButton ;
    TextView mNumber ,mResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateViews();

    }

    public void initiateViews(){

        ZeroButton = findViewById(R.id.ZeroBut);
        ZeroButton.setOnClickListener(this);

        OneButton = findViewById(R.id.OneBut);
        OneButton.setOnClickListener(this);

        TwoButton = findViewById(R.id.TwoBut);
        TwoButton.setOnClickListener(this);

        ThreeButton = findViewById(R.id.ThreeBut);
        ThreeButton.setOnClickListener(this);

        FourButton = findViewById(R.id.FourBut);
        FourButton.setOnClickListener(this);

        FiveButton = findViewById(R.id.FiveBut);
        FiveButton.setOnClickListener(this);

        SixButton = findViewById(R.id.SixBut);
        SixButton.setOnClickListener(this);

        SevenButton = findViewById(R.id.SevenBut);
        SevenButton.setOnClickListener(this);

        EightButton = findViewById(R.id.EightBut);
        EightButton.setOnClickListener(this);

        NineButton = findViewById(R.id.NineBut);
        NineButton.setOnClickListener(this);

        ACButton = findViewById(R.id.ACBut);
        ACButton.setOnClickListener(this);

        CelsiusButton = findViewById(R.id.CelsiusBut);
        CelsiusButton.setOnClickListener(this);

        DivisionButton = findViewById(R.id.DivisionBut);
        DivisionButton.setOnClickListener(this);

        MultiplyButton = findViewById(R.id.multiplyBut);
        MultiplyButton.setOnClickListener(this);

        SubtractionButton = findViewById(R.id.SubtractionBut);
        SubtractionButton.setOnClickListener(this);

        DeleteButton = findViewById(R.id.deleteNumber);
        DeleteButton.setOnClickListener(this);

        EqualButton = findViewById(R.id.EqualBut);
        EqualButton.setOnClickListener(this);

        PointButton = findViewById(R.id.pointBut);
        PointButton.setOnClickListener(this);

        SumButton = findViewById(R.id.SumBut);
        SumButton.setOnClickListener(this);

        mNumber = findViewById(R.id.tvNumber);
        mResult = findViewById(R.id.result);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ZeroBut :
                CheckNumber("0");
                break;
            case R.id.OneBut :
                CheckNumber("1");
                break;
            case R.id.TwoBut :
                CheckNumber("2");
                break;
            case R.id.ThreeBut :
                CheckNumber("3");
                break;
            case R.id.FourBut :
                CheckNumber("4");
                break;
            case R.id.FiveBut :
                CheckNumber("5");
                break;
            case R.id.SixBut :
                CheckNumber("6");
                break;
            case R.id.SevenBut :
                CheckNumber("7");
                break;
            case R.id.EightBut :
                CheckNumber("8");
                break;
            case R.id.NineBut :
                CheckNumber("9");
                break;
            case R.id.pointBut :
                CheckNumber(".");
                break;
            case R.id.ACBut :
                mNumber.setText("");
                mResult.setText("0");
                break;
            case R.id.deleteNumber :
                deleteOneNumber();
                break;
            case R.id.CelsiusBut :
                mResult.setText("%"+myCelsius());
                break;
            case R.id.DivisionBut :
                CheckOperation("/");
                break;
            case R.id.SubtractionBut :
                CheckOperation("-");
                break;
            case R.id.multiplyBut :
                CheckOperation("*");
                break;
            case R.id.SumBut :
                CheckOperation("+");
                break;
            case R.id.EqualBut :
                myEqualization();
                break;
        }
    }

    void CheckNumber(String number){
        String myText = mNumber.getText().toString();
        if (myText.isEmpty()){
            if (number.equals(".")){
                mNumber.setText(myText);
            }
            else
                mNumber.setText(number);
        }
        else if (!(myText.isEmpty()))
        {
            mNumber.setText(myText+number);
        }
    }

    void CheckOperation(String operation){
        String myText = mNumber.getText().toString();
        if (myText.isEmpty()){
            if (operation.equals("-")){
                mNumber.setText(operation+myText);
            }
        }
        else if (myText.endsWith("-") || myText.endsWith("+") ||
                myText.endsWith("*") || myText.endsWith("/"))
        {
            mNumber.setText(myText);
        }
        else
            mNumber.setText(myText+operation);
    }

    float myCelsius(){
        float text = Integer.parseInt(mNumber.getText().toString());
        float celsius = text/100 ;

        return celsius;
    }

    void deleteOneNumber() {
        int cursorPos = mNumber.getSelectionStart();
        int textLen = mNumber.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) mNumber.getText();
            selection.replace(cursorPos-1 , cursorPos, "");
            mNumber.setText(selection);

        }
    }

    void myEqualization() {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            result = (double)engine.eval(mNumber.getText().toString());
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null){
            mResult.setText("= " + result.doubleValue());
        }
    }

}