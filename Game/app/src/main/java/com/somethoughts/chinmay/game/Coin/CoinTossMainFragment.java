package com.somethoughts.chinmay.game.Coin;

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
public class CoinTossMainFragment extends Fragment {
    View view;
    Boolean inProgress;
    CountDownTimer countDownTimer;
    String[] toss = {"Heads", "Tails"};
    public CoinTossMainFragment() {
        inProgress = false;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.coin_main_layout, container, false);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/myfnt.ttf");

        TextView textViewResult = (TextView) view.findViewById(R.id.coin_result_textview);
        TextView textViewStatus = (TextView) view.findViewById(R.id.coin_status_textView);
        TextView textView1 = (TextView) view.findViewById(R.id.coin_head);

        textViewResult.setTypeface(typeface);
        textView1.setTypeface(typeface);
        textViewStatus.setTypeface(typeface);


        //Head button click listener
        Button headButton = (Button) view.findViewById(R.id.coin_button_heads);
        headButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewv) {
                toss_it(true);
            }
        });

        // Tails button on click listener
        Button tailsButton = (Button) view.findViewById(R.id.coin_button_tails);
        tailsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View viewv)
            {
                toss_it(false);
            }
        });


        return view;
    }

    private void toss_it(final Boolean userChoice) {

        final Toast toast = new Toast(getActivity());
        if(inProgress) {
            toast.makeText(getActivity(),"Please Wait",Toast.LENGTH_SHORT).show();
            return;
        }
        final TextView textViewResult = (TextView) view.findViewById(R.id.coin_result_textview);
        final TextView textViewStatus = (TextView) view.findViewById(R.id.coin_status_textView);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.coin_progressBar);

        Random random = new Random();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax(2500);
        progressBar.setIndeterminate(false);
        final Boolean b = random.nextBoolean();
        countDownTimer =
                new CountDownTimer(3000, 125) {
                    @Override
                    public void onTick(long l)
                    {
                        inProgress = true;
                        Random random = new Random();
                        progressBar.setProgress(3000 - (int) l);
                        Log.v("Progress", Integer.toString(progressBar.getProgress()));
                        textViewStatus.setVisibility(View.VISIBLE);
                        textViewStatus.setText(toss[random.nextInt(2)]);
                        textViewResult.setText("Waiting");
                        textViewResult.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    }

                    @Override
                    public void onFinish()
                    {
                        toast.cancel();
                        inProgress = false;
                        if((b && userChoice) || (!b && !userChoice))
                        {

                            textViewStatus.setText("Voila Voila");
                            textViewResult.setBackgroundColor(getResources().getColor(R.color.colorWin));
                            if(userChoice)
                                textViewResult.setText(toss[0]);
                            else
                                textViewResult.setText(toss[1]);
                        }
                        else
                        {
                            textViewStatus.setText("Oops :|");
                            textViewResult.setBackgroundColor(getResources().getColor(R.color.colorLose));
                            if(userChoice)
                                textViewResult.setText(toss[1]);
                            else
                                textViewResult.setText(toss[0]);
                        }
                        progressBar.setProgress(3000);
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