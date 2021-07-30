package com.example.tutorials.carteraclientes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tutorials.carteraclientes.BaseDatos.DatosOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;




public class ActNuevoCliente extends AppCompatActivity {
    private EditText edtNombre;
    private EditText edtDireccion;
    private EditText edtEmail;
    private EditText edtPhone;
    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_nuevo_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtDireccion= (EditText) findViewById(R.id.edtDireccion);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_nuevo_cliente,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch (id){
            case R.id.action_ok:
                if(bCamposCorrectos()) {
                    try {
                        datosOpenHelper = new DatosOpenHelper(this);
                        conexion = datosOpenHelper.getWritableDatabase();
                        StringBuilder sql = new StringBuilder();
                        sql.append("INSERT INTO CLIENTE (NOMBRE, DIRECCION, EMAIL, TELEFONO) VALUES('");
                        sql.append(edtNombre.getText().toString().trim() + "', '");
                        sql.append(edtDireccion.getText().toString().trim() + "', '");
                        sql.append(edtEmail.getText().toString().trim() + "', '");
                        sql.append(edtPhone.getText().toString().trim() + "')");
                        conexion.execSQL(sql.toString());
                        conexion.close();
                        finish();
                    } catch (Exception ex) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                        dlg.setTitle("Aviso");
                        dlg.setMessage(ex.getMessage());
                        dlg.setNeutralButton("OK", null);
                        dlg.show();

                    }
                }
                else{
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("Existen campos vacios");
                    dlg.setNeutralButton("OK",null);
                    dlg.show();

                }

                //Toast.makeText(this,"Boton OK seleccionado",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_cancelar:
                //Toast.makeText(this,"Boton cancelar seleccionado",Toast.LENGTH_LONG).show();
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private boolean bCamposCorrectos(){
        boolean res = true;
        if(edtNombre.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        if(edtDireccion.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        if(edtEmail.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        if(edtPhone.getText().toString().trim().isEmpty()){
            edtNombre.requestFocus();
            res = false;
        }
        return res;
    }
}