package marmitonLike.controller;

import android.util.Log;
import android.view.View;

import fr.clement.entities.Ingredient;
import fr.clement.model.sqllite.IngredientDataSQLiteDAO;
import marmitonLike.adapter.MyItemRecyclerViewAdapter;

public class RecyclerViewButtonController implements View.OnClickListener {

    private Ingredient listenedIngredient;
    private MyItemRecyclerViewAdapter adapter;
    private IngredientDataSQLiteDAO ingredientDataSQLDAO;

    public RecyclerViewButtonController(MyItemRecyclerViewAdapter adapter, IngredientDataSQLiteDAO ingredientDataSQLDAO){
        this.adapter=adapter;
        this.ingredientDataSQLDAO=ingredientDataSQLDAO;
    }

    public void setModelData(Ingredient ingredient){
        this.listenedIngredient=ingredient;
    }

    @Override
    public void onClick(View view) {
        this.ingredientDataSQLDAO.deleteEntries(this.listenedIngredient.getLabel());
        this.adapter.refreshMValues();
        this.adapter.notifyDataSetChanged();
    }
}
