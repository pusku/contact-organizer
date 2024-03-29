package corganizer.puskuuu.com.contactsorganizer;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity {
    EditText nameTxt, phoneTxt, emailTxt, addressTxt;
    ImageView contactImg;
    List<ListOfContacts> Contacts = new ArrayList<ListOfContacts>();
    ListView contactList;
    Uri imguri=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = (EditText) findViewById(R.id.PersonName);
        phoneTxt = (EditText) findViewById(R.id.PhoneNo);
        emailTxt = (EditText) findViewById(R.id.email);
        addressTxt = (EditText) findViewById(R.id.PostalAddress);
        contactList = (ListView) findViewById(R.id.ListView);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        contactImg= (ImageView) findViewById(R.id.contactimg);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.Creator);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("List");
        tabSpec.setContent(R.id.List);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);

        final Button addbtn = (Button) findViewById(R.id.addbutton);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contacts.add(new ListOfContacts(nameTxt.getText().toString(), phoneTxt.getText().toString(), emailTxt.getText().toString(), addressTxt.getText().toString(),imguri));
                gatherList();
                Toast.makeText(getApplicationContext(), nameTxt.getText().toString() + " has been added succesfully!", Toast.LENGTH_SHORT).show();
            }
        });
        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addbtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       contactImg.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent= new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
           }
       });
    }
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode==RESULT_OK){
            if(reqCode==1){
                imguri = data.getData();
                contactImg.setImageURI(data.getData());
            }
        }
    }

    private void gatherList() {
        ArrayAdapter<ListOfContacts> adapter = new ContactListAdapter();
        contactList.setAdapter(adapter);
    }



    private class ContactListAdapter extends ArrayAdapter<ListOfContacts> {
        public ContactListAdapter() {
            super(MainActivity.this, R.layout.listitem, Contacts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listitem, parent, false);

            ListOfContacts CurrentContact = Contacts.get(position);

            TextView name = (TextView) view.findViewById(R.id.textView);
            name.setText(CurrentContact.get_name());
            TextView phone = (TextView) view.findViewById(R.id.textView2);
            phone.setText(CurrentContact.get_phone());
            TextView mail = (TextView) view.findViewById(R.id.textView3);
            mail.setText(CurrentContact.get_mail());
            TextView address = (TextView) view.findViewById(R.id.textView4);
            address.setText(CurrentContact.get_address());
            ImageView cmid=(ImageView) view.findViewById(R.id.cmid);
            cmid.setImageURI(CurrentContact.get_imguri());

            return view;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}