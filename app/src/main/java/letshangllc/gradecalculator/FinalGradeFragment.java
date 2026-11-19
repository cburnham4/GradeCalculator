package letshangllc.gradecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class FinalGradeFragment extends Fragment {

    private EditText etCurrentGrade, etFinalGradeWeight, etGradeWanted;
    private TextView tvFinalGrade;
    private Button btnCalculateFinal;

    public FinalGradeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_grade, container, false);
        findViews(view);
        setupViews();
        return view;
    }

    private void findViews(View view) {
        etCurrentGrade = view.findViewById(R.id.etCurrentGrade);
        etFinalGradeWeight = view.findViewById(R.id.etFinalGradeWeight);
        etGradeWanted = view.findViewById(R.id.etGradeWanted);

        tvFinalGrade = view.findViewById(R.id.tvFinalGrade);
        btnCalculateFinal = view.findViewById(R.id.btnCalculateFinal);
    }

    private void setupViews() {
        btnCalculateFinal.setOnClickListener(view -> calculateOnClick());
    }

    public void calculateOnClick() {
        String currentGradeString = etCurrentGrade.getText().toString().trim();
        String finalGradeWeightString = etFinalGradeWeight.getText().toString().trim();
        String gradeWantedString = etGradeWanted.getText().toString().trim();
        if (currentGradeString.isEmpty() || finalGradeWeightString.isEmpty()
                || gradeWantedString.isEmpty()) {
            Toast.makeText(requireContext(), "One or more required fields is empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double currentGrade = Double.parseDouble(currentGradeString) / 100.0;
            double finalWeight = Double.parseDouble(finalGradeWeightString) / 100.0;
            double gradeWanted = Double.parseDouble(gradeWantedString) / 100.0;

            double currentGradeTotal = (1 - finalWeight) * currentGrade;
            double neededGrade = ((currentGradeTotal - gradeWanted) / (finalWeight)) * -100;
            Toast.makeText(requireContext(),
                    String.format(Locale.getDefault(), "Grade: %.2f", neededGrade),
                    Toast.LENGTH_SHORT).show();
            tvFinalGrade.setText(String.format(Locale.getDefault(), "%.2f %%", neededGrade));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
