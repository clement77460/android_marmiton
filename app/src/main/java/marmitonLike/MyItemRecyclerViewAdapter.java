package marmitonLike;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.clement.R;
import fr.clement.entities.Ingredient;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<Ingredient> mValues;

    public MyItemRecyclerViewAdapter(List<Ingredient> items) {
        Log.d("test","constructeur");
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("test","view type :"+viewType);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("test","Bind view holder :"+position);
        holder.updateTexts(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setmValues(List<Ingredient> mValues) {
        this.mValues = mValues;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Ingredient mItem;

        public ViewHolder(View view) {
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