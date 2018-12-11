package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiahaoliuliu.chutoro.ui.MainApplication;
import com.jiahaoliuliu.chutoro.R;
import com.jiahaoliuliu.chutoro.ui.addtransaction.AddTransactionActivity;

import javax.inject.Inject;

public class TransactionsListActivity extends AppCompatActivity implements TransactionsListContract.View {

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

        // Request for permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},
                REQUEST_CODE_FOR_READ_SMS_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_FOR_READ_SMS_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Nothing
                } else {
                    // permission denied
                    // TODO: Show the screen to request the permission
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isReadSMSPermissionGuaranteed()) {
            presenter.updateTransactionsList();
        } else {
            // TODO: Show the user a dialog to request for permissions
        }
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
