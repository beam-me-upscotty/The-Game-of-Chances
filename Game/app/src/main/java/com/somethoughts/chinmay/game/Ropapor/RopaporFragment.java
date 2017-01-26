package com.somethoughts.chinmay.game.Ropapor;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.somethoughts.chinmay.game.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chinmay on 22/12/16.
 */

public class RopaporFragment extends Fragment {

    View view;
    Boolean inProgress;

    List<String> stringList ;
    CountDownTimer countDownTimer;
    Typeface custom_font;
    public RopaporFragment() {
        stringList= new ArrayList<>(3);
        stringList.clear();
        stringList.add("Stone");
        stringList.add("Paper");
        stringList.add("Scissor");
        inProgress = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ropapor_main_layout, container, false);

         custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/myfnt.ttf");

        // header
        TextView textView = (TextView) view.findViewById(R.id.Tag);
        textView.setText("Ro-Pap-Ors");
        textView.setTypeface(custom_font);

        // result text view
        final   TextView resultTextView = (TextView) view.findViewById(R.id.game_ropapor_result_textView);
        resultTextView.setTypeface(custom_font);
        //button click listeners
        ImageButton StoneButton = (ImageButton) view.findViewById(R.id.game_ropapor_stone);
        StoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resultTextView.setText("Waiting");
                gamePLAY(0);
            }
        });
        ImageButton PaperButton = (ImageButton) view.findViewById(R.id.game_ropapor_paper);
        PaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTextView.setText("Waiting");
                gamePLAY(1);
            }
        });
        ImageButton ScissorButton = (ImageButton) view.findViewById(R.id.game_ropapor_scissor);
        ScissorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTextView.setText("Waiting");
                gamePLAY(2);
            }
        });

        return view;
    }

    public void gamePLAY(final int choice) {
        final Toast toast = new Toast(getActivity());

        if(inProgress)
        {
            toast.makeText(getActivity(),"Please Wait",Toast.LENGTH_SHORT).show();
            return;
        }


        final Random random = new Random();
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.game_ropapor_progressBar);

                progressBar.setIndeterminate(false);
                progressBar.setMax(3000);
                progressBar.setVisibility(View.VISIBLE);

        final TextView textView = (TextView) view.findViewById(R.id.game_ropapor_compareTextView);

                textView.setVisibility(View.VISIBLE);
                textView.setTypeface(custom_font);
        countDownTimer = new CountDownTimer(3000,250)
        {
            @Override
            public void onTick(long l) {
                Random random = new Random();
                progressBar.setProgress(3000 - (int) l);
                textView.setText( stringList.get(choice)+" vs "
                        +  stringList.get(random.nextInt(3)));
                inProgress = true;
            }

            @Override
            public void onFinish() {
                toast.cancel();
                inProgress = false;
                progressBar.setProgress(3000);
                computeResult(choice, random.nextInt(3));
            }

        }.start();
    }


    private void computeResult(int userChoice, int computerChoice) {

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/myfnt.ttf");
        final   TextView resultTextView = (TextView) view.findViewById(R.id.game_ropapor_result_textView);

        TextView textView = (TextView)
                view.findViewById(R.id.game_ropapor_compareTextView);
        textView.setText( stringList.get(userChoice)+" vs "
                +  stringList.get(computerChoice));
        textView.setTypeface(custom_font);

        if (userChoice == computerChoice)
        {
            resultTextView.setText("Its a Tie");
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else
        {
            if ((userChoice == 0 && computerChoice == 2)
                    || (userChoice == 1 && computerChoice == 0)
                    || (userChoice == 2 && computerChoice  == 1))
            {
                resultTextView.setText("Congrats, you win");
                view.setBackgroundColor(getResources().getColor(R.color.colorWin));
            }
            else
            {
                resultTextView.setText("Oops...you lose");
                view.setBackgroundColor(getResources().getColor(R.color.colorLose));
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(inProgress)
        {
            countDownTimer.cancel();
        }
    }
}
