package com.mortuza.digitalsquaremed.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.mortuza.digitalsquaremed.R;
import com.mortuza.digitalsquaremed.activity.SplashActivity;

import java.util.Calendar;

public class MRDialogFragment extends DialogFragment {
    TimePicker timePicker;
    Button button;
    public DialogListener dialogListener;
    private static final String TAG = "MRDialogFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dialogListener = (DialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timePicker = view.findViewById(R.id.timePicker);
        Log.d(TAG, "onViewCreated: "+ Calendar.MINUTE + "f"+Calendar.HOUR_OF_DAY);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            timePicker.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
            timePicker.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
        } else {
            timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(Calendar.getInstance().get(Calendar.MINUTE));
        }

        button = view.findViewById(R.id.setAlarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour;
                int min;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    min = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    min = timePicker.getCurrentMinute();
                }
                if (dialogListener != null) {
                    dialogListener.getBack(hour, min);
                }
                getDialog().dismiss();

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fragment_layout, container, false);
    }

    public interface DialogListener {
        void getBack(int hour, int min);
    }

    @Override
    public void onResume() {
        getDialog().getWindow().setLayout(((getWidth(getActivity()) / 100) * 80), LinearLayout.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();

    }

    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
