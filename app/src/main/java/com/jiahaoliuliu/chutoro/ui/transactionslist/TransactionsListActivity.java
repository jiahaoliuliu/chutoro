package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jiahaoliuliu.chutoro.ui.MainApplication;
import com.jiahaoliuliu.chutoro.R;
import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import javax.inject.Inject;

public class TransactionsListActivity extends AppCompatActivity implements TransactionsListContract.View {

    private static final String TAG = "TransactionsListActivity";

    @Inject
    protected TransactionsListContract.Presenter presenter;

    private RecyclerView recyclerView;
    private TransactionsListAdapter transactionsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);

        MainApplication.getMainComponent().inject(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        transactionsListAdapter = new TransactionsListAdapter();
        recyclerView.setAdapter(transactionsListAdapter);

        presenter.setView(this);
        presenter.retrieveTransactionsList();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void showTransactionsList(List<? extends ITransaction> transactionsList) {
        Log.v(TAG, "List of items retrieved " + transactionsList.toString());
        transactionsListAdapter.setTransactionsList(transactionsList);
    }

    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }
}
