package letshangllc.gradecalculator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private AdsHelper adsHelper;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable adRefreshRunnable = new Runnable() {
        @Override
        public void run() {
            if (adsHelper != null) {
                adsHelper.refreshAd();
            }
            handler.postDelayed(this, getResources().getInteger(R.integer.ad_refresh_rate));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applyWindowInsets();
        setupToolbar();

        ViewPager2 viewPager = findViewById(R.id.vp_main_activity);
        viewPager.setAdapter(new SectionsPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tl_main_activity);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Grades");
                    break;
                case 1:
                    tab.setText("Final Grade");
                    break;
                default:
                    tab.setText("GPA");
                    break;
            }
        }).attach();

        runAds();
    }

    private void applyWindowInsets() {
        View root = findViewById(R.id.root_main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
    }

    private void runAds() {
        adsHelper = new AdsHelper(
                getWindow().getDecorView(),
                getResources().getString(R.string.admob_banner_unit_id),
                this
        );
        adsHelper.setUpAds();
        handler.postDelayed(adRefreshRunnable, getResources().getInteger(R.integer.ad_refresh_rate));
    }

    @Override
    protected void onPause() {
        if (adsHelper != null) {
            adsHelper.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adsHelper != null) {
            adsHelper.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(adRefreshRunnable);
        if (adsHelper != null) {
            adsHelper.onDestroy();
        }
        super.onDestroy();
    }

    private static class SectionsPagerAdapter extends FragmentStateAdapter {
        SectionsPagerAdapter(@NonNull FragmentActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new GradeCalculatorFragment();
                case 1:
                    return new FinalGradeFragment();
                default:
                    return new GPAFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
