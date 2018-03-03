package com.example.juliana.listviewpersonalizado;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //lists
    ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();

    //class
    Restaurant restaurant;

    //srings
    private String stringName, stringAddress, stringPoints, stringRestaurantType;

    //components
    private ListView listViewRestaurants;
    private Button buttonCreate;
    private EditText editTextName,editTextAddress,editTextPoints;
    private RadioButton radioButtonGourmet,radioButtonBuffet,radioButtonFastFood;

    //listview simple
    List<String> listaSimple =  Arrays.asList("There aren't restaurants");
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRestaurants = (ListView)findViewById(R.id.mainActivity_listViewRestaurants);
        //button
        buttonCreate = (Button) findViewById(R.id.mainActivity_buttonCreate);

        //edit texts
        editTextAddress = (EditText) findViewById(R.id.mainActivity_editTextAddress);
        editTextName = (EditText) findViewById(R.id.mainActivity_editTextName);
        editTextPoints = (EditText) findViewById(R.id.mainActivity_editTextPoints);

        //radio buttons
        radioButtonGourmet = (RadioButton) findViewById(R.id.mainActivity_radioButtonGourmet);
        radioButtonBuffet = (RadioButton) findViewById(R.id.mainActivity_radioButtonBuffet);
        radioButtonFastFood = (RadioButton) findViewById(R.id.mainActivity_radioButtonFastFood);


        buttonCreate.setOnClickListener(onCreate);


    }

    @Override
    protected void onResume() {
        adapterFunction();
        super.onResume();
    }

    private View.OnClickListener onCreate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(editTextName.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Empty name",Toast.LENGTH_SHORT).show();
            }
            else if(editTextAddress.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Empty address",Toast.LENGTH_SHORT).show();
            }
            else if(editTextPoints.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Empty points",Toast.LENGTH_SHORT).show();
            }
            else {
                stringAddress = editTextAddress.getText().toString();
                stringName = editTextName.getText().toString();
                stringPoints = editTextPoints.getText().toString();
                if (radioButtonFastFood.isChecked()) {
                    stringRestaurantType = Constants.getTypeFastFood();
                } else if (radioButtonBuffet.isChecked()) {
                    stringRestaurantType = Constants.getTypeBuffet();
                } else {
                    stringRestaurantType = Constants.getTypeGourmet();
                }
                restaurant = new Restaurant(stringName, stringPoints, stringAddress, stringRestaurantType);
                restaurantsList.add(restaurant);
                adapterFunction();
            }
        }
    };
    public void adapterFunction(){

        if(restaurantsList.size()>0){
            listViewRestaurants.setAdapter(new viewAdapter(this));      }
        else{
            simpleList();
        }

    }

    public class viewAdapter extends BaseAdapter {
        LayoutInflater layoutInflater;
        public viewAdapter(Context context){
            layoutInflater = layoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return restaurantsList.size();
        }

        @Override
        public Object getItem(int i) {
            return restaurantsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view = layoutInflater.inflate(R.layout.row,null);
            }
            final int posicionListView = i;
            final TextView titleName = (TextView)view.findViewById(R.id.rowTitleName);
            final TextView titleAddress = (TextView)view.findViewById(R.id.rowTitleAddress);
            final TextView titlePoints = (TextView)view.findViewById(R.id.rowTitlePoints);


            titleName.setText(R.string.stringTitleName);
            titleAddress.setText(R.string.stringTitleAddress);
            titlePoints.setText(R.string.stringTitlePoints);

            final ImageView image = (ImageView)view.findViewById(R.id.rowImageView);
            final TextView name = (TextView)view.findViewById(R.id.rowName);
            final TextView address = (TextView)view.findViewById(R.id.rowAddress);
            final TextView points = (TextView)view.findViewById(R.id.rowPoints);

            name.setText(restaurantsList.get(posicionListView).getName());
            address.setText(restaurantsList.get(posicionListView).getAddress());
            points.setText(restaurantsList.get(posicionListView).getPoints());

            stringRestaurantType = restaurantsList.get(posicionListView).getImage();
            if(stringRestaurantType.equals(Constants.getTypeBuffet())){
                image.setImageResource(R.drawable.buffet);
            }
            else if(stringRestaurantType.equals(Constants.getTypeFastFood())){
                image.setImageResource(R.drawable.fast_food);
            }
            else{
                image.setImageResource(R.drawable.gourmet);
            }

            titleName.setTextColor(getResources().getColor(R.color.title));
            titleAddress.setTextColor(getResources().getColor(R.color.title));
            titlePoints.setTextColor(getResources().getColor(R.color.title));


            name.setTextColor(getResources().getColor(R.color.letters));
            address.setTextColor(getResources().getColor(R.color.letters));
            points.setTextColor(getResources().getColor(R.color.letters));

            return view;
        }
    }
    public void simpleList(){
        adapter = new ArrayAdapter<String>(this,R.layout.simple_linear_layout
                , listaSimple);
        listViewRestaurants.setAdapter(adapter);
    }
}
