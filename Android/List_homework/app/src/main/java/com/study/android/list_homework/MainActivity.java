package com.study.android.list_homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    String [] names = {"홍길동","강감찬","을지문덕","양만춘","유관순","홍길동","강감찬","을지문덕","양만춘","유관순","홍길동","강감찬","을지문덕","양만춘","유관순","홍길동","강감찬","을지문덕"};
    String [] ages  = {"1991년","1990년","1984년","1988년","1977년","1991년","1990년","1984년","1988년","1977년",
                        "1991년","1990년","1984년","1988년","1977년","1991년","1990년","1984년"};
    String [] gender = {"남","남","여","여","남","여","남","남","여","여","남","여","남","남","여","여","남","여"};
    String [] phone = {"010-1234-5678","010-8888-7777","010-7777-8888","010-1234-5678","010-8888-7777","010-7777-8888","010-1234-5678","010-8888-7777","010-7777-8888","010-1234-5678"
                        ,"010-8888-7777","010-7777-8888","010-1234-5678","010-8888-7777","010-7777-8888","010-1234-5678","010-8888-7777","010-7777-8888"};
    String [] address = {"서울특별시 구로구","인천광역시 서구","인천광역시 부평구","서울특별시 금천구","서울특별시 구로구","인천광역시 서구","인천광역시 부평구",
                        "서울특별시 금천구","서울특별시 구로구","인천광역시 서구","인천광역시 부평구","서울특별시 금천구","서울특별시 구로구","인천광역시 서구","인천광역시 부평구","서울특별시 금천구","서울특별시 구로구","인천광역시 서구"};
    int [] images = {R.drawable.face1,R.drawable.face2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyAdapter adapter =new MyAdapter();
        ListView listView1=findViewById(R.id.listView1);
        listView1.setAdapter(adapter);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(),"전화번호 : "+phone[position],Toast.LENGTH_SHORT).show();
            }
        });

    }
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ItemView view = new ItemView(getApplicationContext(),gender[position]);

            if(gender[position].equals("남")){
                view.setImage(images[0]);
            }else if(gender[position].equals("여")){
                view.setImage(images[1]);
            }

            view.setName(names[position]);
            view.setAge(ages[position]);
            view.setAddress(address[position]);

            return view;
        }
    }
}
