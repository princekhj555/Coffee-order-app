/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.java;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     *
     */
    int quantity = 0;

    public void submitOrder(View view) {
        CheckBox creamCheck= (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasCream = creamCheck.isChecked();
        CheckBox chocoCheck= (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChoco = chocoCheck.isChecked();
        EditText name =(EditText) findViewById(R.id.name_input);
        Editable uName = name.getText();
        //displayMessage(createOrderSummary(calcPrice(),hasCream,hasChoco,uName));
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_SUBJECT, "CofFee order by "+uName);
        email.putExtra(Intent.EXTRA_TEXT, createOrderSummary(calcPrice(),hasCream,hasChoco,uName));
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Place order  :"));
        Context context = getApplicationContext();
        CharSequence text = "Placing Order Proceeded :\n mail and get Receipt  ";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
    public int calcPrice()
    {
        int price= quantity*25;
        CheckBox creamCheck= (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasCream = creamCheck.isChecked();
        CheckBox chocoCheck= (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChoco = chocoCheck.isChecked();
        if(hasChoco) price+=(15*quantity);
        if(hasCream) price+=(10*quantity);
        return price;
    }
    public void increment(View view) {
        if(quantity>=100)
        {
            Context context = getApplicationContext();
            CharSequence text = "You Can order  max. 100 cups only at a time";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        display(++quantity);
    }
    public void decrement(View view) {
        if(quantity<=1)
        {
            Context context = getApplicationContext();
            CharSequence text = "There should be atleast one cup in the order";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        display(--quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
   /* private void displayPrice(int number) {
        //int pr= calcPrice();
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
       orderSummaryTextView.setText( NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given text on the screen.
     */
    public void reset(View view)
    {
        quantity=0;
        display(0);
    }
    public String createOrderSummary(int price,boolean hasCream,boolean hasChoco,Editable uName)
    {
       /* TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText("::::RECEIPT:::::\n" +
                                "Name: PRINCE SHARMA\n" +
                "No. of cups ordered =  "+ quantity+
                "\n TOTAL Price = " + NumberFormat.getCurrencyInstance().format(calcPrice()));
        displayMessage("\nThanks!! visit again");*/
        String s = "::::RECEIPT:::::\n" +
                "Name: " + uName  +
                "\nAdd Whipped cream ? " + hasCream +
                "\nAdd Chololate ? " + hasChoco+
                "\nNo. of cups ordered =  " + quantity +
                "\n TOTAL Price :  " + NumberFormat.getCurrencyInstance().format(calcPrice());
        return s;

    }
}