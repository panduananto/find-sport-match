package com.example.findmatch.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.findmatch.R;

public class DialogJoinMatchFragment extends DialogFragment {

    public String sportMatchIdArg;
    private static final String TAG_DIALOGJOINMATCH = "TAG_DIALOGJOINMATCH";
    private EditText editTextInputTotalPlayer;
    private TextView textViewCancelJoin, textViewAcceptJoin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_join_match, container, false);

        editTextInputTotalPlayer = (EditText) view.findViewById(R.id.editText_inputTotalPlayer);

        Bundle bundle = getArguments();
        if (bundle != null) {
            sportMatchIdArg = bundle.getString("sportMatchIdString");
        }

        textViewCancelJoin = (TextView) view.findViewById(R.id.textView_buttonCancelJoin);
        textViewCancelJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        textViewAcceptJoin = (TextView) view.findViewById(R.id.textView_buttonAcceptJoin);
        textViewAcceptJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG_DIALOGJOINMATCH, "sportId: " + sportMatchIdArg);
            }
        });

        return view;
    }
}
