package co.com.uco.tubiblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.uco.tubiblioteca.adapter.LibroAdapter;
import co.com.uco.tubiblioteca.dto.Libro;
import co.com.uco.tubiblioteca.utilidad.ActionBarUtil;

public class ListaLibroActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.LVListaLibros)
    public ListView listViewLibros;
    private LibroAdapter libroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_libro);
        initComponents();
        loadInfo();
    }

    private void initComponents() {
        ButterKnife.bind(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.libros_prestados));
    }

    private void loadInfo(){
        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(new Libro("Libro 1", "Descripción libro 1"));
        listaLibros.add(new Libro("Libro 2", "Descripción libro 2"));
        listaLibros.add(new Libro("Libro 3", "Descripción libro 3"));
        listaLibros.add(new Libro("Libro 4", "Descripción libro 4"));
        listaLibros.add(new Libro("Libro 5", "Descripción libro 5"));
        libroAdapter = new LibroAdapter(this, listaLibros);
        listViewLibros.setAdapter(libroAdapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}