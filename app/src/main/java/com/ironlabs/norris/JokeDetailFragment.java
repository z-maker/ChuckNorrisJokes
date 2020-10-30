package com.ironlabs.norris;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ironlabs.chucknorrisapi.Model.Joke;


public class JokeDetailFragment extends Fragment {

    public static final String JOKE_ITEM = "joke_item";

    private Joke joke;

    public JokeDetailFragment() {
    }

    public static JokeDetailFragment newInstance(Joke joke){

        JokeDetailFragment fragment = new JokeDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(JOKE_ITEM,joke);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(JOKE_ITEM)) {

            joke = (Joke) getArguments().getSerializable(JOKE_ITEM);

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (joke != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(joke.getValue());
        }

        return rootView;
    }
}
