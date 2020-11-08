package co.com.uco.tubiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.com.uco.tubiblioteca.dto.Book;
import co.com.uco.tubiblioteca.dto.Usuario;

public class BookDao {
    Context context;
    Book libro;
    ArrayList<Book> lista;
    SQLiteDatabase sql;
    String bd="BdUsuarios";
    String tabla2 = "create table if not exists libro(id integer primary key autoincrement, nombres text, identificacion text, celular text, libro text, fecha text)";

    public BookDao(Context context) {
        this.context = context;
        sql = context.openOrCreateDatabase(bd, context.MODE_PRIVATE, null);
        sql.execSQL(tabla2);
        libro = new Book();
    }

    public boolean insertBook(Book libro) {
        if(buscar(libro.getLibro()) == 0){
            ContentValues content = new ContentValues();
            content.put("nombres", libro.getNombres());
            content.put("identificacion", libro.getIdentificacion());
            content.put("celular", libro.getCelular());
            content.put("libro", libro.getLibro());
            content.put("fecha", libro.getFecha());
            return (sql.insert("libro", null, content)>0);
        }
        else {
            return false;
        }
    }

    public int buscar(String l){
        int x = 0;
        lista = selectBook();
        for(Book book : lista){
            if (book.getLibro().equals(l)){
                x++;
            }
        }
        return x;
    }
    public ArrayList<Book> selectBook(){
        ArrayList<Book> lista = new ArrayList<Book>();
        lista.clear();
        Cursor cursor = sql.rawQuery("select * from libro", null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                Book u = new Book();
                u.setId(cursor.getInt(0));
                u.setNombres(cursor.getString(1));
                u.setIdentificacion(cursor.getString(2));
                u.setCelular(cursor.getString(3));
                u.setLibro(cursor.getString(4));
                u.setFecha(cursor.getString(5));
                lista.add(u);
            }while (cursor.moveToNext());
        }
        return lista;
    }
}
