package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
    private static final int REQUEST_CODE_FOR_READ_SMS_PERMISSION = 1;

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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        presenter.setView(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS},
                    REQUEST_CODE_FOR_READ_SMS_PERMISSION);
        } else {
            presenter.retrieveTransactionsList().observe(this,
                    transactionsList -> {
                        transactionsListAdapter.setTransactionsList(transactionsList);
                }
            );
        }
    }

//    @SuppressLint("LongLogTag")
//    @Override
//    public void showTransactionsList(List<? extends ITransaction> transactionsList) {
//        Log.v(TAG, "List of items retrieved " + transactionsList.toString());
//        transactionsListAdapter.setTransactionsList(transactionsList);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_FOR_READ_SMS_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    presenter.retrieveTransactionsList();
                } else {
                    // permission denied
                    finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }
}
