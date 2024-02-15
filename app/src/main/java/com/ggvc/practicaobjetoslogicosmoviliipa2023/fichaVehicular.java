package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class fichaVehicular extends AppCompatActivity {

    public final static  String CHANNEL_ID="canal";
    Button notificaciones;

    String fecha;
    EditText tvFecha,cedula,propietario,numeroP,añoF,Marcas,color;
   // String nombre="jeferson",AnioFab="2023-11-20", marca="toyota",color="rojo",tipoV="camioneta",multas="no",placa="pxa-0303";
    //String cedula="1004656979";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_vehicular);

        tvFecha=findViewById(R.id.edt_anioFAv);
        cedula=findViewById(R.id.edt_Cedula);
        propietario=findViewById(R.id.edt_Propietario2);
        numeroP=findViewById(R.id.edt_Placa);
        añoF=findViewById(R.id.edt_anioFAv);
        Marcas=findViewById(R.id.edt_Marca);
        color=findViewById(R.id.edt_Color);
        notificaciones=findViewById(R.id.btn_notifica);

        notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                   generarNoticacionCanal();
               }else{
                   generarNoticacionSinCanal();
               }

            }
        });
    }


    public void AbrirCalendario(View view){
        Calendar cal= Calendar.getInstance();
        int anio= cal.get(Calendar.YEAR);
        int mes= cal.get(Calendar.MONTH);
        int dia= cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(fichaVehicular.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecha= dayOfMonth+"/"+(month+1)+"/"+year;
                tvFecha.setText(fecha);
            }
        },dia,mes,anio);
        dpd.show();
}



public  void CreateVheiculo(View V){
    BDHelper admin=new BDHelper(this,"registrar.db",null,1);
    SQLiteDatabase bd=admin.getWritableDatabase();
    String Cedula= cedula.getText().toString();
    String Nombre = propietario.getText().toString();
    String Placa= numeroP.getText().toString();
    String anioF= añoF.getText().toString();
    String Marca=Marcas.getText().toString();
    String Color=color.getText().toString();
   /* String Tvehiculo=tipoV;
    String Multas=multas;*/
    ContentValues datosReg = new ContentValues();
    datosReg.put("cedula",Cedula);
    datosReg.put("nombre",Nombre);
    datosReg.put("placa",Placa);
    datosReg.put("aniof",anioF);
    datosReg.put("marca",Marca);
    datosReg.put("color",Color);/*
    datosReg.put("tvehiculo",Tvehiculo);
    datosReg.put("multas",Multas);*/

   bd.insert("tbRegVehiculos",null,datosReg);
    Toast.makeText(fichaVehicular.this, "LA FICHA SE REGISTRO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generarNoticacionCanal(){
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        generarNoticacionSinCanal();
    }

    public void generarNoticacionSinCanal(){

        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_brightness_high_24)
                .setContentTitle("FICHA VEHICULAR")
                .setContentText("APLICACION PARA REGISTRAR UNA FICHA VEHICULAR")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("NOTIFICACION"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Mostrar la notificación
        notificationManager.notify(0, builder.build());





    }


}