package letshangllc.gradecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class GPAFragment extends Fragment {

    private TextView tvGPA;
    private EditText[] etHours;
    private EditText[] etLetterGrades;
    private Button btnCalculateGPA;

    public GPAFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gpa, container, false);
        findViews(view);
        setupViews();
        return view;
    }

    private void findViews(View view) {
        tvGPA = view.findViewById(R.id.tvGPA);

        etHours = new EditText[]{
                view.findViewById(R.id.etHours1),
                view.findViewById(R.id.etHours2),
                view.findViewById(R.id.etHours3),
                view.findViewById(R.id.etHours4),
                view.findViewById(R.id.etHours5),
                view.findViewById(R.id.etHours6),
                view.findViewById(R.id.etHours7)
        };

        etLetterGrades = new EditText[]{
                view.findViewById(R.id.etLetterGrade1),
                view.findViewById(R.id.etLetterGrade2),
                view.findViewById(R.id.etLetterGrade3),
                view.findViewById(R.id.etLetterGrade4),
                view.findViewById(R.id.etLetterGrade5),
                view.findViewById(R.id.etLetterGrade6),
                view.findViewById(R.id.etLetterGrade7)
        };

        btnCalculateGPA = view.findViewById(R.id.btnCalculateGPA);
    }

    private void setupViews() {
        btnCalculateGPA.setOnClickListener(view -> calculateGPA());
    }

    private void calculateGPA() {
        double totalHours = 0;
        double totalGPA = 0;
        for (int i = 0; i < etLetterGrades.length; i++) {
            EditText etHour = etHours[i];
            EditText etLetterGrade = etLetterGrades[i];
            String hourString = etHour.getText().toString().trim();
            String letterGrade = etLetterGrade.getText().toString().trim().toUpperCase(Locale.US);
            if (!(hourString.isEmpty() || letterGrade.isEmpty())) {
                double hours = Double.parseDouble(hourString);
                totalHours += hours;
                double gpa = getGPA(letterGrade);
                totalGPA += gpa * hours;
            }
        }

        if (totalHours <= 0) {
            tvGPA.setText(getString(R.string.gpa));
            return;
        }

        double GPA = totalGPA / totalHours;
        tvGPA.setText(String.format(Locale.getDefault(), "GPA: %.2f", GPA));
    }

    private double getGPA(String letterGrade) {
        switch (letterGrade) {
            case "A+":
                return 4.3;
            case "A":
                return 4;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D":
                return 1.0;
            case "D-":
                return 0.7;
            case "F":
                return 0;
            default:
                return 0;
        }
    }
}
