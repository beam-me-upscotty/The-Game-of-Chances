package com.somethoughts.chinmay.game.Dice;

/**
 * Created by chinmay on 11/12/16.
 * Copyright 2016, Chinmay Relkar
 */

/**
 * This file is part of Game.

 Game is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the SomeThoughts, either version 3 of the License, or
 (at your option) any later version.

 Game is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Game.  If not, see <http://www.gnu.org/licenses/>.
 */
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.somethoughts.chinmay.game.R;

import java.util.Random;


public class DiceFragment extends Fragment {
    View view;
    Boolean inProgress;
    CountDownTimer countDownTimer;
    public DiceFragment() {
        inProgress = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dice_layout, container, false);
        Button button = (Button) view.findViewById(R.id.dice_start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewv) {
                spin();
            }
        }  );
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/myfnt.ttf");
        TextView textView = (TextView) view.findViewById(R.id.dice_result_textview);
        textView.setTypeface(custom_font);
        textView = (TextView) view.findViewById(R.id.dice_head);
        textView.setTypeface(custom_font);
        return view;
    }

    void spin()
    {
        if(inProgress) {
            Toast.makeText(getActivity(),"Please Wait",Toast.LENGTH_SHORT).show();
            return;
        }
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/myfnt.ttf");
        final TextView textView = (TextView) view.findViewById(R.id.dice_result_textview);
        final TextView textViewrun = (TextView) view.findViewById(R.id.result_textView_run);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.dice_progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setMax(3000);
        textView.setTypeface(custom_font);
        textViewrun.setTypeface(custom_font);
        progressBar.setVisibility(View.VISIBLE);

        countDownTimer =
                new CountDownTimer(3000,250)
                {
                    @Override
                    public void onTick(long l) {
                        inProgress = true;
                        Random random = new Random();

                        progressBar.setProgress(3000 - (int) l);

                        Log.v("Progress",Integer.toString(progressBar.getProgress()));
                        textViewrun.setVisibility(View.VISIBLE);
                        textViewrun.setText("Waiting");
                        textView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        textView.setText(Integer.toString(random.nextInt(6)+1));
                    }

                    @Override
                    public void onFinish() {
                        inProgress = false;
                        view.setBackgroundColor(getResources().getColor(R.color.colorWin));
                        textViewrun.setText("Here you go");
                        progressBar.setProgress(3000);
                        return;
                    }
                }.start();
            }

    @Override
    public void onPause() {
        super.onPause();
        if(countDownTimer != null)
            countDownTimer.cancel();
    }
}