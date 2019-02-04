package bluma.example.com.reversi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityMainMenu extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent intent = new Intent(this, ActivityMatch.class);
        startActivity(intent);

    }
}
