package letshangllc.gradecalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


public class GPAFragment extends Fragment {
    private final static String TAG = GPAFragment.class.getSimpleName();

    /* Views */
    private TextView tvGPA;
    private EditText etHours1, etHours2, etHours3, etHours4, etHours5 ,etHours6, etHours7;
    private EditText[] etHours;
    private EditText etLetterGrade1, etLetterGrade2, etLetterGrade3, etLetterGrade4, etLetterGrade5,
            etLetterGrade6, etLetterGrade7;
    private EditText[] etLetterGrades;
    private Button btnCalculateGPA;

    public GPAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gpa, container, false);

        this.findViews(view);
        this.setupViews();
        return view;
    }

    private void findViews(View view){
        tvGPA = (TextView) view.findViewById(R.id.tvGPA);

        etHours1 = (EditText) view.findViewById(R.id.etHours1);
        etHours2 = (EditText) view.findViewById(R.id.etHours2);
        etHours3 = (EditText) view.findViewById(R.id.etHours3);
        etHours4 = (EditText) view.findViewById(R.id.etHours4);
        etHours5 = (EditText) view.findViewById(R.id.etHours5);
        etHours6 = (EditText) view.findViewById(R.id.etHours6);
        etHours7 = (EditText) view.findViewById(R.id.etHours7);

        etHours = new EditText[]{etHours1,etHours2,etHours3,etHours4,etHours5,etHours6,etHours7};

        etLetterGrade1 = (EditText) view.findViewById(R.id.etLetterGrade1);
        etLetterGrade2 = (EditText) view.findViewById(R.id.etLetterGrade2);
        etLetterGrade3 = (EditText) view.findViewById(R.id.etLetterGrade3);
        etLetterGrade4 = (EditText) view.findViewById(R.id.etLetterGrade4);
        etLetterGrade5 = (EditText) view.findViewById(R.id.etLetterGrade5);
        etLetterGrade6 = (EditText) view.findViewById(R.id.etLetterGrade6);
        etLetterGrade7 = (EditText) view.findViewById(R.id.etLetterGrade7);

        etLetterGrades = new EditText[]{etLetterGrade1, etLetterGrade2, etLetterGrade3, etLetterGrade4,
                etLetterGrade5, etLetterGrade6, etLetterGrade7};

        btnCalculateGPA = (Button) view.findViewById(R.id.btnCalculateGPA);
    }

    private void setupViews(){
        btnCalculateGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateGPA();
            }
        });
    }
    private void calculateGPA(){
        double totalHours= 0;
        double totalGPA = 0;
        for (int i = 0 ; i<etLetterGrades.length; i++){
            EditText etHour= etHours[i];
            EditText etLetterGrade = etLetterGrades[i];
            String hourString = etHour.getText().toString().trim();
            String letterGrade = etLetterGrade.getText().toString().trim().toUpperCase();
            if(!(hourString.isEmpty()|| letterGrade.isEmpty())){
                double hours  = Double.parseDouble(hourString);
                totalHours += hours;
                double gpa = getGPA(letterGrade);
                totalGPA += gpa*hours;
            }
        }

        double GPA = totalGPA/totalHours;
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
        }
        return 0;

    }
}
