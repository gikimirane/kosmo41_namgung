package com.study.android.project_exam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class menulistItemView extends LinearLayout {
    private static final String TAG = "lecture";
    TextView textView1;
    TextView textView2;
    CheckBox checkBox;
    Spinner spinner;
    String[] count = {"0","1","2","3","4","5","6"};
    private View header;

    public menulistItemView(final Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menulist_item_view,this,true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),android.R.layout.simple_spinner_item,count);

        checkBox=findViewById(R.id.checkBox1);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        header = inflater.inflate(R.layout.activity_order, null, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d("TAG","view 리스너에서 설정하고 싶다.");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


    }

    public void setDrink(String drink){
        textView1.setText(drink);
    }
    public void setAmount(String amount) {textView2.setText(amount); }
    public void setCount(String count) { spinner.setSelection(Integer.parseInt(count));}
    public void setIscheck(boolean ch) { checkBox.setChecked(ch);}
}
