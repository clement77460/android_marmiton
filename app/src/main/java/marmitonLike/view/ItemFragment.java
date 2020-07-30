package marmitonLike.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import fr.clement.R;
import fr.clement.entities.Ingredient;
import marmitonLike.adapter.MyItemRecyclerViewAdapter;

public class ItemFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    /**
     * Model
     */
    private MyItemRecyclerViewAdapter adapter;

    public ItemFragment(MyItemRecyclerViewAdapter adapter ) {
        this.adapter=adapter;
    }

    public static ItemFragment newInstance(int columnCount,MyItemRecyclerViewAdapter adapter) {
        ItemFragment fragment = new ItemFragment(adapter);
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView rv = (RecyclerView) view;
            if (mColumnCount <= 1) {
                rv.setLayoutManager(new LinearLayoutManager(context));
            } else {
                rv.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            rv.setAdapter(this.adapter);

        }
        return view;
    }

    public void updateMe(){
        /*
        this.items.remove(0);
        this.adapter.setmValues(this.items);
        */
        this.adapter.notifyDataSetChanged();
    }
}