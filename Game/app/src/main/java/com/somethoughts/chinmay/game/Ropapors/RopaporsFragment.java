package com.somethoughts.chinmay.game.Ropapors;

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
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.somethoughts.chinmay.game.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RopaporsFragment extends Fragment {
    View view;
    public RopaporsFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ropapors_main_layout, container, false);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/myfnt.ttf");
        // header
        TextView textView = (TextView) view.findViewById(R.id.Tag);
        textView.setText("Ro-Pap-Ors");
        textView.setTypeface(custom_font);
        // inflating placeholder fragment
        getFragmentManager().beginTransaction().replace(R.id.menu_frag,new placeHolderFragment()).commit();
        // inflating result fragment
        Fragment fragment = new RopaporsResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("result",2);              // 2 is for *start*
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.Result_fragment,fragment).commit();
        return view;
    }


    public static class placeHolderFragment extends Fragment {

        Boolean inProgress;
        View view;
        List<String> stringList ;
        CountDownTimer countDownTimer;
        public placeHolderFragment() {
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
                                 Bundle savedInstanceState)
        {
            view = inflater.inflate(R.layout.ropapors_dock_layout, container, false);
            Button StoneButton = (Button) view.findViewById(R.id.game_stone);
            StoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Fragment fragment = new RopaporsResultFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("result",3);                  // 3 is for wait
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Result_fragment,fragment).commit();
                    gamePLAY(0);
                }
            });
            Button PaperButton = (Button) view.findViewById(R.id.game_paper);
            PaperButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new RopaporsResultFragment();
                    Bundle bundle = new Bundle();               //3 is for wait
                    bundle.putInt("result",3);
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Result_fragment,fragment).commit();
                    gamePLAY(1);
                }
            });
            Button ScissorButton = (Button) view.findViewById(R.id.game_scissor);
            ScissorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new RopaporsResultFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("result",3);                  // 3  is for *wait*
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Result_fragment,fragment).commit();
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
            final ProgressBar progressBar =
                    (ProgressBar) view.findViewById(R.id.Ropapors_Result_progressBar);
            progressBar.setIndeterminate(false);
            progressBar.setMax(3000);
            progressBar.setVisibility(View.VISIBLE);
            final TextView textView = (TextView)
                    view.findViewById(R.id.compareTextView);
                textView.setVisibility(View.VISIBLE);
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

            TextView textView = (TextView)
                    view.findViewById(R.id.compareTextView);
            textView.setText( stringList.get(userChoice)+" vs "
                    +  stringList.get(computerChoice));
            textView.setTypeface(custom_font);
            if (userChoice == computerChoice) {
                Fragment fragment = new RopaporsResultFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("result",4);                  // 4 is for a tie
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.Result_fragment,fragment).commit();
            }else
            {
                if ((userChoice == 0 && computerChoice == 2)
                        || (userChoice == 1 && computerChoice == 0)
                        || (userChoice == 2 && computerChoice  == 1))
                {

                    Fragment fragment = new RopaporsResultFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("result",1);                  // 1 is for a win
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Result_fragment,fragment).commit();
                }
                else
                {
                    Fragment fragment = new RopaporsResultFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("result",0);                  // 0 is for lose
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Result_fragment,fragment).commit();
                }
            }
        }
        @Override
        public void onPause() {
            super.onPause();
            if(countDownTimer != null)
            countDownTimer.cancel();
        }
    }
}
