package marmitonLike.controller;

import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.clement.R;
import fr.clement.model.sqllite.IngredientDataSQLiteDAO;
import marmitonLike.adapter.MyItemRecyclerViewAdapter;
import marmitonLike.view.BlankFragment;
import marmitonLike.view.ItemFragment;
import marmitonLike.Main;

public class BottomViewController implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Main mainActivity;

    /**
     * Model
     */
    private IngredientDataSQLiteDAO ingredientDataSQLDAO;

    /**
     * Vue
     * Fragment
     */

   public BottomViewController(Main mainActivity,IngredientDataSQLiteDAO ingredientDataSQLDAO){
        this.mainActivity=mainActivity;
        this.ingredientDataSQLDAO=ingredientDataSQLDAO;
   }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.bv_macro:
                Toast.makeText(this.mainActivity.getBaseContext(), "Tracking macro", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bv_recette:
                fragment=new BlankFragment();
                this.mainActivity.setFragment(fragment);
                Toast.makeText(this.mainActivity.getBaseContext(), "Liste recettes", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.bv_ingredient:
                MyItemRecyclerViewAdapter mrv = new MyItemRecyclerViewAdapter(this.ingredientDataSQLDAO);
                fragment=new ItemFragment(mrv);
                this.mainActivity.setFragment(fragment);
                Toast.makeText(this.mainActivity.getBaseContext(), "Liste Ingrédients", Toast.LENGTH_SHORT).show();

                return true;
        }
        return false;
    }

}
