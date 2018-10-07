package com.story.hacker.modules.news.view.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.story.hacker.R;
import com.story.hacker.databinding.LayoutStoryItemBinding;
import com.story.hacker.modules.news.model.Story;

public class StoryAdapter extends PagedListAdapter<Story, RecyclerView.ViewHolder> {

    MutableLiveData<String> urlLiveData = new MutableLiveData<>();

    public StoryAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutStoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()), R.layout.layout_story_item, parent, false);
        return new StoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Story story = getItem(position);
        StoryViewHolder viewHolder = (StoryViewHolder) holder;
        if (story != null)
            viewHolder.getBinding().setViewModel(story);
    }

    @Override
    public void onCurrentListChanged(@Nullable PagedList<Story> currentList) {
        super.onCurrentListChanged(currentList);
        notifyDataSetChanged();
    }

    public MutableLiveData<String> getUrlLiveData() {
        return urlLiveData;
    }

    private static DiffUtil.ItemCallback<Story> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Story>() {
                @Override
                public boolean areItemsTheSame(Story oldStory, Story newStory) {
                    return oldStory.getId() == newStory.getId();
                }

                @Override
                public boolean areContentsTheSame(Story oldStory,
                                                  Story newStory) {
                    return oldStory.equals(newStory);
                }
            };

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        private LayoutStoryItemBinding binding;

        public StoryViewHolder(LayoutStoryItemBinding bind) {
            super(bind.getRoot());
            binding = bind;
            binding.title.setOnClickListener(v -> urlLiveData.postValue(getItem(getAdapterPosition()).getUrl()));
        }

        public LayoutStoryItemBinding getBinding() {
            return binding;
        }
    }
}
