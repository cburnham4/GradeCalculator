package letshangllc.gradecalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class GradeCalculatorFragment extends Fragment {
    private String TAG = GradeCalculatorFragment.class.getSimpleName();

    /* Section number */
    int sections = 0;

    /* Views */
    private ViewGroup flexGroup;
    private TextView tvSectionNum, tvGrade;
    private EditText etGrade, etWeight;
    private Button addSection, btnCalculateGrade;

    public GradeCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_grade_calculator, container, false);

        flexGroup = (ViewGroup) view.findViewById(R.id.flexGrades);
//        addSection = (Button) view.findViewById(R.id.btnAddSection);
        btnCalculateGrade = (Button) view.findViewById(R.id.btnCalculateGrade);
        tvGrade = (TextView) view.findViewById(R.id.tvGrade);


        btnCalculateGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateGrade();
            }
        });
        //setListeners();

        sections = 0;
        for(int i = 0; i<7; i++){
            addGradeView();
        }
        return view;
    }

    private void addGradeView() {
        View gradeView = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_grade_item, flexGroup, false);

        sections++;
        etGrade = (EditText) gradeView.findViewById(R.id.etGrade);
        etWeight = (EditText) gradeView.findViewById(R.id.etWeight);
        //etWeight.addTextChangedListener(new GradeTextWatcher());
        tvSectionNum = (TextView) gradeView.findViewById(R.id.tvSection);

        tvSectionNum.setText(String.format(Locale.getDefault(), "%01d", sections));

        flexGroup.addView(gradeView);
    }

    private void calculateGrade(){
        double weightSum = 0;
        double gradeTotal = 0;
        for(int i  = 0; i<flexGroup.getChildCount(); i++){
            View view = flexGroup.getChildAt(i);
            EditText etGrade = (EditText) view.findViewById(R.id.etGrade);
            EditText etWeight = (EditText) view.findViewById(R.id.etWeight);
            if(!(etGrade.getText().toString().isEmpty() || etWeight.getText().toString().isEmpty())){
                double grade = Double.parseDouble(etGrade.getText().toString())/100.0;
                double weight = Double.parseDouble(etWeight.getText().toString())/100.0;
                weightSum += weight;
                gradeTotal += weight*grade;
            }

        }
        if(!(weightSum>0.99 && weightSum<1.01)){
            Toast.makeText(this.getContext(), "The weighted grades do not add to 100", Toast.LENGTH_SHORT).show();
        }else{
            tvGrade.setText(String.format(Locale.getDefault(), "GRADE: %.2f", gradeTotal*100));
        }
        Log.i(TAG, "GRADE: " +gradeTotal);
    }


    class GradeTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            calculateGrade();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "ON PAUSE");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, " ON RESUME");
    }
}
