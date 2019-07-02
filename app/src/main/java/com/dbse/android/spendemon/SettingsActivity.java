package com.dbse.android.spendemon;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.dbse.android.spendemon.Summary.entries;


public class SettingsActivity extends AppCompatActivity {

//    public static final String IMG = "drawable-v24/team_logo_3.png";

    private static final int STORAGE_CODE = 1000;
    ArrayList<Float> iData = new ArrayList<>();
    ArrayList<Float> eData = new ArrayList<>();
    String currencySign;
    String currency;
    float balance;
    float expenseSum;
    float incomeSum;
    Button bDeleteAll;
    Button bDownloadReport;
    SharedPreferences sharedPreferences;
    private SummaryViewModel summaryViewModel;
    private int testNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class);
        bDeleteAll = findViewById(R.id.bDeleteAll);
        bDownloadReport = findViewById(R.id.download_report);

//        adding the warning button for delete all:
//        bDeleteAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Button","deleteAll clicked");
//                AlertDialog.Builder builder;
//                builder = new AlertDialog.Builder(getApplicationContext());
//                Log.i("builder: ", String.valueOf(builder));
//                builder.setMessage("Are you sure you want to reset all the data?")
//                        .setTitle("Reset All")
//                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Log.i("builder setMessage:","successful");
//                                summaryViewModel.deleteAllData();
//                            }
//                        })
//                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                        // CANCEL
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        bDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(R.string.del_msg)
                        .setTitle(R.string.confirm_dialog_title)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                summaryViewModel.deleteAllData();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            private Object PrintAttributes;

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Cancel
                                //PrintedPdfDocument doncumentPdf = new PrintedPdfDocument(getApplicationContext(), (android.print.PrintAttributes) PrintAttributes);


                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        bDownloadReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("clicked", "inside onClick");
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, STORAGE_CODE);
                        Log.i("clicked", "permission denied");
                    } else {
                        createPDF();
                    }
                } else {
                    createPDF();

                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                permission granted
            } else {
//                    permission denied
                Toast.makeText(this, "permission denied...!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void createPDF() {
        // DocumentFile doc = new DocumentFile();
        currency = sharedPreferences.getString("currency", "Euro");
        assert currency != null;
        switch (currency) {
            case "Euro":
                currencySign = "€";
                break;
            case "Dollars":
                currencySign = "$";
                break;
            case "Rupees":
                currencySign = "INR ";
                break;
            case "Yen":
                currencySign = "￥" /*"YEN "*/;
                break;
            case "Pound":
                currencySign = "£";
                break;
            case "Franc":
                currencySign = "CHf ";
                break;
            case "Canadian Dollar":
                currencySign = "CAD";
                break;
        }
        Document mDoc = new Document();
        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        mFileName = "SPENDEMON_" + mFileName;
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + "Download/" + mFileName + ".pdf";

        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getType().equals("Incomes")) {
                iData.add((float) entry.getAmount());
            } else if (entry.getType().equals("Expenses")) {
                eData.add((float) entry.getAmount());
            }
        }

        balance = 0;
        incomeSum = 0;
        expenseSum = 0;
        for (float index : iData) {
            balance += index;
            incomeSum += index;
        }
        for (float index : eData) {
            balance -= index;
            expenseSum += index;
        }
