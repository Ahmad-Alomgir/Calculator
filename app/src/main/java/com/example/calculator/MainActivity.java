package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result,solution;
    MaterialButton button_c,button_open_bracet,button_close_bracet;
    MaterialButton button_divide, button_multi, button_plus,button_minus,button_equal;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    MaterialButton button_ac,button_dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(button_c,R.id.button_c);
        assignId(button_open_bracet,R.id.button_open_bracet);
        assignId(button_close_bracet,R.id.button_close_bracet);
        assignId(button_divide,R.id.button_divide);
        assignId(button_multi,R.id.button_multi);
        assignId(button_minus,R.id.button_minus);
        assignId(button_plus,R.id.button_plus);
        assignId(button_equal,R.id.button_equal);
        assignId(button_ac,R.id.button_ac);
        assignId(button_0,R.id.button_0);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_dot,R.id.button_dot);



    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solution.getText().toString();
        if (buttonText.equals("AC"))
        {
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataCalculate = dataCalculate.substring(0,dataCalculate.length()-1);
        }else {
            dataCalculate = dataCalculate+buttonText;
        }


        solution.setText(dataCalculate);

        String finalResult = getResult(dataCalculate);

        if (!finalResult.equals("Err")){

            result.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1, null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return  finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}
