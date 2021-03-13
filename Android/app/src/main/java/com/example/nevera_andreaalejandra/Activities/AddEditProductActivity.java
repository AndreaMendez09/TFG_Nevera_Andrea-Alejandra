package com.example.nevera_andreaalejandra.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nevera_andreaalejandra.Models.ProductoModelo;
import com.example.nevera_andreaalejandra.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class AddEditProductActivity extends AppCompatActivity {
    //El boton para añadir
    private FloatingActionButton add;

    //Creamos las variables necesarias para asociarlas con el XML
    private EditText calendario;
    private EditText nombre;
    private EditText precio;
    private EditText cantidad;
    private Spinner  tipo;

    //Creamos el DatePicker para seleccionar una fecha
    private DatePickerDialog SeleccionarFecha;

    //Para realizar la conexon con la firebase
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);

        //DB Firebase
        mDataBase = FirebaseDatabase.getInstance().getReference();

        //Vinculamos con el XML
        add = findViewById(R.id.Guardar);
        calendario = (EditText) findViewById(R.id.ECalendarioProducto);
        nombre = (EditText) findViewById(R.id.ENombreProducto);
        precio = (EditText) findViewById(R.id.EPrecioProducto);
        cantidad = (EditText) findViewById(R.id.ECantidadProducto);
        tipo = (Spinner) findViewById(R.id.ETipoProducto);
        
        //Creamos los métodos on click
        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCalendario();
            }
        });
        
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirProducto();
            }
        });
        
        

    }

    private void añadirProducto() {
        String productName = nombre.getText().toString().trim();
        //Date productFecha = calendario.getText().toString().trim();
        int productCantidad = Integer.parseInt(cantidad.getText().toString().trim());
        double productPrecio = Double.parseDouble(precio.getText().toString().trim());
        String productTipo = tipo.getSelectedItem().toString();

        //TODO La ubicación vamos a tener que pasarla por el intent, igual que si es añadir o editar
        ProductoModelo producto = new ProductoModelo(productName,productCantidad, productPrecio,"nevera", productTipo);
        mDataBase.child("Producto").push().setValue(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddEditProductActivity.this, "Producto Creado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddEditProductActivity.this, "Error al crear", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void mostrarCalendario() {
        //Toast.makeText(this, "Has pulsado para mostrar el calendario", Toast.LENGTH_SHORT).show();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        SeleccionarFecha = new DatePickerDialog(AddEditProductActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        },  year, month, day);
        SeleccionarFecha.show();

    }
}