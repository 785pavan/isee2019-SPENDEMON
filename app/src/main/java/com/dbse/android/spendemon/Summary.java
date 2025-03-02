package com.dbse.android.spendemon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dbse.android.spendemon.model.Entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Summary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "myTag";
    static ArrayList<Entry> entries = new ArrayList<>();
    private final String TYPE = "type";
    RecyclerView rvEntries;
    private SummaryViewModel summaryViewModel; // object of View Model created.
    private DrawerLayout drawer1;
    private int backKey = 0;
    private Intent intent;
    private entryAdaptor entryAdaptor = new entryAdaptor(entries);


    //    ArrayList<Float> iData = new ArrayList<>();
//    ArrayList<Float> eData = new ArrayList<>();
//    float balance;

    public static int compareDate(String a, String b) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = simpleDateFormat.parse(a);
            Date date2 = simpleDateFormat.parse(b);
            if (date1.after(date2)) {
                return 1;
            } else if (date1.before(date2)) {
                return -1;
            } else return 0;

        } catch (ParseException e) {              // Insert this block.
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class); //reference to current
        intent = getIntent();
        // activity given to view model object.
        summaryViewModel.getTable().observe(this, new Observer<List<Table>>() {
            List<Entry> filtered = new ArrayList<>();

            @Override
            public void onChanged(List<Table> tables) {
                entries.clear();
                for (Table table : tables) {
                    Entry e = new Entry(table.getType(), table.getCategory(), table.getAmount(), table.getDate(), table.getPaymethod(),
                            table.getNote());
                    e.setId(table.getId());
                    entries.add(e);
                    if (intent.hasExtra("Types")) {
                        String[] types = intent.getStringArrayExtra("Types");
                        for (String type : types) {
                            for (int i = 0; i < entries.size(); i++) {
                                if (entries.get(i).getType().equals(type)) {
                                    filtered.add(entries.get(i));
                                }
                            }
                        }
                        entries.clear();
                        entries.addAll(filtered);
                        filtered.clear();
                    }
                    if (intent.hasExtra("PayMethod")) {
                        String[] payMethods = intent.getStringArrayExtra("PayMethod");
                        for (String pay : payMethods) {
                            for (Entry entry : entries) {
                                if (entry.getPayMethod().equals(pay)) {
                                    filtered.add(entry);
                                }
                            }
                        }
                        entries.clear();
                        entries.addAll(filtered);
                        filtered.clear();
                    }
                    if (intent.hasExtra("Categories")) {
                        String[] cats = intent.getStringArrayExtra("Categories");
                        for (String cat : cats) {
                            for (Entry entry : entries) {
                                if (entry.getCategory().equals(cat)) {
                                    filtered.add(entry);
                                }
                            }
                        }
                        entries.clear();
                        entries.addAll(filtered);
                        filtered.clear();
                    }

                    if (intent.hasExtra("StartDate") && intent.hasExtra("EndDate")) {
                        String start = intent.getStringExtra("StartDate");
                        String end = intent.getStringExtra("EndDate");
                        for (Entry entry : entries) {
                            {
                                if (((compareDate(start, entry.getDate()) == -1) || (compareDate(start, entry.getDate()) == 0))
                                        && ((compareDate(end, entry.getDate()) == 1) || (compareDate(end, entry.getDate()) == 0)))
                                    filtered.add(entry);
                            }
                        }
                        entries.clear();
                        entries.addAll(filtered);
                        filtered.clear();
                    }
                }

                rvEntries = findViewById(R.id.rvEntries);
                entryAdaptor adaptor = new entryAdaptor(entries);
                rvEntries.setAdapter(adaptor);
                rvEntries.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        adding the navigation bar code:

        drawer1 = findViewById(R.id.drawer_layout_sum);


        NavigationView navigationView1 = findViewById(R.id.navigation_view1_Sum);

        NavigationView navigationView2 = findViewById(R.id.navigation_view2_Sum);

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawer1, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer1.addDrawerListener(toggle1);
        toggle1.syncState();


        RecyclerView rvEntries = findViewById(R.id.rvEntries);
        //Button bDelete = findViewById(R.id.bDelete);

        entryAdaptor adaptor = new entryAdaptor(entries);
        rvEntries.setAdapter(adaptor);
        rvEntries.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabPlus = findViewById(R.id.fabPlus);
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra(TYPE, "Incomes");
                startActivity(intent);
            }
        });
        FloatingActionButton fabMinus = findViewById(R.id.fabMinus);
        fabMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "minus", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra(TYPE, "Expenses");
                startActivity(intent);

            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage(R.string.del_msg)
                        .setTitle(R.string.confirm_dialog_title)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                summaryViewModel.delete(summaryViewModel.getTableById(entryAdaptor.getEntryAt(viewHolder.getAdapterPosition()).getId()));
                                Toast.makeText(getApplicationContext(), "Entry Deleted", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                            private Object PrintAttributes;

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Cancel
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();*/
                summaryViewModel.delete(summaryViewModel.getTableById(entryAdaptor.getEntryAt(viewHolder.getAdapterPosition()).getId()));
                Toast.makeText(getApplicationContext(), "Entry Deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(rvEntries);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter) {
            final Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {

        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawer(GravityCompat.START);
        } else if (drawer1.isDrawerOpen(GravityCompat.END)) {
            drawer1.closeDrawer(GravityCompat.END);
        } else {
            backKey++;
            if (backKey == 1) {
                Toast.makeText(Summary.this, "Click one more time to exit app", Toast.LENGTH_SHORT).show();
//                super.onBackPressed();
            } else {
                //exit app to home screen
                Intent homeScreenIntent = new Intent(Intent.ACTION_MAIN);
                homeScreenIntent.addCategory(Intent.CATEGORY_HOME);
                homeScreenIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeScreenIntent);
            }
//            super.onBackPressed();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_daily:
                Intent intent_daily = new Intent(getApplicationContext(), PieChartDailyActivity.class);
                startActivity(intent_daily);
                break;
            case R.id.nav_balance:
                Intent intent_balance = new Intent(getApplicationContext(), BalanceActivity.class);
                startActivity(intent_balance);
                break;
            case R.id.nav_monthly:
                Intent intent_month = new Intent(getApplicationContext(), ChartMonthActivity.class);
                startActivity(intent_month);
                break;
            case R.id.nav_total:
                Intent intent_total = new Intent(getApplicationContext(), PieChartActivity.class);
                intent_total.putExtra("Duration", "All");
                startActivity(intent_total);
                break;
            case R.id.nav_trendLine:
                Intent intent_trendLine = new Intent(getApplicationContext(), TrendLineActivity.class);
                startActivity(intent_trendLine);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();

                Intent intent_share = new Intent(Intent.ACTION_SEND);
                intent_share.setType("text/plain");
                String shareBody = "We are team SPENDEMON. " +
                        "\n Please check out our App at:" +
                        "\n https://github.com/DBSE-teaching/isee2019-SPENDEMON/releases/download/V0.1.2/Spendemon.apk";
//                String shareSubject = "The Link to SPENDEMON App:";
//                intent_share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                intent_share.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent_share, "Share this by"));
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Contact Us E-mail", Toast.LENGTH_LONG).show();
                Intent intent_contactUs = new Intent(Intent.ACTION_SEND);
                intent_contactUs.setType("message/rfc822");
                String contactBody = "Sent from SPENDEMON app.";
                String contactSubject = "Feedback for SPENDEMON team";
                intent_contactUs.putExtra(Intent.EXTRA_SUBJECT, contactSubject);
                intent_contactUs.putExtra(Intent.EXTRA_TEXT, contactBody);
                intent_contactUs.putExtra(Intent.EXTRA_EMAIL, new String[]{"pavan.kandapagari@st.ovgu.de",
                        "atrayee.neog@st.ovgu.de", "seyedbehnam.beladi@st.ovgu.de"});
                startActivity(Intent.createChooser(intent_contactUs, "Share this by"));
                break;
            case R.id.nav_summary:
                Intent intent = new Intent(getApplicationContext(), Summary.class);
                startActivity(intent);

//                right drawer
                break;
            case R.id.nav_currency:
                Toast.makeText(this, "Feature Not Available in the free version", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_Calculator:
                Toast.makeText(this, "Feature Not Available in the free version", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_settings:
                Intent intent_set = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent_set);
                break;
            case R.id.nav_aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_LONG).show();
                Intent intent_aboutUs = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dbse-teaching.github.io/isee2019-SPENDEMON"));
                startActivity(intent_aboutUs);
                break;
        }

        drawer1.closeDrawer(GravityCompat.START);


        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}