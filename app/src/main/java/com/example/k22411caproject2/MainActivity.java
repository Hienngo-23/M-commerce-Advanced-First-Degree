package com.example.k22411caproject2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setContentView(R.layout.activity_main);

        // Các khai báo và xử lý của bạn ở đây
        Button btnVi = findViewById(R.id.btn_vi);
        Button btnEn = findViewById(R.id.btn_en);
        Button btnFr = findViewById(R.id.btn_fr);
        Button btnEs = findViewById(R.id.btn_es);

        btnVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("vi");
            }
        });

        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });
        btnFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("fr");
            }
        });
        btnEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("es");
            }
        });
    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Restart Activity để áp dụng thay đổi ngôn ngữ
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

    private void addViews() {
        edtCoefficientA=findViewById(R.id.edtCoefficientA);
        edtCoefficientB=findViewById(R.id.edtCoefficientB);
        txtResult=findViewById(R.id.txtResult);
    }

    public void do_solution(View view) {
        //Để lấy giá trị hệ số a
        String hsa=edtCoefficientA.getText().toString();
        double a=Double.parseDouble(hsa);

        //Để lấy giá trị hệ số b
        double b=Double.parseDouble(edtCoefficientB.getText().toString());

        //Tiến hành giải và biện luận phương trình
        if (a==0 && b==0)
        {

            txtResult.setText(getResources().getText(R.string.title_infinity));
        }
        else if(a==0 && b!=0){
            txtResult.setText(getResources().getText(R.string.title_nosolution));
        }
        else
        {
            txtResult.setText("X="+(-b/a));
        }
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        //Đưa ô nhập liệu vào dòng hệ số A đẻ nhập cho lẹ
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }
}