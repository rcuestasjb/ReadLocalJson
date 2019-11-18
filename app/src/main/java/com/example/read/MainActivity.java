package com.example.read;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.InputStream;

//1) Definicion de la clase evento; esta clase la utilizaremos para rellenar una vista
class Evento {
    private String id;
    private String categoria;
    private String titulo;
    private String descripcion;
    private String dia;
    private String hora;

    public Evento() {}

    public String getId() { return id; }
    public String getCategoria() { return categoria; }
    public String getTitulo() { return titulo; }
    public String getDecripcion() { return descripcion; }
    public String getDia() { return dia; }
    public String getHora() { return hora; }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1) Cargamos la actividad main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2) Leemos el fichero json, es este caso lo hacemos en local pero podria estar en una cadena
        //String json = "{\"titulo\": \"Mercado medieval\",\"categoria\": \"Mercado\",\"descripcion\": \"Productos de la tierra\",\"dia\": \"2019-11-15\",\"hora\": \"19:30\",\"id\": \"3\"}";
        String json ="";
        try {
            InputStream stream = getAssets().open("evento.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json  = new String(buffer);
        } catch (Exception e) { }

        //2) Instanciamos un objeo de la clase evento,(lo rellenamos!)
        Gson gson = new Gson();
        Evento evento = gson.fromJson(json, Evento.class);

        //3) Mostramos por pantalla los valores de los campos del objeto evento.
        Log.d("log",evento.getTitulo());
        EditText editText = (EditText)findViewById(R.id.editTextId);
        editText.setText(evento.getId());
        editText = (EditText)findViewById(R.id.editTextCategoria);
        editText.setText(evento.getCategoria());
        editText = (EditText)findViewById(R.id.editTextTitulo);
        editText.setText(evento.getTitulo());
        editText = findViewById(R.id.editTextDescripcion);
        editText.setText(evento.getDecripcion());
        editText = findViewById(R.id.editTextDia);
        editText.setText(evento.getDia());
        editText = findViewById(R.id.editTextHora);
        editText.setText(evento.getHora());
    }
}
