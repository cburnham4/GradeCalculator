package letshangllc.gradecalculator.objects;

/**
 * Created by Carl on 6/25/2016.
 */
public class GradeSection {
    int num;
    double gradePercent;
    double weightPercent;

    public GradeSection(int num, double gradePercent, double weightPercent) {
        this.num = num;
        this.gradePercent = gradePercent;
        this.weightPercent = weightPercent;
    }
}
