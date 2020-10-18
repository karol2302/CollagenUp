package com.example.collagenup;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collagenup.Db.DbStrings;
import com.example.collagenup.ObjectsAdapters.Obiekty;
import com.example.collagenup.Adapters.ItemAdapter;
import com.example.myapplication.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ItemAdapter itemAdapter;
    Context thisContext;
    ListView myListView;
    TextView progressTextView;
    ArrayList<Obiekty> ObjektyMap = new ArrayList<Obiekty>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        progressTextView = (TextView) findViewById(R.id.progressTextView);
        thisContext = this;
        progressTextView.setText("");


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData retrieveData = new GetData();
                retrieveData.execute("SELECT   concat(firstname ,' ', CONVERT(BINARY CONVERT(lastname USING latin2) USING utf8) ) as nazwisko, date_format(e.start,'%Y-%m-%d %H.%i') czas,  if(e.name ='' ,p.name, concat ( p.name, '-',e.name)) zabieg, concat(p.duration,'min') trwanie FROM customer c join events e on c.id = e.customer join product p on p.id = e.product WHERE e.start>NOW() and e.deleted=0 order by start asc limit 10");
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Main2Activity.class);
                startIntent.putExtra("com.example.myapplication.NAPIS", "pobieram dane ...");
                startActivity(startIntent);
            }
        });
    }

    private class GetData {

        String msg = "";


        protected void execute(String sql) {


            try {

                Connection conn = DbStrings.getConnection();
                Statement stmt = conn.createStatement();

                progressTextView.setText("Polaczono...");
                stmt.execute("SET NAMES 'utf8' COLLATE 'utf8_general_ci';");
                stmt.execute("SET CHARACTER SET 'latin2';");
                ResultSet rs = stmt.executeQuery(sql);
                ObjektyMap.clear();
                while (rs.next()) {
                    String nazwisko = rs.getString("nazwisko");
                    String czas = rs.getString("czas");
                    String zabieg = rs.getString("zabieg");
                    String trwanie = rs.getString("trwanie");
                    ObjektyMap.add(new Obiekty(nazwisko, czas, zabieg, trwanie));
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
                msg = ex.toString();
                ex.printStackTrace();
            }
            onPostExecute();

        }


        protected void onPostExecute() {
            progressTextView.setText(this.msg);

            if (ObjektyMap.size() > 0) {
                itemAdapter = new ItemAdapter(thisContext, ObjektyMap);
                myListView.setAdapter(itemAdapter);
            }
        }
    }
}
