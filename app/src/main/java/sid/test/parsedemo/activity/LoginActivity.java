package sid.test.parsedemo.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

//import com.google.android.gms.common.AccountPicker;

import java.util.regex.Pattern;

import sid.test.parsedemo.R;
import sid.test.parsedemo.activities.MainActivityNew;
import sid.test.parsedemo.helper.ParseUtils;
import sid.test.parsedemo.helper.PrefManager;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
    private PrefManager pref;
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    String usernametxt;
    String passwordtxt;

    //  private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            Intent intent;
            intent = new Intent(
                    LoginActivity.this,
                    MainActivityNew.class);
            startActivity(intent);
        } else {
            // show the signup or login screen

            setContentView(R.layout.activity_login);
            // Verifying parse configuration. This is method is for developers only.
            ParseUtils.verifyParseConfiguration(this);

            inputEmail = (EditText) findViewById(R.id.email);
            inputPassword = (EditText) findViewById(R.id.password);
            btnLogin = (Button) findViewById(R.id.btnLogin);
            btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

//            inputEmail.setOnTouchListener(new OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                           pickUserAccount();
//
//                    return false;
//                }
//            });
            // Progress dialog
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);

            btnLogin.setOnClickListener(this);
            btnLinkToRegister.setOnClickListener(this);
        }
    }

    private void pickUserAccount() {
//        String[] accountTypes = new String[]{"com.google"};
//        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
//                accountTypes, false, null, null, null, null);
//        startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                Toast.makeText(this, possibleEmail, Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void onActivityResult(final int requestCode, final int resultCode,
                                    final Intent data) {
        if (requestCode == REQUEST_CODE_PICK_ACCOUNT && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        }
    }

    private void login() {
        usernametxt = inputEmail.getText().toString();
        passwordtxt = inputPassword.getText().toString();
        pDialog.setMessage("Logging in ...");
        showDialog();
        ParseUser.logInInBackground(usernametxt, passwordtxt,
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            hideDialog();
                            // If user exist and authenticated, send user to Welcome.class
                            Intent intent;
                            intent = new Intent(
                                    LoginActivity.this,
                                    MainActivityNew.class);
                            startActivity(intent);
//                            Toast.makeText(getApplicationContext(),
//                                    "Successfully Logged in",
//                                    Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            hideDialog();
                            Toast.makeText(

                                    getApplicationContext(),
                                    "No such user exist, please signup",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void signup() {
        startActivity(new Intent(LoginActivity.this, SignUp.class));
        finish();

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnLinkToRegisterScreen:
                signup();
            default:
        }
    }
}