//        string should only have two numbers after the float
        String stringBalance = String.valueOf((float) ((int) (balance * 100)) / 100);
        if (balance > 0) {
            stringBalance = "+ " + currencySign + stringBalance;
        } else if (balance < 0) {
            stringBalance = "- " + currencySign + stringBalance.substring(1);
        }

        String stringIncomeSum = String.valueOf((float) ((int) (incomeSum * 100)) / 100);
        stringIncomeSum = "+ " + currencySign + stringIncomeSum;

        String stringExpenseSum = String.valueOf((float) ((int) (expenseSum * 100)) / 100);
        stringExpenseSum = "- " + currencySign + stringExpenseSum;

        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            mDoc.open();
            Drawable d = getResources().getDrawable(R.drawable.team_logo_3);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image logo = Image.getInstance(stream.toByteArray());
            logo.scaleAbsolute(100, 100);
            mDoc.add(logo);

            String mText = "Summary Report";
            mDoc.add(new Paragraph(mText));
            mText = "        ";
            mDoc.add(new Paragraph(mText));
            mText = "Balance:";
            mDoc.add(new Paragraph(mText));
            mText = stringBalance;
            mDoc.add(new Paragraph(mText));
            mText = "           ";
            mDoc.add(new Paragraph(mText));
            mText = "Sum of all the expenses: ";
            mDoc.add(new Paragraph(mText));
            mText = stringExpenseSum;
            mDoc.add(new Paragraph(mText));
            mText = "           ";
            mDoc.add(new Paragraph(mText));
            mText = "Sum of all the incomes: ";
            mDoc.add(new Paragraph(mText));
            mText = stringIncomeSum;
            mDoc.add(new Paragraph(mText));
            mText = "           ";
            mDoc.add(new Paragraph(mText));


            int iteration = 0;
//            for (com.dbse.android.spendemon.model.Entry entry : entries) {
//                iteration ++;
//                mText = "T_" + iteration + "   " + entry.getDate() + "   " + entry.getAmount() + " "
//                + entry.getType() + " " + entry.getCategory() + " " + entry.getPayMethod();
//                mDoc.add(new Paragraph(mText));
//
//            }
            PdfPTable table = new PdfPTable(6);

            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("#");
            table.addCell("Date");
            table.addCell("Amount");
            table.addCell("Type");
            table.addCell("Category");
            table.addCell("Payment Method");
            table.setHeaderRows(1);
            PdfPCell[] cells = table.getRow(0).getCells();
            for (int j = 0; j < cells.length; j++) {
                cells[j].setBackgroundColor(BaseColor.GRAY);
            }
            iteration = 0;
            int columnNumber = cells.length;
            PdfPCell nextCell;
            for (com.dbse.android.spendemon.model.Entry entry : entries) {
                Log.i("enter:", "entries");
                iteration++;
                if (entry.getType().equals("Incomes")) {

                    @SuppressLint("DefaultLocale") String iterationString = String.format("T_%04d", iteration);
                    nextCell = new PdfPCell(new Phrase(iterationString));
                    nextCell.setBackgroundColor(new BaseColor(208, 240, 192));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getDate()));
                    nextCell.setBackgroundColor(new BaseColor(208, 240, 192));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(String.format("%s%s", currencySign, entry.getAmount())));
                    nextCell.setBackgroundColor(new BaseColor(208, 240, 192));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getType()));
                    nextCell.setBackgroundColor(new BaseColor(208, 240, 192));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getCategory()));
                    nextCell.setBackgroundColor(new BaseColor(208, 240, 192));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getPayMethod()));
                    nextCell.setBackgroundColor(new BaseColor(208, 240, 192));
                    table.addCell(nextCell);


//
                } else if (entry.getType().equals("Expenses")) {
////
                    @SuppressLint("DefaultLocale") String iterationString = String.format("T_%04d", iteration);
                    nextCell = new PdfPCell(new Phrase(iterationString));
                    nextCell.setBackgroundColor(new BaseColor(255, 227, 235));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getDate()));
                    nextCell.setBackgroundColor(new BaseColor(255, 227, 235));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(currencySign + String.valueOf(entry.getAmount())));
                    nextCell.setBackgroundColor(new BaseColor(255, 227, 235));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getType()));
                    nextCell.setBackgroundColor(new BaseColor(255, 227, 235));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getCategory()));
                    nextCell.setBackgroundColor(new BaseColor(255, 227, 235));
                    table.addCell(nextCell);

                    nextCell = new PdfPCell(new Phrase(entry.getPayMethod()));
                    nextCell.setBackgroundColor(new BaseColor(255, 227, 235));
                    table.addCell(nextCell);
//
                }
            }


            Log.i("exit:", "entries");
            mDoc.add(table);
            mDoc.close();
            Log.i("path:", mFilePath);
            Toast.makeText(this, mFileName + ".pdf\nis saved to\n" + mFilePath, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}