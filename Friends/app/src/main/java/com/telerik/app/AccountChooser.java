package com.telerik.app;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.telerik.app.tasks.GoogleLoginTask;

import model.BaseViewModel;

public class AccountChooser extends Dialog implements AdapterView.OnItemClickListener {
    String[] accounts;
    private Activity activity;

    public AccountChooser(Activity activity, String[] accounts) {
        super(activity);
        this.activity = activity;
        this.accounts = accounts;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.accounts_list);

        ListView listView = (ListView) findViewById(R.id.al_accounts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, this.accounts);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String accountName = this.accounts[position];
        BaseViewModel.getInstance().setSelectedAccount(accountName);
        new GoogleLoginTask(this.activity, accountName).execute((Void) null);
        dismiss();
    }
}
