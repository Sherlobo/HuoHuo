package com.Huohuo.Huohuo.friend.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.DividerDecoration;
import com.Huohuo.Huohuo.DriverInfoActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.FriendContactWithHeadersAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.Driver;
import com.Huohuo.Huohuo.bean.Person;
import com.Huohuo.Huohuo.databinding.FragmentFriendContactBinding;
import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class FriendContactFragment extends BaseFragment<FragmentFriendContactBinding> implements OnQuickSideBarTouchListener {

    private LinkedList<Person> personList = new LinkedList<>();
    private HashMap<String, Integer> letters = new HashMap<>();
    private RecyclerView recyclerView;
    private QuickSideBarTipsView quickSideBarTipsView;
    private QuickSideBarView quickSideBarView;

    @Override
    public int setContent() {
        return R.layout.fragment_friend_contact;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initPerson();
        initView();
    }

    private void initPerson() {
        List<Driver> driverList = DataSupport.findAll(Driver.class);
        for (Driver driver : driverList) {
            personList.add(driver);
        }
    }

    private void initView() {
        recyclerView = bindingView.recyclerView;
        quickSideBarTipsView = bindingView.quickSideBarTipsView;
        quickSideBarView = bindingView.quickSideBarView;
        quickSideBarView.setOnQuickSideBarTouchListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        FriendContactWithHeadersAdapter adapter = new FriendContactWithHeadersAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FriendContactWithHeadersAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Person person) {
                Intent intent = new Intent(getActivity(), DriverInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("driver", person);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        ArrayList<String> customLetters = new ArrayList<>();
        int position = 0;
        for (Person person : personList) {
            String letter = person.getFirstLetter();
            if (!letters.containsKey(letter)) {
                letters.put(letter, position);
                customLetters.add(letter);
            }
            position ++;
        }
        quickSideBarView.setLetters(customLetters);
        adapter.addAll(personList);
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);
        recyclerView.addItemDecoration(new DividerDecoration(getActivity()));
    }

    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
        if(letters.containsKey(letter)) {
            recyclerView.scrollToPosition(letters.get(letter));
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {
        quickSideBarTipsView.setVisibility(touching? View.VISIBLE:View.INVISIBLE);
    }

}
