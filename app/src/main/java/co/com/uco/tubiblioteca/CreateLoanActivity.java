package co.com.uco.tubiblioteca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.com.uco.tubiblioteca.dao.BookDao;
import co.com.uco.tubiblioteca.dto.Book;
import co.com.uco.tubiblioteca.utilidad.ActionBarUtil;

public class CreateLoanActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    EditText nombres;
    EditText identificacion;
    EditText celular;
    EditText libro;
    EditText inputDate;
    Button btnCreateLoan;
    BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_loan);
        initComponents();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nombres = (EditText) findViewById(R.id.inputNombre);
        identificacion = (EditText) findViewById(R.id.inputId);
        celular = (EditText) findViewById(R.id.inputPhone);
        libro = (EditText) findViewById(R.id.inputBook);
        inputDate = findViewById(R.id.inputDate);
        btnCreateLoan = findViewById(R.id.btnCreateLoan);
        bookDao = new BookDao(this);

        btnCreateLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book b = new Book();
                b.setNombres(nombres.getText().toString());
                b.setIdentificacion(identificacion.getText().toString());
                b.setCelular(celular.getText().toString());
                b.setLibro(libro.getText().toString());
                b.setFecha(inputDate.getText().toString());
                if(!b.isNull()){
                    Toast.makeText(CreateLoanActivity.this, "Error: Campos vacíos", Toast.LENGTH_LONG).show();
                }
                else {
                    bookDao.insertBook(b);
                    Toast.makeText(CreateLoanActivity.this, "Prestamo creado correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateLoanActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.inputDate:
                        showDatePickerDialog();
                        break;
                }
            }
        });

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                inputDate.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.create_loan));
    }

}