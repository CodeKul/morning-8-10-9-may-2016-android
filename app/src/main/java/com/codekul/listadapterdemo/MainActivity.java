package com.codekul.listadapterdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListView listView=(ListView) findViewById(R.id.listCountries);
        GridView listView=(GridView) findViewById(R.id.listCountries);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textInfo = (TextView) findViewById(R.id.textInfo);

                TextView textName = (TextView) view.findViewById(R.id.textItem);
                ImageView imageIcon = (ImageView) view.findViewById(R .id.imageIcon);

                textInfo.setText(textName.getText().toString());

                Log.i("@codekul","Pos - "+position);
                Log.i("@codekul","Id - "+id);
                Log.i("@codekul","Text - "+textName.getText().toString());
            }
        });

        ArrayList<MyItem> dataSet = new ArrayList<>();
        MyItem item1 = new MyItem();
        item1.setImgIcon(android.R.drawable.sym_call_outgoing);
        item1.setName("Android");
        dataSet.add(item1);

        MyItem item2 = new MyItem();
        item2.setImgIcon(android.R.drawable.sym_call_incoming);
        item2.setName("iOS");
        dataSet.add(item2);

        MyItem item3 = new MyItem();
        item3.setImgIcon(android.R.drawable.sym_call_missed);
        item3.setName("RIM");
        dataSet.add(item3);

        MyItem item4 = new MyItem();
        item4.setImgIcon(R.mipmap.ic_launcher);
        item4.setName("Symbian");
        dataSet.add(item4);

        MyAdapter adapter=new MyAdapter(this,dataSet);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSet);
        listView.setAdapter(adapter);
    }
    public class MyItem {
        private int imgIcon;
        private String name;

        public int getImgIcon() {
            return imgIcon;
        }

        public void setImgIcon(int imgIcon) {
            this.imgIcon = imgIcon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public class MyAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<MyItem> dataSet;
        private LayoutInflater inflater;

        public MyAdapter(Context context,ArrayList<MyItem> dataSet){

            this.context=context;
            this.dataSet=dataSet;
            inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return dataSet.size();
        }

        @Override
        public Object getItem(int position) {
            return dataSet.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view=inflater.inflate(R.layout.my_item,null,false);

            MyItem item = dataSet.get(position);
            ImageView imageIcon = (ImageView) view.findViewById(R.id.imageIcon);
            imageIcon.setImageResource(item.getImgIcon());

            TextView textView=(TextView)view.findViewById(R.id.textItem);
            textView.setText(item.getName());

            return view;
        }
    }
}
