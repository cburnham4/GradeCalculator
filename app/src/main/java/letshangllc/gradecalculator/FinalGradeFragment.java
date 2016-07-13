package letshangllc.gradecalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinalGradeFragment extends Fragment {
    private final static String TAG  = FinalGradeFragment.class.getSimpleName();

    /* VIEWS */
    private EditText etCurrentGrade, etFinalGradeWeight, etGradeWanted, etWhatIf;
    private TextView tvFinalGrade, tvOverallGrade;
    private Button btnCalculateFinal;


    public FinalGradeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_grade, container, false);

        this.findViews(view);
        this.setupViews();
        return view;
    }

    private void findViews(View view){
        etCurrentGrade = (EditText) view.findViewById(R.id.etCurrentGrade);
        etFinalGradeWeight = (EditText) view.findViewById(R.id.etFinalGradeWeight);
        etGradeWanted = (EditText) view.findViewById(R.id.etGradeWanted);
        etWhatIf = (EditText) view.findViewById(R.id.etWhatIf);

        tvFinalGrade = (TextView) view.findViewById(R.id.tvFinalGrade);
        tvOverallGrade = (TextView) view.findViewById(R.id.tvOverallGrade);
        btnCalculateFinal = (Button) view.findViewById(R.id.btnCalculateFinal);
    }

    private void setupViews(){
        btnCalculateFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateOnClick();
            }
        });
    }

    public void calculateOnClick(){
        String currentGradeString = etCurrentGrade.getText().toString().trim();
        String finalGradeWeightString = etFinalGradeWeight.getText().toString().trim();
        //String whatIfString = etWhatIf.getText().toString().trim();
        String gradeWantedString = etGradeWanted.getText().toString().trim();
        if(currentGradeString.isEmpty() || finalGradeWeightString.isEmpty()||
                (/*whatIfString.isEmpty() && */ gradeWantedString.isEmpty())){
            Toast.makeText(this.getContext(), "One or more required fields is empty", Toast.LENGTH_SHORT).show();
        }else {
            try {
                double currentGrade = Double.parseDouble(currentGradeString)/ 100.0;
                double finalWeight = Double.parseDouble(finalGradeWeightString) / 100.0;
//                if(!whatIfString.isEmpty()){
//                    double whatIf = Double.parseDouble(whatIfString);
//                }
                //if(!gradeWantedString.isEmpty()){
                    double gradeWanted = Double.parseDouble(gradeWantedString)/ 100.0;
                //}

                double currentGradeTotal = (1 - finalWeight) *currentGrade;
                double neededGrade= ((currentGradeTotal - gradeWanted)/ (finalWeight)) * -100;
                Toast.makeText(this.getContext(), String.format(Locale.getDefault(), "Grade: %.2f", neededGrade),
                        Toast.LENGTH_SHORT).show();
                tvFinalGrade.setText(String.format(Locale.getDefault(), "%.2f %%", neededGrade));
            }catch (Exception e){
                e.printStackTrace();
                return;
            }
        }
    }

}
