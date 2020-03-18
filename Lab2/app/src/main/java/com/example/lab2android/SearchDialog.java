package com.example.lab2android;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SearchDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View search = inflater.inflate(R.layout.search, null);
        final EditText productName = (EditText) search.findViewById(R.id.productName);

        builder.setTitle("Search for product: ")
                .setView(search)
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity mainActivity = new MainActivity();

                        String productNameAsString = productName.getText().toString();
                        String[] products = mainActivity.productsTitle;
                        boolean exist = false;
                        for (String product : products) {
                            if (product.toLowerCase().equals(productNameAsString.toLowerCase())) {
                                exist = true;
                                break;
                            }
                        }

                        if (exist) {
                            SearchResponseDialog searchResponseDialog = new SearchResponseDialog("Product found!");
                            searchResponseDialog.show(requireFragmentManager(), "search response positive");
                        } else {
                            SearchResponseDialog searchResponseDialog = new SearchResponseDialog("Product not found!");
                            searchResponseDialog.show(requireFragmentManager(), "search response negative");
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SearchDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
