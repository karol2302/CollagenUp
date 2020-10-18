package com.example.collagenup;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagenup.Db.DbStrings;
import com.example.myapplication.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(getIntent().getStringExtra("com.example.myapplication.CUSTOMER"));
        ListView lv = (ListView) findViewById(R.id.ListV);

        ArrayList<String> values = new ArrayList<>();
        String sql = "select concat(date_format(e.start,'%Y-%m-%d %H.%i') ,' - ',p.name) termin from customer c join events e on e.customer = c.id join product p on p.id = e.product and p.name like 'Ico%' and e.deleted=0  where CONVERT(BINARY CONVERT(concat(lastname ,' ', firstname) USING latin2) USING utf8)  like '%" + getIntent().getStringExtra("com.example.myapplication.CUSTOMER") + "%'  order by start";

        String msg = "";
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
            stmt.execute("SET NAMES 'utf8' COLLATE 'utf8_general_ci';");
            stmt.execute("SET CHARACTER SET 'latin2';");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String termin = rs.getString("termin");
                values.add(termin);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException connError) {
            msg = "Wyjatek z jdbc " + connError.toString();
            connError.printStackTrace();
            ;
        } catch (ClassNotFoundException ex) {
            msg = "wyjatek z jdbc class " + ex.toString();
        } catch (IllegalAccessException ex) {
            msg = "illegal accessexc";
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);

    }

}

