package marmitonLike.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.clement.R;
import fr.clement.entities.Ingredient;
import fr.clement.model.sqllite.IngredientDataSQLiteDAO;
import marmitonLike.controller.RecyclerViewButtonController;

import java.util.List;

/**
 * Adapters in Android are a bridge between the Adapter View (e.g. ListView) and the underlying data for that view
 */

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    /**
     *  Model
     */
    private IngredientDataSQLiteDAO ingredientDataSQLDAO;
    private List<Ingredient> mValues;

    public MyItemRecyclerViewAdapter(IngredientDataSQLiteDAO ingredientDataSQLDAO) {
        this.ingredientDataSQLDAO = ingredientDataSQLDAO;
        this.mValues = this.ingredientDataSQLDAO.getAllMatchData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("test","onCreateViewHolder "+parent.getClass());
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent, false);
            return new ViewHolderItem(view, new RecyclerViewButtonController(this,this.ingredientDataSQLDAO));
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_header, parent, false);
            return new HeaderViewHolder(view);
        } else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("test","onBindViewHolder "+position);
        if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).updateTexts("testMe");

        }
        else if (holder instanceof ViewHolderItem){
            ((ViewHolderItem) holder).updateTexts(mValues.get(position-1),this.ingredientDataSQLDAO);

        }

    }

    public void refreshMValues(){
        this.mValues = this.ingredientDataSQLDAO.getAllMatchData();
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
            Log.d("test","ViewHolderHeader construct");
            this.txt_needsreview = (TextView) view.findViewById(R.id.textone);
        }
        public void updateTexts(String myText) {
            this.txt_needsreview.setText("List des ingrédients");
        }

    }

    public class ViewHolderItem extends RecyclerView.ViewHolder{
        private final View mView;
        private final TextView mIdView;
        private final TextView mContentView;

        private Ingredient mItem;
        private IngredientDataSQLiteDAO ingredientDataSQLDAO;
        private RecyclerViewButtonController buttonController;

        public ViewHolderItem(View view, RecyclerViewButtonController buttonController) {
            super(view);
            Log.d("test","classe : "+view.getClass());
            Log.d("test","ViewHolderItem construct");
            this.mView = view;
            this.buttonController=buttonController;
            this.mIdView = (TextView) view.findViewById(R.id.item_number);
            this.mContentView = (TextView) view.findViewById(R.id.content);
        }

        public void updateTexts(Ingredient mItem, IngredientDataSQLiteDAO ingredientDataSQLDAO){
            this.mItem = mItem;
            this.ingredientDataSQLDAO = ingredientDataSQLDAO;

            this.mIdView.setText(mItem.getLabel());
            this.mContentView.setText(mItem.getLabel());
            this.buttonController.setModelData(this.mItem);
            this.mView.findViewById(R.id.reviewButton).setOnClickListener(this.buttonController);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }
}