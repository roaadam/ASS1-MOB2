package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragmentInteractionListener,Fragment3.OnCheckBoxChangeListener {

    private ActivityMainBinding binding;

    private int currentFragment = 1;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        Fragment2 fragment2 = Fragment2.newInstance(null, null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue2.setOnClickListener(view -> {
            if (currentFragment == 1) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();

                currentFragment = 2;

            } else if (currentFragment == 2) {

                Fragment2 current = (Fragment2) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);

                if (current != null) {
                    current.sendNameToActivity();
                }
            }
        });
    }

    @Override
    public void onContinueClicked(String name) {
        this.name=name;
        Fragment3 fragment3=Fragment3.newInstance(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment3).addToBackStack(null).commit();
        binding.buttonContinue2.setVisibility(View.GONE);
    }

    @Override
    public void onCheckBoxChanged(boolean isChecked) {
        Fragment3 fragment3 = (Fragment3) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (fragment3 != null) {
            fragment3.updateButton(isChecked);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Fragment current = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (current instanceof Fragment2) {
            binding.buttonContinue2.setVisibility(View.VISIBLE);
            currentFragment = 2;
        } else if (current instanceof Fragment1) {
            binding.buttonContinue2.setVisibility(View.VISIBLE);
            currentFragment = 1;
        }
    }
}