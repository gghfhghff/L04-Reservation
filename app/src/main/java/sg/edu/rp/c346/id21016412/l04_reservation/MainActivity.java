package sg.edu.rp.c346.id21016412.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etNumber;
    EditText etSize;
    DatePicker dp;
    TimePicker tp;
    RadioGroup rgArea;
    RadioButton rbAreaNonSmoking;
    RadioButton rbAreaSmoking;
    Button btnConfirm;
    Button btnReset;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etNumber = findViewById(R.id.editTextNumber);
        etSize = findViewById(R.id.editTextSize);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        rgArea = findViewById(R.id.radioGroupArea);
        rbAreaNonSmoking = findViewById(R.id.radioButtonAreaNonSmoking);
        rbAreaSmoking = findViewById(R.id.radioButtonAreaSmoking);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);
        tvInfo = findViewById(R.id.textViewInfo);

        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        dp.updateDate(2020, 5, 1);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker v, int hourOfDay, int minute) {
                int timeHour = tp.getCurrentHour();
                if ((timeHour >= 21) || (timeHour <= 7)) {
                    tp.setCurrentHour(19);
                    tp.setCurrentMinute(30);
                    Toast.makeText(getApplicationContext(), "Reservations can only be made for 08:00 to 20:59", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((etName.getText().toString().isEmpty()) || (etNumber.getText().toString().isEmpty()) || (etSize.getText().toString().isEmpty()) || (Double.parseDouble(etSize.getText().toString()) <= 0)) {
                    Toast.makeText(getApplicationContext(), "Please check all required fields!", Toast.LENGTH_LONG).show();
                } else {
                    String name = etName.getText().toString();
                    String number = etNumber.getText().toString();
                    String size = etSize.getText().toString();

                    int dateDay = dp.getDayOfMonth();
                    int dateMonth = dp.getMonth() + 1;
                    int dateYear = dp.getYear();
                    String date = dateDay + "/" + dateMonth + "/" + dateYear;

                    int timeHour = tp.getCurrentHour();
                    int timeMinute = tp.getCurrentMinute();
                    String time = timeHour + ":" + timeMinute;

                    String area = "";

                    int checkedRadioId = rgArea.getCheckedRadioButtonId();
                    if (checkedRadioId == R.id.radioButtonAreaNonSmoking) {
                        area = "Non-Smoking";
                    }
                    if (checkedRadioId == R.id.radioButtonAreaSmoking) {
                        area = "Smoking";
                    }

                    String info = String.format("Name: %s\nMobile Number: %s\nGroup Size: %s\nDate of Reservation: %s\nTime of Reservation: %s\nArea: %s", name, number, size, date, time, area);
                    tvInfo.setText(info);
                }
             }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText("");
                etNumber.setText("");
                etSize.setText("");
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
                rbAreaNonSmoking.setChecked(true);
                rbAreaSmoking.setChecked(false);
                tvInfo.setText("");
            }
        });
    }
}