package com.somethoughts.chinmay.game.GameHolder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.somethoughts.chinmay.game.Coin.CoinTossMainFragment;
import com.somethoughts.chinmay.game.Dice.DiceFragment;
import com.somethoughts.chinmay.game.Ropapors.RopaporsFragment;

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
public class gamePagerAdapter extends FragmentStatePagerAdapter {
    public gamePagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
        return new RopaporsFragment();
        else if (position == 1)
            return new DiceFragment();
        else
            return new CoinTossMainFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
