package marmitonLike.controller;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import fr.clement.model.sqllite.IngredientDataSQLiteDAO;

public class ButtonController implements View.OnClickListener {

    private FragmentManager mainManager;
    private IngredientDataSQLiteDAO ingredientDataSQLDAO;

    public ButtonController(FragmentManager mainManager, IngredientDataSQLiteDAO ingredientDataSQLDAO){
        this.mainManager = mainManager;
        this.ingredientDataSQLDAO=ingredientDataSQLDAO;
    }

    @Override
    public void onClick(View view) {
        //Log.d("test",""+mainManager.getPrimaryNavigationFragment());
        Log.d("test","button");
    }
}
