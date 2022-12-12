package com.example.rgr;

import static com.example.rgr.CustomAdapterCircularList.position;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rgr.databinding.FragmentFirstBinding;

import com.example.rgr.data.structure.CircularList;
import com.example.rgr.data.types.UserFactory;
import com.example.rgr.data.types.UserType;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    static CircularList cl = new CircularList();
    static UserType userType = UserFactory.getBuilderByName("Integer");

    static RecyclerView.Adapter customAdapterCircularList = new CustomAdapterCircularList(cl);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        binding.ViewList.setAdapter(customAdapterCircularList);
        binding.ViewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.ViewList.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Object x = userType.create();
                    binding.editTextTextPersonName.setText(x.toString());
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Editable editable = binding.editTextTextPersonName.getText();
                String str = editable == null ? "" : editable.toString();

                if (str.isEmpty()) {
                    Object x = userType.create();
                    binding.editTextTextPersonName.setText(x.toString());
                }
                try {
                    cl.addBack(userType.parseValue(str));
                }
                catch (Exception e){
                    System.out.println(e);
                }

                customAdapterCircularList.notifyDataSetChanged();
            }
        });

        binding.addPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Editable editable = binding.editTextTextPersonName.getText();
                String str = editable == null ? "" : editable.toString();

                if (str.isEmpty()) {
                    Object x = userType.create();
                    binding.editTextTextPersonName.setText(x.toString());
                }
                try {
                    cl.addAtPosition(userType.parseValue(str), position);
                }
                catch (Exception e){
                    System.out.println(e);
                }

                customAdapterCircularList.notifyDataSetChanged();
            }
        });

        binding.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != null) {
                    cl.removeAtPosition(position);
                }
                customAdapterCircularList.notifyDataSetChanged();
            }
        });

        binding.sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CircularList clSorted = new CircularList<>();
                clSorted = cl.mergeSort(userType.getTypeComparator());
                cl.clear();
                cl.addAll(clSorted);
                Toast.makeText(getContext(), cl.toString(), Toast.LENGTH_SHORT).show();

                customAdapterCircularList.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}