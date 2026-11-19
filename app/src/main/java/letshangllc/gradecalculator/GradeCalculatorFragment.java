package letshangllc.gradecalculator;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class GradeCalculatorFragment extends Fragment {
    private static final String TAG = GradeCalculatorFragment.class.getSimpleName();

    private int sections = 0;

    private ViewGroup flexGroup;
    private TextView tvGrade;
    private Button btnCalculateGrade;

    public GradeCalculatorFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade_calculator, container, false);

        flexGroup = view.findViewById(R.id.flexGrades);
        btnCalculateGrade = view.findViewById(R.id.btnCalculateGrade);
        tvGrade = view.findViewById(R.id.tvGrade);

        btnCalculateGrade.setOnClickListener(v -> calculateGrade());

        sections = 0;
        for (int i = 0; i < 7; i++) {
            addGradeView();
        }
        return view;
    }

    private void addGradeView() {
        View gradeView = LayoutInflater.from(requireContext())
                .inflate(R.layout.layout_grade_item, flexGroup, false);

        sections++;
        TextView tvSectionNum = gradeView.findViewById(R.id.tvSection);
        tvSectionNum.setText(String.format(Locale.getDefault(), "%01d", sections));

        flexGroup.addView(gradeView);
    }

    private void calculateGrade() {
        double weightSum = 0;
        double gradeTotal = 0;
        for (int i = 0; i < flexGroup.getChildCount(); i++) {
            View view = flexGroup.getChildAt(i);
            EditText etGrade = view.findViewById(R.id.etGrade);
            EditText etWeight = view.findViewById(R.id.etWeight);
            if (!(etGrade.getText().toString().isEmpty() || etWeight.getText().toString().isEmpty())) {
                double grade = Double.parseDouble(etGrade.getText().toString()) / 100.0;
                double weight = Double.parseDouble(etWeight.getText().toString()) / 100.0;
                weightSum += weight;
                gradeTotal += weight * grade;
            }
        }
        if (!(weightSum > 0.99 && weightSum < 1.01)) {
            Toast.makeText(requireContext(), "The weighted grades do not add to 100",
                    Toast.LENGTH_SHORT).show();
        } else {
            tvGrade.setText(String.format(Locale.getDefault(), "GRADE: %.2f", gradeTotal * 100));
        }
        Log.i(TAG, "GRADE: " + gradeTotal);
    }

    /** Kept for potential live-recalculation wiring; unused by default. */
    @SuppressWarnings("unused")
    class GradeTextWatcher implements TextWatcher {
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
}
