package com.example.myapp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    static public double result = 0;
    static public String operation = "=";
    static public boolean newValue = true;
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.Output);

        Resources res = getResources();
        int id;

        for (int i = 0; i < 10; i++){
            id = res.getIdentifier("num_" + Integer.toString(i), "id", getPackageName());

            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentNum = getResources().getResourceName(v.getId());
                    currentNum = currentNum.substring(currentNum.length() - 1); //Получаем текущую нажатую цифру
                    String str = text.getText().toString();
                    Double number = new Double(str);

                    if (number == 0 || newValue)
                        text.setText(currentNum);
                    else
                        text.append(currentNum);

                    newValue = false;
                }
            });
        }

        findViewById(R.id.num_00).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = text.getText().toString();
                Double number = new Double(str);

                if (number != 0 && !newValue){
                    newValue = false;
                    text.append("00");
                }
            }
        });
    }

    public void onClickOperation(View v){
        //text.setText("AZazazazaz");

        String currentText = text.getText().toString();
        Double currentNumber = new Double(currentText);

        switch(v.getId()){
            case R.id.clr:

                result = 0;
                newValue = true;
                operation = "=";
                text.setText("0");

                break;

            case R.id.change_sign:

                if (currentNumber < 0)
                    text.setText(currentText.substring(1));
                else
                    text.setText("-" + currentText);

                break;

            case R.id.addition:

                result = implementationOperation(result, currentNumber, operation);
                operation = "+";
                newValue = true;

                text.setText(Double.toString(result));
                break;

            case R.id.multiplication:

                result = implementationOperation(result, currentNumber, operation);
                operation = "*";
                newValue = true;

                text.setText(Double.toString(result));

                break;

            case R.id.subtraction:

                result = implementationOperation(result, currentNumber, operation);
                operation = "-";
                newValue = true;

                text.setText(Double.toString(result));

                break;

            case R.id.divide:

                result = implementationOperation(result, currentNumber, operation);
                operation = "/";
                newValue = true;

                text.setText(Double.toString(result));

                break;

            case R.id.dot:

                if (!currentText.contains("."))
                    text.setText(currentText + ".");

                break;

            case R.id.result:

                result = implementationOperation(result, currentNumber, operation);
                text.setText(Double.toString(result));
                result = 0;
                operation = "=";
                newValue = true;

                break;
        }
    }

    public double implementationOperation(double a, double b, String oper){

        double result = a;

        if (oper == "+")
            result += b;
        else if (oper == "-")
            result -= b;
        else if (oper == "/")
            result /= b;
        else if (oper == "*")
            result *= b;
        else
            result = b;

        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
