package marmitonLike;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fr.clement.entities.Ingredient;
import fr.clement.model.sqllite.IngredientDataSQLiteDAO;
import fr.clement.R;
import fr.clement.model.sqllite.SQLiteScore;
import marmitonLike.controller.BottomViewController;
import marmitonLike.view.BlankFragment;
import marmitonLike.view.ItemFragment;

public class Main extends AppCompatActivity{
	private Fragment fragment;
	private BottomNavigationView bv;
	private List<Ingredient> ingredients;

	/**
	 * Declaring Models
	 */
	private IngredientDataSQLiteDAO ingredientDataSQLDAO;


	/**
	 * Declaring Controlers
	 *
	 */
	private BottomViewController bottomViewController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.ingredientDataSQLDAO = new IngredientDataSQLiteDAO(new SQLiteScore(this));
		this.ingredientDataSQLDAO.insertScore();

		if(savedInstanceState==null){
			this.fragment=new BlankFragment();
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.frame_layout_main, fragment)
					.commit();

		}

		this.initControlers();

		this.setListenerOnBottomView();
		//this.setListenerOnButton();

	}
	private void initControlers(){
		this.bottomViewController=new BottomViewController(this, this.ingredientDataSQLDAO);
	}
	/*
	private void setListenerOnButton() {
		Button button = findViewById(R.id.buttonTest);
		button.setOnClickListener(new ButtonController(this.getSupportFragmentManager()));

	}

	 */
	private void setListenerOnBottomView() {
		this.bv = findViewById(R.id.bottom_nav);
		this.bv.setOnNavigationItemSelectedListener(this.bottomViewController);
	}
	public void setFragment(Fragment fragment){

		//solution sale, a revoir !
		if(fragment instanceof ItemFragment) {
			findViewById(R.id.buttonTest).setVisibility(View.VISIBLE);
		}else{
			findViewById(R.id.buttonTest).setVisibility(View.INVISIBLE);
		}

		this.fragment=fragment;
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.frame_layout_main, this.fragment)
				.commit();
	}
}