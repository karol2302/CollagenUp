package com.example.collagenup;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagenup.Adapters.PersonListAdapter;
import com.example.collagenup.ObjectsAdapters.Person;
import com.example.collagenup.Db.DbStrings;
import com.example.myapplication.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView messages_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        ListView mListView = (ListView) findViewById(R.id.listView);

        GetPerson gp = new GetPerson();
        ArrayList<Person> arr_persons = gp.execute("\n" +
                "select CONVERT(BINARY CONVERT(v.klientka USING latin2) USING utf8) nazwisko, concat(ilosc_icone_40 ,'/',ilosc_icone_50,'/',ilosc_icone_60,'/',ilosc_icone_90,'/',ilosc_icone_na_twarz) icone_40_50_60_90_twarz \n" +
                ",v.suma_wykonanych_zabiegow, date_format(pierwszy_zabieg_icoone,'%d-%m-%Y %H.%i') pierwszy_zabieg_icoone, date_format(pierwszy_zabieg_z_ostatniego_pakietu,'%d-%m-%Y %H.%i') pierwszy_zabieg_z_ostatniego_pakietu, ilosc_pakietow_icoone, gratis_icoone\n" +
                "from v_customer_icoone v");


        PersonListAdapter adapter = new PersonListAdapter(this, R.layout.adapter_view_layout, arr_persons);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
                String info = ((Person) parent.getItemAtPosition(position)).getNazwiskonazwisko();
                Toast.makeText(getBaseContext(), "Klient: " + info, Toast.LENGTH_SHORT).show();

                Intent startIntent = new Intent(getApplicationContext(), Main3Activity.class);
                startIntent.putExtra("com.example.myapplication.CUSTOMER", info);
                startActivity(startIntent);
            }
        });

    }


    private class GetPerson {

        String msg = "";
        ArrayList<Person> OsobyMap = new ArrayList<Person>();

        public ArrayList<Person> execute(String sql) {

            Connection conn = null;
            Statement stmt = null;

            try {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Class.forName(DbStrings.getJdbcDriver()).newInstance();
                conn = DriverManager.getConnection(
                        DbStrings.getUrl(),
                        DbStrings.getUser(),
                        DbStrings.getPass()
                );

                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                OsobyMap.clear();
                while (rs.next()) {
                    String nazwisko = rs.getString("nazwisko");
                    String icoone_ilosc = rs.getString("icone_40_50_60_90_twarz");
                    String icoone_suma = rs.getString("suma_wykonanych_zabiegow");
                    String pierwszy_icoone = rs.getString("pierwszy_zabieg_icoone");
                    String pierwszy_ost_pakiet = rs.getString("pierwszy_zabieg_z_ostatniego_pakietu");
                    String ilosc_pakietow = rs.getString("ilosc_pakietow_icoone");
                    String ilosc_gratisow = rs.getString("gratis_icoone");
                    OsobyMap.add(new Person(nazwisko, icoone_ilosc, icoone_suma, pierwszy_icoone, pierwszy_ost_pakiet, ilosc_pakietow, ilosc_gratisow));
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException connError) {
                msg = "Wyjatek z jdbc " + connError.toString();
                connError.printStackTrace();

            } catch (ClassNotFoundException ex) {
                msg = "wyjatek z jdbc class " + ex.toString();

            } catch (IllegalAccessException ex) {
                msg = "illegal accessexc";
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return OsobyMap;

        }

    }

}