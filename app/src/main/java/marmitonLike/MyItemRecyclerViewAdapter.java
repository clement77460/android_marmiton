package marmitonLike;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.clement.R;
import fr.clement.entities.Ingredient;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<Ingredient> mValues;

    public MyItemRecyclerViewAdapter(List<Ingredient> items) {
        Log.d("test","constructeur");
        mValues = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent, false);
            return new ViewHolderItem(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_header, parent, false);
            return new HeaderViewHolder(view);
        } else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).updateTexts("testMe");

        }
        else if (holder instanceof ViewHolderItem){
            ((ViewHolderItem) holder).updateTexts(mValues.get(position-1));

        }

    }

    @Override
    public int getItemCount() {
        return mValues.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }



    public void setmValues(List<Ingredient> mValues) {
        this.mValues = mValues;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public final TextView txt_needsreview;

        public HeaderViewHolder(View view) {
            super(view);
            this.txt_needsreview = (TextView) view.findViewById(R.id.textone);
        }
        public void updateTexts(String myText) {
            this.txt_needsreview.setText("List des ingrédients");
        }
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Ingredient mItem;

        public ViewHolderItem(View view) {
            super(view);
            mView = view;
            this.mIdView = (TextView) view.findViewById(R.id.item_number);
            this.mContentView = (TextView) view.findViewById(R.id.content);
        }

        public void updateTexts(Ingredient mItem){
            this.mIdView.setText(mItem.getLabel());
            this.mContentView.setText(mItem.getLabel());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}