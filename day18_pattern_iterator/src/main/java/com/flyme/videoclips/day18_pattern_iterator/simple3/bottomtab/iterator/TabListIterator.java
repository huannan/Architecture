package com.flyme.videoclips.day18_pattern_iterator.simple3.bottomtab.iterator;

import com.flyme.videoclips.day18_pattern_iterator.simple3.bottomtab.BottomTabItem;

import java.util.ArrayList;
import java.util.List;

public class TabListIterator<T extends BottomTabItem> implements TabIterator {
    private List<T> mTabItems;
    private int index;

    public TabListIterator() {
        mTabItems = new ArrayList<>();
    }

    public void addItem(T item) {
        mTabItems.add(item);
    }

    @Override
    public BottomTabItem next() {
        return mTabItems.get(index++);
    }

    @Override
    public boolean hashNext() {
        return index < mTabItems.size();
    }
}
