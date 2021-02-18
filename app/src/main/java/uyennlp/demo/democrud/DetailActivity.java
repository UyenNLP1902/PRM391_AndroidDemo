package uyennlp.demo.democrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import uyennlp.demo.democrud.daos.StudentDAO;
import uyennlp.demo.democrud.dtos.StudentDTO;

public class DetailActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);

        Intent intent = getIntent();
        StudentDTO dto = (StudentDTO) intent.getSerializableExtra("dto");

        edtAge.setText(dto.getAge() + "");
        edtName.setText(dto.getName());
        edtId.setText(dto.getId() + "");
    }

    public void goBackToView(View view) {
        Intent intent = new Intent(DetailActivity.this, ViewActivity.class);
        startActivity(intent);
    }

    public void updateStudent(View view) {
        edtId = findViewById(R.id.edtId);
        int id = Integer.parseInt(edtId.getText().toString());

        edtName = findViewById(R.id.edtName);
        String name = edtName.getText().toString();

        edtAge = findViewById(R.id.edtAge);
        int age = Integer.parseInt(edtAge.getText().toString());

        try{
            StudentDAO dao = new StudentDAO();
            boolean check = dao.updateStudent(new StudentDTO(id, name, age));

            if (Boolean.TRUE.equals(check)) {
                Toast.makeText(DetailActivity.this, "Update successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(DetailActivity.this, "Update failed!", Toast.LENGTH_LONG).show();
            }

        } catch (SQLException throwables) {
            Toast.makeText(DetailActivity.this, "SQLException", Toast.LENGTH_SHORT).show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(DetailActivity.this, "ClassNotFoundException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void deleteStudent(View view) {
        edtId = findViewById(R.id.edtId);
        int id = Integer.parseInt(edtId.getText().toString());

        try{
            StudentDAO dao = new StudentDAO();
            boolean check = dao.deleteStudent(id);

            if (Boolean.TRUE.equals(check)) {
                Toast.makeText(DetailActivity.this, "Delete successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(DetailActivity.this, "Delete failed!", Toast.LENGTH_LONG).show();
            }

        } catch (SQLException throwables) {
            Toast.makeText(DetailActivity.this, "SQLException", Toast.LENGTH_SHORT).show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(DetailActivity.this, "ClassNotFoundException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        goBackToView(view);
    }
}