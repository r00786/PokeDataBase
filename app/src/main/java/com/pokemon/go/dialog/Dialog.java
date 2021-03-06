package com.pokemon.go.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.pokemon.go.R;
import com.pokemon.go.model.PokemonPlayers;

/**
 * Created by Lucifer on 11-01-2018.
 */

public class Dialog extends android.support.v4.app.DialogFragment {
    EditText etName;
    EditText etId;
    CheckBox status;
    Button btsb;
    private int query;
    private DialogCallbacks dialogCallbacks;
    private PokemonPlayers pokemonPlayers;

    @Override
    public void onStart() {
        super.onStart();

    }

    public interface DialogCallbacks {
        void add(PokemonPlayers pokemonPlayers);

        void delete(int index);

        void update(int index, PokemonPlayers pokemonPlayers);


    }

    public void setCallbacks(DialogCallbacks dialogCallbacks) {
        this.dialogCallbacks = dialogCallbacks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.layout_dialog_main, container, false);
        etName = viewRoot.findViewById(R.id.et_content);
        etId = viewRoot.findViewById(R.id.et_id);
        status = viewRoot.findViewById(R.id.status);
        btsb = viewRoot.findViewById(R.id.bt_sb);
        if (getArguments() != null) {
            query = getArguments().getInt("query");
        }
        pokemonPlayers = new PokemonPlayers();
        btsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (query == 0) {
                    if (dialogCallbacks != null) {
                        pokemonPlayers.setName(etName.getText().toString());
                        pokemonPlayers.setPlaysPokemonGo(status.isChecked());
                        pokemonPlayers.setId(etId.getText().toString());
                        dialogCallbacks.add(pokemonPlayers);
                    }
                } else if (query == 1) {
                    if (dialogCallbacks != null) {
                        dialogCallbacks.delete(Integer.valueOf(etId.getText().toString()));
                    }

                } else if (query == 2) {
                    if (dialogCallbacks != null) {
                        pokemonPlayers.setName(etName.getText().toString());
                        pokemonPlayers.setPlaysPokemonGo(status.isChecked());
                        pokemonPlayers.setId(etId.getText().toString());
                        dialogCallbacks.update(Integer.valueOf(etId.getText().toString()), pokemonPlayers);
                    }
                }
                dismiss();

            }
        });

        return viewRoot;
    }

    @Override
    public android.app.Dialog onCreateDialog(final Bundle savedInstanceState) {

        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // creating the fullscreen dialog
        final android.app.Dialog dialog = new android.app.Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        return dialog;
    }

}
