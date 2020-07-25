package marmitonLike;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import fr.clement.R;
import fr.clement.entities.Ingredient;

public class ItemFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private RecyclerView rv;
    private List<Ingredient> items;
    private MyItemRecyclerViewAdapter adapter;

    public ItemFragment(List<Ingredient> ingredients) {
        this.items=ingredients;
    }

    public static ItemFragment newInstance(Ingredient[] ingredients, int columnCount) {
        ItemFragment fragment = new ItemFragment(null);
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
            Log.d("test","ici");
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            this.adapter = new MyItemRecyclerViewAdapter(this.items);
            recyclerView.setAdapter(this.adapter);
        }
        return view;
    }

    public void updateMe(){
        this.items.remove(0);
        this.adapter.setmValues(this.items);
        this.adapter.notifyDataSetChanged();
    }
}