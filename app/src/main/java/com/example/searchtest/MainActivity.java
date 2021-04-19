package com.example.searchtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.searchtest.adapter.DataRecyclerViewAdapter;
import com.example.searchtest.service.GeneratorService;
import com.example.searchtest.service.age.Age;
import com.example.searchtest.service.data.QueryService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.dataList)
    RecyclerView dataListView;

    @BindView(R.id.btn_search)
    Button btnSearch;

    @BindView(R.id.search_editText)
    AutoCompleteTextView searchEditText;

    private DataRecyclerViewAdapter adapter = null;
    private Age itemData = null;
    private List<Age> dataList;
    private ArrayList<String> nameList = null;
    List<Age> dataSearchList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        btnSearch.setOnClickListener(this);
        nameList = new ArrayList<>();
        getAge();
    }

    public void getAge(){
        QueryService service = GeneratorService.createServiceAge(QueryService.class);
        Call<List<Age>> call = service.getAge();
        call.enqueue(new Callback<List<Age>>() {
            @Override
            public void onResponse(Call<List<Age>> call, Response<List<Age>> response) {
                if(response.code() == 200){
                    dataList = response.body();
                    Log.i("======> response", dataList.toString());
                    adapter = new DataRecyclerViewAdapter(dataList, MainActivity.this);
                    dataListView.setHasFixedSize(true);
                    dataListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    dataListView.setAdapter(adapter);
                    setSearch();
                }
            }

            @Override
            public void onFailure(Call<List<Age>> call, Throwable t) {
                Log.e("======>", t.getMessage());
            }
        });
    }

    private void setSearch(){
        for (int i = 0;i < dataList.size();i++){
            nameList.add(dataList.get(i).getName().toString());
        }
        ArrayAdapter<String> adapterSearch = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_search, nameList);
        searchEditText.setAdapter(adapterSearch);
        searchEditText.setThreshold(1);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.insert(dataList);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                dataSearchList = new ArrayList<>(dataList);
                List<Age> temp = new ArrayList<>();
                int i = 0;
                while (i < dataSearchList.size()){
                    if(searchEditText.getText().toString().trim().equalsIgnoreCase(dataSearchList.get(i).getName())){
                        temp.add(dataSearchList.get(i));
                    }
                    dataSearchList.remove(i);
                    i = 0;
                }
                adapter.insert(temp);
                break;
        }
    }
}
