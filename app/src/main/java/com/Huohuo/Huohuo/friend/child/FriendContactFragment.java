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
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

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
    private FriendContactWithHeadersAdapter adapter;
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
    }

    private void initPerson() {
        AVQuery<AVObject> query = new AVQuery<>("Driver");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    personList.clear();
                    for (AVObject avObject : list) {
                        Driver driver = new Driver();
                        driver.setObjectId(avObject.getObjectId());
                        driver.setPhone(avObject.get("phone").toString());
                        driver.setRealName(avObject.get("realName").toString());
                        driver.setIdNumber(avObject.get("idNumber").toString());
                        driver.setTaskCount(Integer.parseInt(avObject.get("taskCount").toString()));
                        driver.setRating(Integer.parseInt(avObject.get("rating").toString()));
                        driver.setBriefIntroduce(avObject.get("briefIntroduce").toString());
                        personList.add(driver);
                    }
                    initView();
                }
            }
        });
    }

    private void initView() {
        recyclerView = bindingView.recyclerView;
        quickSideBarTipsView = bindingView.quickSideBarTipsView;
        quickSideBarView = bindingView.quickSideBarView;
        quickSideBarView.setOnQuickSideBarTouchListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FriendContactWithHeadersAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FriendContactWithHeadersAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Person person) {
                Intent intent = new Intent(getActivity(), DriverInfoActivity.class);
                Bundle bundle = new Bundle();
                Driver driver = (Driver) person;
                bundle.putSerializable("driver", driver);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        int position = 0;
        for (Person person : personList) {
            String letter = person.getFirstLetter();
            if (!letters.containsKey(letter)) {
                letters.put(letter, position);
            }
            position ++;
        }
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
