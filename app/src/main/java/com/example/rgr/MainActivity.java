package com.example.rgr;

import static com.example.rgr.FirstFragment.cl;
import static com.example.rgr.FirstFragment.customAdapterCircularList;
import static com.example.rgr.FirstFragment.userType;
import static com.example.rgr.SavingHelper.readItems;
import static com.example.rgr.SavingHelper.writeItems;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rgr.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rgr.data.structure.CircularList;
import com.example.rgr.data.types.UserFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    String[] classesList = { "Integer", "Point"};
    String pointerClassesList = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, classesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pointerClassesList = (String)adapterView.getItemAtPosition(i);
                userType = UserFactory.getBuilderByName(pointerClassesList);
                cl.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this.getBaseContext(), "Я сделаль!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_save) {
            Toast.makeText(this.getBaseContext(), "Saving...", Toast.LENGTH_SHORT).show();
            writeItems(getBaseContext(), cl);
            return true;
        }

        if (id == R.id.action_load) {
            Toast.makeText(this.getBaseContext(), "Loading...", Toast.LENGTH_SHORT).show();
            cl.clear();
            cl.addAll((CircularList) readItems(getBaseContext()));
            if(cl.size() > 0) {
                String localClass = "";
                localClass = cl.getTailData().getClass().toString();
                localClass = localClass.replaceAll("[^A-Za-zА-Яа-я0-9]", "");
                if (!localClass.toLowerCase().contains(pointerClassesList.toLowerCase())) {
                    cl.clear();
                    Toast.makeText(this.getBaseContext(), "Choose another data type!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this.getBaseContext(), "Empty...", Toast.LENGTH_SHORT).show();
            }

            customAdapterCircularList.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}