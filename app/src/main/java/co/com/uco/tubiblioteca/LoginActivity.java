package co.com.uco.tubiblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import co.com.uco.tubiblioteca.dao.UsuarioDao;
import co.com.uco.tubiblioteca.dto.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText user;
    EditText password;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btnLogin = findViewById(R.id.btnLogin);
        final Button btnRegister = findViewById(R.id.btnRegister);

        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        usuarioDao = new UsuarioDao(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = user.getText().toString();
                String pass = password.getText().toString();
                if(usuario.equals("") && pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Error: Campos vacíos", Toast.LENGTH_LONG).show();
                }
                else if(usuarioDao.login(usuario,pass) == 1){
                    Usuario ux = usuarioDao.getUsuario(usuario,pass);
                    Toast.makeText(LoginActivity.this, "Ingreso autorizado.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("id", ux.getId());
                    startActivityForResult(intent, 0);
                }
                else{
                    Toast.makeText(LoginActivity.this, "El usuario no existe.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }


}