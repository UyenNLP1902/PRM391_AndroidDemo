package uyennlp.demo.democrud.daos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import uyennlp.demo.democrud.R;
import uyennlp.demo.democrud.dtos.StudentDTO;

public class StudentAdapter extends BaseAdapter {

    private List<StudentDTO> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);
        }
        TextView txtId = convertView.findViewById(R.id.txtId);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtAge = convertView.findViewById(R.id.txtAge);

        StudentDTO dto = list.get(position);
        txtId.setText(dto.getId() + "");
        txtName.setText(dto.getName());
        txtAge.setText(dto.getAge() + "");

        return convertView;
    }

    public List<StudentDTO> getList() {
        return list;
    }

    public void setList(List<StudentDTO> list) {
        this.list = list;
    }
}
