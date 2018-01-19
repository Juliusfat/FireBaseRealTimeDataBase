package cp.fr.firebaserealtimedatabase;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cp.fr.firebaserealtimedatabase.model.Auteur;
import cp.fr.firebaserealtimedatabase.model.Book;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference bookReference;
    private List<Book> booklist;
    private ListView booklistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        bookReference = firebaseDatabase.getReference().child("books");
        booklist = new ArrayList<>();

        booklistview = findViewById(R.id.bookListView);

        //récupération des données avec abonnement aux modifications ultérieures
        bookReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //réintialiser la liste
                booklist.clear();

                //boucler sur les résultats de l'ensemble des noeuds
                for (DataSnapshot booksnapshoot : dataSnapshot.getChildren() ){
                    //création d'une instance de book et hydratation avec les données du snapshoot
                    Book book = booksnapshoot.getValue(Book.class);
                    //ajout du livre à la liste
                    booklist.add(book);
                }

                Log.d ("MAIN", "FIN DE LA RECUPERATION DES DONNEES");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("MAIN","FIN DE ONCREATE");
        //addBooks();
    }

    private void addBooks() {


        Auteur hugo = new Auteur("Hugo", "Victor", "Francais");
        Auteur auster = new Auteur("Auster", "Paul", "Américain");

        String bookId = bookReference.push().getKey();
        Book book = new Book ("Les Misérables", 18.0,hugo);
        bookReference.child(bookId).setValue(book);

        bookId = bookReference.push().getKey();
        book = new Book ("Ruy Blas", 13.0,hugo);
        bookReference.child(bookId).setValue(book);

        bookId = bookReference.push().getKey();
        book = new Book ("New York", 19.0,auster);
        bookReference.child(bookId).setValue(book);


    }

    private class BookArrayAdapter extends ArrayAdapter<Book> {

        private Activity context;
        int resource;
        List<Book> data;

        public BookArrayAdapter(Activity context, int resource, List<Book> data) {
            super(context, resource, data);
            this.context = context;
            this.resource = resource;
            this.data = data;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = context.getLayoutInflater().inflate(this.resource, parent, false);

            Book currentBook = booklist.get(position);
            TextView textview = findViewById(R.id.bookListText);
            textview.setText(currentBook.getTitle() + "par" + currentBook.getAuthor());

            return view;
        }

    }


}
