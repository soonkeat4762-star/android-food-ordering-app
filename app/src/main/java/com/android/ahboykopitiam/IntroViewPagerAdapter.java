package com.android.ahboykopitiam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class IntroViewPagerAdapter extends RecyclerView.Adapter<IntroViewPagerAdapter.ScreenViewHolder> {

    Context mContext;
    List<ScreenItem> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public ScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_screen, parent, false);
        return new ScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScreenViewHolder holder, int position) {
        holder.title.setText(mListScreen.get(position).getTitle());
        holder.description.setText(mListScreen.get(position).getDescription());

        // Load and play the Lottie animation
        holder.animationView.setAnimation(mListScreen.get(position).getImage());
        holder.animationView.playAnimation();
    }

    @Override
    public int getItemCount() {
        return mListScreen.size();
    }

    public static class ScreenViewHolder extends RecyclerView.ViewHolder {
        LottieAnimationView animationView;
        TextView title;
        TextView description;

        public ScreenViewHolder(@NonNull View itemView) {
            super(itemView);

            animationView = itemView.findViewById(R.id.intro_img);
            title = itemView.findViewById(R.id.intro_title);
            description = itemView.findViewById(R.id.intro_description);
        }
    }
}
