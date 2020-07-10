package com.example.passwordgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText password, length;
    private ImageView showPass, hidePass, showPass2, hidePass2;
    private CheckBox upperCase, lowerCase, symble, number;
    private Button increase, decrease, passGenerate, randomGenerate, recommendation;
    private TextView finalPassword;

    int a = 0, b = 0, c = 0, d = 0, l, j, k, i, m;
    char cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Password Generator");

        password = (EditText) findViewById(R.id.customPasswordId);
        length = (EditText) findViewById(R.id.passwordLengthId);
        showPass = (ImageView) findViewById(R.id.showId);
        showPass2 = (ImageView) findViewById(R.id.showId2);
        hidePass = (ImageView) findViewById(R.id.hideId);
        hidePass2 = (ImageView) findViewById(R.id.hideId2);
        upperCase = (CheckBox) findViewById(R.id.upperCaseId);
        lowerCase = (CheckBox) findViewById(R.id.lowerCaseId);
        symble = (CheckBox) findViewById(R.id.symbleId);
        number = (CheckBox) findViewById(R.id.numberId);
        increase = (Button) findViewById(R.id.increasingId);
        decrease = (Button) findViewById(R.id.decreasingId);
        passGenerate = (Button) findViewById(R.id.generatePassword);
        randomGenerate = (Button) findViewById(R.id.randomPassword);
        recommendation = (Button) findViewById(R.id.recommendationId);
        finalPassword = (TextView) findViewById(R.id.finalPassword);


        showPass.setOnClickListener(this);
        showPass2.setOnClickListener(this);
        hidePass.setOnClickListener(this);
        hidePass2.setOnClickListener(this);
        increase.setOnClickListener(this);
        decrease.setOnClickListener(this);
        passGenerate.setOnClickListener(this);
        randomGenerate.setOnClickListener(this);
        recommendation.setOnClickListener(this);

        finalPassword.setTransformationMethod(new PasswordTransformationMethod());
        password.setTransformationMethod(new PasswordTransformationMethod());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*if(item.getItemId()== R.id.logoutId)
        {
            Toast.makeText(MainActivity.this, "Logging out system is in under development ", Toast.LENGTH_SHORT).show();
        }*/

        if(item.getItemId()== R.id.feedbackId)
        {
            Intent intent = new Intent(MainActivity.this, Feedback.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.aboutId)
        {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        }

        if(item.getItemId()== R.id.shareId)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject = "DefeatCovid app";
            String body = "Hello!\n" +
                    "New Password Generator app is here for you\n" +
                    "Through this app, you can encrypt your own password or generate random password which will secure you account more effectively\n" +
                    "Here is the drive link to download : https://drive.google.com/drive/folders/1y3y1mCppFUiasKjw9PQVQesIf8SKIkxr?usp=sharing";

            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);

            startActivity(Intent.createChooser(intent, "Share with"));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        int a = 0, b = 0, c =0; d = 0;

        if(lowerCase.isChecked())
            a = 1;
        if(upperCase.isChecked())
            b =1;
        if(symble.isChecked())
            c = 1;
        if(number.isChecked())
            d = 1;

        if(v.getId() == R.id.recommendationId)
        {
            Intent intent = new Intent(MainActivity.this, Recommendation.class);
            startActivity(intent);
        }

        if(v.getId()== R.id.increasingId)
        {
            String p = length.getText().toString();
            int m = 0;
            m = Integer.parseInt(p);
            m ++;
            p = Integer.toString(m);
            length.setText(p);
        }

        if(v.getId() == R.id.decreasingId)
        {
            String p = length.getText().toString();
            int m = 0;
            m = Integer.parseInt(p);
            if(m-1>=1)
                m --;
            p = Integer.toString(m);
            length.setText(p);
        }


        if(v.getId()==R.id.showId)
        {
            password.setTransformationMethod(null);
        }

        if(v.getId()== R.id.hideId)
        {
            password.setTransformationMethod(new PasswordTransformationMethod());
        }

        if(v.getId()==R.id.showId2)
        {
            finalPassword.setTransformationMethod(null);
        }

        if(v.getId()== R.id.hideId2)
        {
            finalPassword.setTransformationMethod(new PasswordTransformationMethod());
        }

        if(v.getId()==R.id.generatePassword)
        {
            String s;
            if (password.getText().toString().trim().length() == 0 ) {
                Toast.makeText(this, "You did not enter password", Toast.LENGTH_SHORT).show();
            }
            else if(length.getText().toString().trim().length() == 0){
                Toast.makeText(this, "You did not enter password length", Toast.LENGTH_SHORT).show();
            }
            else
            {
                s = password.getText().toString();

                if(a==1 && b==0 && c == 0 && d==0)
                    lowerCase(s);
                if(a == 0 && b==1 && c == 0 && d==0)
                    upperCase(s);
                if(a==0 && b==0 && c == 1 && d==0)
                    symbol(s);
                if(a==0 && b==0&& c == 0 && d==1)
                    number(s);
                if(a == 1 && b == 1 && c == 0 && d == 0)
                    lowerUpper(s);
                if(a==1 && b==0 && c == 1 && d==0)
                    lowerSymbol(s);
                if(a==1 && b==0 && c == 0 && d==1)
                    lowerNumber(s);
                if(a==0 && b==1 && c == 1 && d==0)
                    upperSymbol(s);
                if(a==0 && b==1 && c == 0 && d==1)
                    upperNumber(s);
                if(a==0 && b==0 && c == 1 && d==1)
                    symbolNumber(s);
                if(a==1 && b==1 && c == 1 && d == 0)
                    lowerUpperSymbol(s);
                if(a==1 && b==0 && c == 1 && d == 1)
                    lowerSymbolNumber(s);
                if(a==0 && b==1 && c == 1 && d==1)
                    upperSymbolNumber(s);
                if(a==1 && b==1 && c == 0 && d==1)
                    lowerUpperNumber(s);
                if(a==1 && b==1 && c == 1 && d==1)
                    lowerUpperSymbolNumber(s);
                if(a == 0 && b == 0 && c == 0 && d == 0)
                    Toast.makeText(this, "Enter at least one condition, four is recomended", Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId()==R.id.randomPassword)
        {
            String s = random();
            if(length.getText().toString().trim().length() == 0){
                Toast.makeText(this, "You did not enter password length", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(a==1 && b==0 && c == 0 && d==0)
                    lowerCase(s);
                if(a == 0 && b==1 && c == 0 && d==0)
                    upperCase(s);
                if(a==0 && b==0 && c == 1 && d==0)
                    symbol(s);
                if(a==0 && b==0&& c == 0 && d==1)
                    number(s);
                if(a == 1 && b == 1 && c == 0 && d == 0)
                    lowerUpper(s);
                if(a==1 && b==0 && c == 1 && d==0)
                    lowerSymbol(s);
                if(a==1 && b==0 && c == 0 && d==1)
                    lowerNumber(s);
                if(a==0 && b==1 && c == 1 && d==0)
                    upperSymbol(s);
                if(a==0 && b==1 && c == 0 && d==1)
                    upperNumber(s);
                if(a==0 && b==0 && c == 1 && d==1)
                    symbolNumber(s);
                if(a==1 && b==1 && c == 1 && d == 0)
                    lowerUpperSymbol(s);
                if(a==1 && b==0 && c == 1 && d==1)
                    lowerSymbolNumber(s);
                if(a==0 && b==1 && c == 1 && d==1)
                    upperSymbolNumber(s);
                if(a==1 && b==1 && c == 0 && d==1)
                    lowerUpperNumber(s);
                if(a==1 && b==1 && c == 1 && d==1)
                    lowerUpperSymbolNumber(s);
                if(a == 0 && b == 0 && c == 0 && d == 0)
                    Toast.makeText(this, "Enter at least one condition, four is recomended", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void lowerCase(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x =(int) s.charAt(j) + 97;
                while(x>122)
                {
                    x = x - 121 + (s.charAt(j)%2);
                    x = x+97;
                }
                cache = (char) x;
                r = r + cache;
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }

        finalPassword.setText(r);
    }

    public void upperCase(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x =(int) s.charAt(j) + 65;
                while(x>90)
                {
                    x = x - 89 - (s.charAt(j)%2);
                    x = x+65;
                }
                cache = (char) x;
                r = r + cache;
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }



    public void symbol(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x =(int) s.charAt(j) + 33;
                while(x>47)
                {
                    x = x - 47 + (s.charAt(j)%2);
                    x = x+33;
                }
                if(x == 39)
                    x = 64;
                if(x == 44)
                    x = 58;
                cache = (char) x;
                r= r+cache;
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }


    public void number(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x =(int) s.charAt(j) + 47;
                while(x>57)
                {
                    x = x - 57 + (s.charAt(j)%2);
                    x = x+47;
                }
                cache = (char) x;
                r= r+(cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }


    public void lowerUpper(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m)
                {
                    x =(int) s.charAt(j) + 97; //lower case shift

                    while(x>122)
                    {

                        x = x - 121 + (s.charAt(j)%2);
                        x = x+97;
                    }
                }

                else if(s.charAt(j) %2 !=0 || i==m)
                {
                    x =(int) s.charAt(j) + 65; // uppercase shift
                    while(x>90)
                    {

                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }

                cache = (char) x;
                r = r+ (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }

        finalPassword.setText(r);
    }


    public void lowerSymbol(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m)
                {
                    x =(int) s.charAt(j) + 97; //lower case shift

                    while(x>122)
                    {

                        x = x - 121 + (s.charAt(j)%2);
                        x = x+97;
                    }
                }

                else if(s.charAt(j) %2 !=0 || i==m)
                {
                    x =(int) s.charAt(j) + 33; // special char shift
                    while(x>47)
                    {
                        x = x - 47 + (s.charAt(j)%2);
                        x = x+33;
                    }
                    if(x == 39)
                        x = 64;
                    if(x == 44)
                        x = 58;
                }

                cache = (char) x;
                r = r + (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }


    public void lowerNumber(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m)
                {
                    x =(int) s.charAt(j) + 97; //lower case shift

                    while(x>122)
                    {

                        x = x - 121 + (s.charAt(j)%2);
                        x = x+97;
                    }
                }

                else if(s.charAt(j) %2 !=0 || i==m)
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                cache = (char)x;
                r = r + (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }


    public void upperSymbol(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m)
                {
                    x =(int) s.charAt(j) + 65; // upper case shift
                    while(x>90)
                    {
                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }

                else if(s.charAt(j) %2 !=0 || i==m)
                {
                    x =(int) s.charAt(j) + 33; // special char shift
                    while(x>47)
                    {
                        x = x - 47 + (s.charAt(j)%2);
                        x = x+33;
                    }
                    if(x == 39)
                        x = 64;
                    if(x == 44)
                        x = 58;
                }

                cache = (char) x;
                r = r + (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }

        finalPassword.setText(r);
    }


    public void upperNumber(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m)
                {
                    x =(int) s.charAt(j) + 65; // upper case shift
                    while(x>90)
                    {
                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }

                else if(s.charAt(j) %2 !=0 || i==m)
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                cache = (char) x;
                r = r+(cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }

        finalPassword.setText(r);
    }


    public void symbolNumber(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);

        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m)
                {
                    x =(int) s.charAt(j) + 33; // special char shift
                    while(x>47)
                    {
                        x = x - 47 + (s.charAt(j)%2);
                        x = x+33;
                    }
                    if(x == 39)
                        x = 64;
                    if(x == 44)
                        x = 58;
                }

                else if(s.charAt(j) %2 !=0 || i==m)
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                cache = (char) x;
                r = r + (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }


    public void lowerUpperSymbol(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);
        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m && i!= m/2)
                {
                    x =(int) s.charAt(j) + 97; //lower case shift

                    while(x>122)
                    {

                        x = x - 121 + (s.charAt(j)%2);
                        x = x+97;
                    }
                }

                else if((s.charAt(j) %3 == 0 || i==m) && i != m/2)
                {
                    x =(int) s.charAt(j) + 65; // uppercase shift
                    while(x>90)
                    {

                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }

                else if((s.charAt(j) %2 != 0 && s.charAt(j)% 3!=1) || i==m/2)
                {
                    x =(int) s.charAt(j) + 33; // special char shift
                    while(x>47)
                    {
                        x = x - 47 + (s.charAt(j)%2);
                        x = x+33;
                    }
                    if(x == 39)
                        x = 64;
                    if(x == 44)
                        x = 58;
                }
                else
                {
                    x =(int) s.charAt(j) + 65; // uppercase shift
                    while(x>90)
                    {

                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }

                cache = (char) x;
                r = r+(cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }


    public void lowerSymbolNumber(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);
        int i = 1;

        while(i<=m)
        {
            for(int j = 0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i != m && i!= m/2)
                {
                    x =(int) s.charAt(j) + 97; //lower case shift

                    while(x>122)
                    {

                        x = x - 121 + (s.charAt(j)%2);
                        x = x+97;
                    }
                }

                else if((s.charAt(j) %3 == 0 || i==m) && i != m/2)
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j) % 2);
                        x = x+47;
                    }
                }

                else if((s.charAt(j) %3 != 0 ) || i == m/2)
                {
                    x =(int) s.charAt(j) + 33; // special char shift
                    while(x>47)
                    {
                        x = x - 47 + (s.charAt(j)%2);
                        x = x+33;
                    }
                    if(x == 39)
                        x = 64;
                    if(x == 44)
                        x = 58;
                }
                else
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j) % 2);
                        x = x+47;
                    }
                }

                cache = (char) x;
                r = r + (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }


    public void upperSymbolNumber(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);
        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m && i!= m/2)
                {
                    x =(int) s.charAt(j) + 65; // upper case shift
                    while(x>90)
                    {
                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }

                else if((s.charAt(j) %3 == 0 || i==m) && i != m/2)
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                else if((s.charAt(j) %2 != 0 && s.charAt(j)% 3!=1) || i==m/2)
                {
                    x =(int) s.charAt(j) + 33; // special char shift
                    while(x>47)
                    {
                        x = x - 47 + (s.charAt(j)%2);
                        x = x+33;
                    }
                    if(x == 39)
                        x = 64;
                    if(x == 44)
                        x = 58;
                }
                else
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                cache = (char) x;
                r = r + (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }



    public void lowerUpperNumber(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);
        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m && i!= m/2)
                {
                    x =(int) s.charAt(j) + 65; // upper case shift
                    while(x>90)
                    {
                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }

                else if((s.charAt(j) %3 == 0 || i==m) && i != m/2)
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                else if((s.charAt(j) %2 != 0 && s.charAt(j)% 3!=1) || i==m/2)
                {
                    x =(int) s.charAt(j) + 97; // lower case shift
                    while(x>122)
                    {
                        x = x - 121 + (s.charAt(j)%2);
                        x = x+97;
                    }
                }
                else
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                cache = (char) x;
                r = r + (cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }
        finalPassword.setText(r);
    }



    public void lowerUpperSymbolNumber(String s)
    {
        String r = "", p;
        l = s.length();
        p = length.getText().toString();

        int m = 0;
        m = Integer.parseInt(p);
        int i = 1;

        while(i<=m)
        {
            for(int j =0; j<l; j++)
            {
                int x = 0;
                if((s.charAt(j)%2==0 || i==1) && i !=m && i!= m/2 && i != (m/2+1))
                {
                    x =(int) s.charAt(j) + 33; // special char shift
                    while(x>47)
                    {
                        x = x - 47 + (s.charAt(j)%2);
                        x = x+33;
                    }
                    if(x == 39)
                        x = 64;
                    if(x == 44)
                        x = 58;

                }

                else if((s.charAt(j) %3 == 2 || s.charAt(j)%3 == 0 || i==m/2) && i != (m/2+1) && i!= m)
                {
                    x =(int) s.charAt(j) + 97; // lower case shift
                    while(x>122)
                    {
                        x = x - 121 + (s.charAt(j)%2);
                        x = x+97;
                    }
                }

                else if((s.charAt(j) %3 == 1 || i == (m/2 +1)) && i!=m)
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                else if(((s.charAt(j)%3!=1 && s.charAt(j)%2!=0) || i==m))
                {
                    x =(int) s.charAt(j) + 65; // upper case shift
                    while(x>90)
                    {
                        x = x - 89 - (s.charAt(j)%2);
                        x = x+65;
                    }
                }
                else
                {
                    x =(int) s.charAt(j) + 47; // numeric char shift
                    while(x>57)
                    {
                        x = x - 57 + (s.charAt(j)%2);
                        x = x+47;
                    }
                }

                cache = (char) x;
                r = r +(cache);
                i++;
                if(i>=m)
                {
                    break;
                }
                s = new StringBuilder(s).reverse().toString();
            }
        }

        finalPassword.setText(r);
    }


    public String random()
    {
        String r = "";
        char c = 33;
        Random rn = new Random();
        for(int i = 1; i<100; i++)
        {
            int x = rn.nextInt(88);
            char y = (char)(c + x);
            r = r+y;
        }
        return r;
    }
}