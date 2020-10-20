package com.example.collagenup;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagenup.Adapters.PersonListAdapterCooltech;
import com.example.collagenup.ObjectsAdapters.Person;
import com.example.collagenup.Db.DbStrings;
import com.example.myapplication.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Main4Activity extends AppCompatActivity {

    ListView messages_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        ListView mListView = (ListView) findViewById(R.id.listView);

        Main4Activity.GetPerson gp = new Main4Activity.GetPerson();
        ArrayList<Person> arr_persons = gp.execute("\n" +
                "select CONVERT(BINARY CONVERT(concat (c.lastname , ' ' ,c.firstname ) USING latin2) USING utf8) nazwisko, count(*) ilosc from customer c join events e on e.customer = c.id join product p on e.product = p.id WHERE e.deleted = 0 and e.start<NOW() and p.name like 'Cooltech%' group by concat(c.lastname, ' ' ,c.firstname) order by 1 asc");

        PersonListAdapterCooltech adapter = new PersonListAdapterCooltech(this, R.layout.adapter_view_layout, arr_persons);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
                String info = ((Person) parent.getItemAtPosition(position)).getNazwiskonazwisko();
                Toast.makeText(getBaseContext(), "Klient: " + info, Toast.LENGTH_SHORT).show();

                Intent startIntent = new Intent(getApplicationContext(), Main5Activity.class);
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
                    String icoone_ilosc = rs.getString("ilosc");
                    OsobyMap.add(new Person(nazwisko, icoone_ilosc));
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
