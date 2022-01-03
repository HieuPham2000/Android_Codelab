package com.example.android.navigationexcercise.ui.radixtransfer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.navigationexcercise.MainActivity;
import com.example.android.navigationexcercise.R;
import com.example.android.navigationexcercise.Utils;
import com.example.android.navigationexcercise.databinding.FragmentRadixTransferBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixTransferFragment extends Fragment {

    private RadixTransferViewModel radixTransferViewModel;
    private FragmentRadixTransferBinding binding;

    private Spinner spinnerSrc, spinnerDes;
    private Button btnConvert;
    private EditText txtInput;
    private TextView txtResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        radixTransferViewModel =
                new ViewModelProvider(this).get(RadixTransferViewModel.class);

        binding = FragmentRadixTransferBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initViewById();
        initValue();
        initEvent();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * findViewById tất cả các thành phần trên activity
     * Created by: PTHIEU 27.11.2021
     */
    private void initViewById() {
        spinnerSrc = binding.spinnerSrc;
        spinnerDes = binding.spinnerDes;

        txtInput = binding.txtInput;
        txtResult = binding.txtResult;
        btnConvert = binding.btnConvert;
    }

    /**
     * Khởi tạo giá trị ban đầu
     * Created by: PTHIEU 27.11.2021
     */
    private void initValue() {
        // Giá trị spinner lựa chọn
        List<String> items = new ArrayList<>(
                Arrays.asList(Utils.BASE_10, Utils.BASE_2, Utils.BASE_8, Utils.BASE_16)
        );

        Adapter adapter = new ArrayAdapter<>(binding.getRoot().getContext(),
                R.layout.support_simple_spinner_dropdown_item,
                items);
        spinnerSrc.setAdapter((SpinnerAdapter) adapter);
        spinnerDes.setAdapter((SpinnerAdapter) adapter);
    }

    /**
     * Khởi tạo/gán sự kiện
     * Created by: PTHIEU 27.11.2021
     */
    private void initEvent() {
        btnConvert.setOnClickListener(view -> doConvert(view));
    }


    /**
     * Thực hiện hành động chuyển đổi hệ cơ số
     * @param view Đối tượng view
     * Created by: PTHIEU 27.11.2021
     */
    public void doConvert(View view) {
        // ẩn bàn phím
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String input = txtInput.getText().toString();

        // nếu đầu vào trống
        if(input.isEmpty()) {
            Toast.makeText(binding.getRoot().getContext(),
                    "Cần nhập giá trị đầu vào!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // lấy ra giá trị hệ cơ số từ 2 spinner
        int fromBase = Utils.MAP_RADIX.get(spinnerSrc.getSelectedItem());
        int toBase = Utils.MAP_RADIX.get(spinnerDes.getSelectedItem());

        // thực hiện chuyển đổi hệ cơ số
        try {
            String result = Utils.convertFromBaseToBase(input, fromBase, toBase);
            txtResult.setText("Kết quả: " + result);
        } catch (NumberFormatException ex) {
            // trường hợp đầu vào không thỏa mãn hệ cơ số
            Toast.makeText(binding.getRoot().getContext(),
                    "Giá trị nhập vào không hợp lệ!",
                    Toast.LENGTH_SHORT).show();
        }

    }
}