package com.example.a04.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a04.R;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.ArrayList;
import java.util.Arrays;

public class Descobrir_fragment extends Fragment implements CardStackListener {

    private CardStackView cardStackView;
    private CardStackLayoutManager layoutManager;
    private TextView textRatedCount;
    private int filmesAvaliados = 0;

    public Descobrir_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descobrir, container, false);
        ArrayList<Direction> directions = new ArrayList<>(
                Arrays.asList(Direction.Left, Direction.Right, Direction.Top)
        );


        cardStackView = view.findViewById(R.id.card_stack_view);
        textRatedCount = view.findViewById(R.id.text_rated_count);
        ImageButton button_dislike = view.findViewById(R.id.button_dislike);
        ImageButton button_favorite = view.findViewById(R.id.button_favorite);
        ImageButton button_like = view.findViewById(R.id.button_like);

        layoutManager = new CardStackLayoutManager(getContext(), this);
        layoutManager.setCanScrollHorizontal(true);
        layoutManager.setCanScrollVertical(true);
        layoutManager.setDirections(directions);
        cardStackView.setLayoutManager(layoutManager);

        button_dislike.setOnClickListener(v -> deslizarCard(Direction.Left));
        button_favorite.setOnClickListener(v -> deslizarCard(Direction.Top));
        button_like.setOnClickListener(v -> deslizarCard(Direction.Right));

        // Inflate the layout for this fragment
        return view;
    }

    private void deslizarCard(Direction direction) {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(direction)
                .setDuration(200)
                .build();
        layoutManager.setSwipeAnimationSetting(setting);
        cardStackView.swipe();
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        if (direction == Direction.Right) {
            // Lógica para quando curtir (Like)
        } else if (direction == Direction.Left) {
            // Lógica para quando não curtir (Dislike)
        } else if (direction == Direction.Top) {
            // Lógica para quando favoritar
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }
}