package th.co.omc.mvpsample.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import th.co.omc.mvpsample.R;
import th.co.omc.mvpsample.model.OrderhistoryModel;
import th.co.omc.mvpsample.view.InteractorView;

public class MainActivity extends AppCompatActivity implements InteractorView {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_order);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void setRecyclerView(List<OrderhistoryModel> orderhistoryModelList) {

    }
}
