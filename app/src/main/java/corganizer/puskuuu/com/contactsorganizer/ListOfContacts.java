package corganizer.puskuuu.com.contactsorganizer;
import android.net.Uri;
/**
 * Created by user on 3/30/2017.
 */

public class ListOfContacts {
    private String _name,_phone,_mail,_address;
    private Uri _imguri;
    public ListOfContacts(String name, String phone, String mail, String address, Uri imguri){
        _name = name;
        _phone=phone;
        _mail=mail;
        _address=address;
        _imguri=imguri;

    }

    public String get_name() {
        return _name;
    }
    public String get_phone() {
        return _phone;
    }

    public String get_mail() {
        return _mail;
    }

    public String get_address() {
        return _address;
    }

    public Uri get_imguri() {
        return _imguri;
    }
}
