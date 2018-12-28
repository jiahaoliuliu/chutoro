package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jiahaoliuliu.chutoro.R;
import com.jiahaoliuliu.chutoro.ui.MainApplication;
import com.jiahaoliuliu.chutoro.ui.addtransaction.AddTransactionActivity;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionsListActivity extends AppCompatActivity implements
        TransactionsListContract.View {

    private static final int REQUEST_CODE_FOR_READ_SMS_PERMISSION = 1;
    private static final int REQUEST_CODE_FOR_ADD_NEW_TRANSACTIONS = 1000;

    @Inject
    protected TransactionsListContract.Presenter presenter;

    private RecyclerView recyclerView;
    private TransactionsListAdapter transactionsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);

        MainApplication.getMainComponent().inject(this);

        // Link the views
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddTransactionActivity.class);
            startActivityForResult(intent, REQUEST_CODE_FOR_ADD_NEW_TRANSACTIONS);
        });

        transactionsListAdapter = new TransactionsListAdapter();
        recyclerView.setAdapter(transactionsListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        presenter.setView(this);
        presenter.retrieveTransactionsList().observe(this,
            transactionsList -> transactionsListAdapter.setTransactionsList(transactionsList)
        );
    }

    private void updateTransactionsList() {
        // Request for permission if it is not granted
        if (isReadSMSPermissionGuaranteed()) {
            presenter.updateTransactionsList();
        } else {
            // Request for the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},
                    REQUEST_CODE_FOR_READ_SMS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_FOR_READ_SMS_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Request to update the list of transactions
                    presenter.updateTransactionsList();
                } else {
                    // permission denied
                    // TODO: Show the screen to request the permission
                }
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTransactionsList();
    }

    private boolean isReadSMSPermissionGuaranteed() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }
}
