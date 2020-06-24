package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isOpPressed = false;

    private double firstNumber = 0;

    private int secondNumberIndex = 0;

    private char currentOp = 0;

    private boolean isDot = false;

    private TextView calculatorScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calculatorScreen = findViewById(R.id.calculator_screen);

        final Button button0 = findViewById(R.id.button_0);
        final Button button1 = findViewById(R.id.button_1);
        final Button button2 = findViewById(R.id.button_2);
        final Button button3 = findViewById(R.id.button_3);
        final Button button4 = findViewById(R.id.button_4);
        final Button button5 = findViewById(R.id.button_5);
        final Button button6 = findViewById(R.id.button_6);
        final Button button7 = findViewById(R.id.button_7);
        final Button button8 = findViewById(R.id.button_8);
        final Button button9 = findViewById(R.id.button_9);
        final Button equals = findViewById(R.id.button_equals);
        final Button plus = findViewById(R.id.button_plus);
        final Button minus = findViewById(R.id.button_minus);
        final Button multiply = findViewById(R.id.button_multiply);
        final Button divide = findViewById(R.id.button_divide);
        final Button clear = findViewById(R.id.button_C);
        final Button delete = findViewById(R.id.button_del);
        final Button dot = findViewById(R.id.button_dot);

        final View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = view.getId();
                switch (id) {
                    case R.id.button_0:
                        calculatorScreen.append("0");
                        break;
                    case R.id.button_1:
                        calculatorScreen.append("1");
                        break;
                    case R.id.button_2:
                        calculatorScreen.append("2");
                        break;
                    case R.id.button_3:
                        calculatorScreen.append("3");
                        break;
                    case R.id.button_4:
                        calculatorScreen.append("4");
                        break;
                    case R.id.button_5:
                        calculatorScreen.append("5");
                        break;
                    case R.id.button_6:
                        calculatorScreen.append("6");
                        break;
                    case R.id.button_7:
                        calculatorScreen.append("7");
                        break;
                    case R.id.button_8:
                        calculatorScreen.append("8");
                        break;
                    case R.id.button_9:
                        calculatorScreen.append("9");
                        break;

                    case R.id.button_equals:
                        if(isOpPressed) {
                            String screenContent = calculatorScreen.getText().toString();
                            char lastChar = screenContent.charAt(screenContent.length() - 1);
                            if (lastChar == '+' || lastChar == '-' || lastChar == '*'|| lastChar == '/') {
                                return;
                            }
                            String secondNumberString = screenContent.substring(secondNumberIndex, screenContent.length());
                            double secondNumber = Double.parseDouble(secondNumberString);


                            if (currentOp == '+') {
                                secondNumber += firstNumber;
                            } else if (currentOp == '-') {
                                secondNumber = firstNumber - secondNumber;
                            } else if (currentOp == '*') {
                                secondNumber *= firstNumber;
                            } else if (currentOp == '/') {
                                if (secondNumber == 0) {
                                    return;
                                }
                                secondNumber = firstNumber / secondNumber;
                            }
                            calculatorScreen.setText(String.valueOf(secondNumber));
                            String result = String.valueOf(secondNumber);
                            if (result.endsWith(".0")) {
                                result = result.substring(0, result.length() - 2);
                            }
                            calculatorScreen.setText(result);
                            isOpPressed = false;
                        }
                        break;

                    case R.id.button_plus:
                        opPressed('+');
                        break;
                    case R.id.button_minus:
                        opPressed('-');
                        break;
                    case R.id.button_multiply:
                        opPressed('*');
                        break;
                    case R.id.button_divide:
                        opPressed('/');
                        break;

                    case R.id.button_dot:
                        if (!isDot) {
                            String screenContent = calculatorScreen.getText().toString();
                            final int screenContenLenght = screenContent.length();
                            if (screenContenLenght < 1) {
                                return;
                            }
                            char lastChar = screenContent.charAt(screenContenLenght - 1);
                            if (lastChar == '+' || lastChar == '-' || lastChar == '*'|| lastChar == '/') {
                                return;
                            }
                            calculatorScreen.append(".");
                            isDot = true;
                        }
                        break;



                }
            }
        };

        button0.setOnClickListener(calculatorListener);
        button1.setOnClickListener(calculatorListener);
        button2.setOnClickListener(calculatorListener);
        button3.setOnClickListener(calculatorListener);
        button4.setOnClickListener(calculatorListener);
        button5.setOnClickListener(calculatorListener);
        button6.setOnClickListener(calculatorListener);
        button7.setOnClickListener(calculatorListener);
        button8.setOnClickListener(calculatorListener);
        button9.setOnClickListener(calculatorListener);
        dot.setOnClickListener(calculatorListener);
        plus.setOnClickListener(calculatorListener);
        minus.setOnClickListener(calculatorListener);
        multiply.setOnClickListener(calculatorListener);
        divide.setOnClickListener(calculatorListener);
        equals.setOnClickListener(calculatorListener);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayedElements = calculatorScreen.getText().toString();
                int lenght = displayedElements.length();
                if (lenght > 0) {
                    displayedElements = displayedElements.substring(0, lenght - 1);
                    calculatorScreen.setText(displayedElements);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorScreen.setText("");
                isOpPressed = false;
                isDot = false;
            }
        });


    }

    private void opPressed(char operation) {
        if (isOpPressed) {
            return;
        }
        String screenContent = calculatorScreen.getText().toString();
        final int screenContentLength = screenContent.length();
        if (screenContentLength < 1) {
            return;
        }
        secondNumberIndex = screenContentLength + 1;
        firstNumber = Double.parseDouble(screenContent);
        calculatorScreen.append(String.valueOf(operation));
        isOpPressed = true;
        isDot = false;
        currentOp = operation;
    }


}