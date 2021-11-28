package com.example.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //region Attributes
    /**
     * text view hiển thị kết quả và phép tính
     */
    private TextView textResult, textCalculation;

    /**
     * nút toán tử, nút control
     */
    private Button btnPlus, btnMinus, btnMultiply, btnDivision,
            btnCE, btnC, btnSign, btnDot, btnEqual;
    private ImageButton btnBackspace;

    /**
     * List nút số
     */
    private List<Button> btnNumbers;

    private String tmpOperator = null, prevAction = null;
    private int tmpResult = 0;
    //endregion

    //region Constants
    private final String BUTTON_TAG = "Button";
    /**
     * Định nghĩa các hằng biểu diễn toán tử
     */
    private final String PLUS = "\u002B";
    private final String MINUS = "\u2212";
    private final String MULTIPLY = "\u00D7";
    private final String DIVISION = "\u00F7";

    /**
     * Định nghĩa các hằng action
     */
    private final String PRESS_NUMBER = "PRESS_NUMBER";
    private final String PRESS_OPERATOR = "PRESS_OPERATOR";
    private final String PRESS_CALCULATE = "PRESS_CALCULATE";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.initEvent();
    }

    /**
     * khởi tạo các view bằng các findViewById
     * pthieu 7.11.2021
     */
    public void initView() {
        // các text view
        textResult = findViewById(R.id.text_result);
        textCalculation = findViewById(R.id.text_caculation);
        textResult.setText("0");
        textCalculation.setText("");

        // các nút số
        Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnNumbers = Arrays.asList(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);

        // các nút toán tử
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivision = findViewById(R.id.btn_division);

        // các nút điều khiển
        btnCE = findViewById(R.id.btn_CE);
        btnC = findViewById(R.id.btn_C);
        btnBackspace = findViewById(R.id.btn_backspace);
        btnDot = findViewById(R.id.btn_dot);
        btnSign = findViewById(R.id.btn_sign);
        btnEqual = findViewById(R.id.btn_equal);
    }

    /**
     * Khởi tạo sự kiện cho các control
     * pthieu 7.11.2021
     */
    public void initEvent() {
        // sự kiện khi nhấn button số
        for(Button btnNumber: btnNumbers) {
            btnNumber.setOnClickListener(MainActivity.this::onClickBtnNumber);
        }

        // sự kiện khi nhấn button CE
        btnCE.setOnClickListener(MainActivity.this::onClickBtnClearEntry);

        // sự kiện khi nhấn button C
        btnC.setOnClickListener(MainActivity.this::onClickBtnClear);


        // sự kiện khi nhấn button Backspace
        btnBackspace.setOnClickListener(MainActivity.this::onClickBtnBackspace);

        // sự kiện khi nhấn button đổi dấu
        btnSign.setOnClickListener(MainActivity.this::onClickBtnSign);

        // sự kiện nhấn vào nút toán tử
        btnPlus.setOnClickListener(view -> onClickBtnOperator(PLUS));
        btnMinus.setOnClickListener(view -> onClickBtnOperator(MINUS));
        btnMultiply.setOnClickListener(view -> onClickBtnOperator(MULTIPLY));
        btnDivision.setOnClickListener(view -> onClickBtnOperator(DIVISION));

        // sự kiện nhấn nút =
        btnEqual.setOnClickListener(view -> onClickBtnEqual());
    }

    /**
     * Xủ lý sự kiện click vào các nút số
     * @param view  button số
     * pthieu 7.11.2021
     */
    public void onClickBtnNumber(View view) {
        // lấy ra value của nút số
        Button btnNumber = (Button)view;
        String btnNumberValue = btnNumber.getText().toString();
        // log
        Log.v(BUTTON_TAG, btnNumberValue);
        // set giá trị cho ô text view textResult
        String textResultValue = textResult.getText().toString();

        // kiểm tra textResult hiện tại
        // nếu giá trị đang là 0
        // hoặc trước đó ấn nút =, +, -, x, /
        if(textResultValue.equals("0") ||
                (prevAction != null && !prevAction.equals(PRESS_NUMBER))) {
            textResultValue = btnNumberValue;
        } else {
            textResultValue += btnNumberValue;
        }
        textResult.setText(textResultValue);
        prevAction = PRESS_NUMBER;
    }

    /**
     * Xủ lý sự kiện click vào nút Backspace
     * @param view  button Backspace
     * pthieu 7.11.2021
     */
    public void onClickBtnBackspace(View view) {
        Log.v(BUTTON_TAG, "backspace");

        String textResultValue = textResult.getText().toString();
//        int len = textResultValue.length();
//        if(len > 1) {
//            textResultValue = textResultValue.substring(0, len - 1);
//            if(textResultValue.equals("-")) {
//                textResultValue = "0";
//            }
//        } else {
//            textResultValue = "0";
//        }
//        textResult.setText(textResultValue);
        int value = Integer.parseInt(textResultValue);
        // chia 10 (lấy phần nguyên) => xóa chữ số cuối
        value = value / 10;
        textResult.setText(String.valueOf(value));
    }

    /**
     * Xủ lý sự kiện click vào nút Clear Entry (CE)
     * @param view  button CE
     * pthieu 7.11.2021
     */
    public void onClickBtnClearEntry(View view) {
        Log.v(BUTTON_TAG, "Clear entry");
        textResult.setText("0");
    }

    /**
     * Xủ lý sự kiện click vào nút Clear(C)
     * @param view  button CE
     * pthieu 7.11.2021
     */
    public void onClickBtnClear(View view) {
        Log.v(BUTTON_TAG, "Clear");
        textResult.setText("0");
        tmpResult = 0;
        textCalculation.setText("");
    }

    /**
     * Xủ lý sự kiện click vào nút Sign(+/-)
     * @param view  button sign
     * pthieu 7.11.2021
     */
    public void onClickBtnSign(View view) {
        Log.v(BUTTON_TAG, "Change sign");
        String textResultValue = textResult.getText().toString();
        int value = Integer.parseInt(textResultValue);
        value = - value;
        textResult.setText(String.valueOf(value));
    }

    /**
     * Xủ lý sự kiện click vào các nút toán tử
     * @param operator hằng thể hiện toán tử
     * pthieu 7.11.2021
     */
    public void onClickBtnOperator(String operator) {
        Log.v(BUTTON_TAG, operator);
        // Nếu ngay trước đó vừa ấn vào nút toán tử thì sẽ không thực hiện gì
        // Ngược lại:
        if(prevAction != null && !prevAction.equals(PRESS_OPERATOR)) {
            // lưu toán tử hiện tại
            tmpOperator = operator;
            // lấy text từ ô result, hiển thị lên ô phép tính
            String textResultValue = textResult.getText().toString();
            tmpResult = Integer.parseInt(textResultValue);
            String calculation = textResultValue + " " + operator;
            textCalculation.setText(calculation);

            prevAction = PRESS_OPERATOR;
        }
    }

    /**
     * Chuyển định dạng xâu số âm, vd "-86" sang "negate(86)"
     * @param strNum xâu số
     * @return xâu số đã định dạng
     */
    public String convertFormatNegativeNumber(String strNum) {
        int value = Integer.parseInt(strNum);
        if(value < 0) {
            value = - value;
            return "negate(" + value + ")";
        }
        return strNum;
    }

    /**
     * Xủ lý việc tính toán
     * @param operator hằng biểu diễn toán tử
     * @param a toán hạng 1
     * @param b toán hạng 2
     * pthieu 7.11.2021
     */
    public int doCalculation(String operator, int a, int b) {
        Log.v(BUTTON_TAG, operator);
        try {
            switch (operator) {
                case PLUS:
                    return a + b;
                case MINUS:
                    return a - b;
                case MULTIPLY:
                    return a * b;
                case DIVISION:
                    // TODO: trường hợp b = 0
                    if(b == 0) {
                        Toast.makeText(MainActivity.this,
                                "Can't devide by zero",
                                Toast.LENGTH_SHORT).show();
                        return 0;
                    } else {
                        return a / b;
                    }
                default:
                    return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Xủ lý sự kiện click vào nút =
     * pthieu 7.11.2021
     */
    public void onClickBtnEqual() {
        Log.v(BUTTON_TAG, "equal");
        prevAction = PRESS_CALCULATE;

        String textResultValue = textResult.getText().toString();
        int lastOperand = Integer.parseInt(textResultValue);
        if(tmpOperator != null && !tmpOperator.isEmpty()) {
            int result = this.doCalculation(tmpOperator, tmpResult, lastOperand);
            textResult.setText(String.valueOf(result));
            textCalculation.setText("");
        }
    }
}