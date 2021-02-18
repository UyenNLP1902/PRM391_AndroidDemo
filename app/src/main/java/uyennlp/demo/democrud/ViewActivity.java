package uyennlp.demo.democrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import uyennlp.demo.democrud.daos.StudentAdapter;
import uyennlp.demo.democrud.daos.StudentDAO;
import uyennlp.demo.democrud.dtos.StudentDTO;

public class ViewActivity extends AppCompatActivity {

    private ListView listViewStudent;
    private StudentAdapter adapter;
    private EditText edtSearchAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        List<StudentDTO> list = getListAllStudent();
        loadData(list);
    }

    public void goBackToMain(View view) {
        Intent intent = new Intent(ViewActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void addStudent(View view) {
        Toast.makeText(ViewActivity.this, "Not implemented", Toast.LENGTH_SHORT).show();
    }

    public void loadData(List<StudentDTO> list) {
        listViewStudent = findViewById(R.id.lstStudent);
        adapter = new StudentAdapter();

        if (list.isEmpty() || list == null) {
            Toast.makeText(ViewActivity.this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setList(list);

            listViewStudent.setAdapter(adapter);
            listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    StudentDTO dto = (StudentDTO) listViewStudent.getItemAtPosition(position);
                    Intent intent = new Intent(ViewActivity.this, DetailActivity.class);
                    intent.putExtra("dto", dto);

                    startActivity(intent);
                }
            });
        }
    }

    public void searchStudent(View view) {
        edtSearchAge = findViewById(R.id.edtSearchAge);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.getText().toString().equals("Search");

        int age = Integer.parseInt(edtSearchAge.getText().toString());

//        List<StudentDTO> list = getListSearchStudent(age);
//        loadData(list);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<StudentDTO> list = getListSearchStudent(age);
                if (!list.isEmpty() || list == null) {
                    loadData(list);
                    handler.removeCallbacks(this); //ngưng search
                } else {
                    Toast.makeText(ViewActivity.this, "Searching...", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(this, 5*1000); //search mỗi 5 giây
                }
            }
        };

        runnable.run();
    }

    public void viewAll(View view) {
        List<StudentDTO> list = getListAllStudent();
        loadData(list);
    }

    @Nullable
    private List<StudentDTO> getListAllStudent() {
        try {
            StudentDAO dao = new StudentDAO();
            return dao.getAllStudent();
        } catch (SQLException e) {
            Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    private List<StudentDTO> getListSearchStudent(int age) {
        try {
            StudentDAO dao = new StudentDAO();
            return dao.searchByAge(age);
        } catch (SQLException e) {
            Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return null;
    }
}