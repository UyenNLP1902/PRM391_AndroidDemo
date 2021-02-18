package uyennlp.demo.democrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createMenu(Menu menu) {
        menu.add(0, 0, 0, "View Database");
        menu.add(0, 1, 1, "Placeholder");
    }

    private boolean chooseFromMenu(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent(this, ViewActivity.class);
                startActivity(intent);
                return true;
            case 1:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        createMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return chooseFromMenu(item);
    }

    public void viewAllStudent(View view) {
        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);
    }
}