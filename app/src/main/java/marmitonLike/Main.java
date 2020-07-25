package marmitonLike;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.clement.entities.Ingredient;
import fr.clement.model.sqllite.IngredientDataSQLiteDAO;
import fr.clement.R;
import fr.clement.model.sqllite.SQLiteScore;

public class Main extends AppCompatActivity{
	private ItemFragment fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IngredientDataSQLiteDAO ingredientDataSQLDAO = new IngredientDataSQLiteDAO(new SQLiteScore(this));
		List<Ingredient> ingredients = ingredientDataSQLDAO.getAllMatchData();


		if(savedInstanceState==null){
			Log.d("test","loool");
			this.fragment=new ItemFragment(ingredients);
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.frame_layout_main, fragment)
					.commit();
		this.setListenerOnButton();
		}
		//this.fragment.updateMe();

	}
	private void setListenerOnButton(){
		Button button=findViewById(R.id.buttonTest);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				fragment.updateMe();
			}
		});
	}
}