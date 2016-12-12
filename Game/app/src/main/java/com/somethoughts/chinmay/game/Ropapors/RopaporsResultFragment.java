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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.somethoughts.chinmay.game.R;

/**
 * Created by chinmay on 2/12/16.
 */

public class RopaporsResultFragment extends Fragment {
    private View view;
    public RopaporsResultFragment() {
        // constructor definition
    }
@Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.ropapors_result_layout,container,false);
    Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/myfnt.ttf");
    TextView textView = (TextView) view.findViewById(R.id.result_textView);
    textView.setTypeface(custom_font);
    Bundle bundle = this.getArguments();
    int result = bundle.getInt("result");
    if(result == 1 )
    {
        textView.setText("Nice, You win");
        view.setBackgroundColor(getResources().getColor(R.color.colorWin));
    }
    else if ( result == 0)
    {
        textView.setText("Ohh Crap you lose");
        view.setBackgroundColor(getResources().getColor(R.color.colorLose));
    }
    else if ( result == 3)
    {
        textView.setText("Waiting");
        view.setBackgroundColor(getResources().getColor(R.color.colorWait));
    }
    else if ( result == 4)
    {
        textView.setText("Its A Tie");
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    return view;
    }
}
