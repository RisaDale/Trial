package com.risa.TipCalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;



public class TipCalculator extends Activity
{

    //constants used when saving/restoring activity

    private static final String BILL_TOTAL= "BILL_TOTAL";
    private static final String CUSTOM_PERCENT= "CUSTOM_PERCENT";

    private double currentBillTotal; //bill amount entered by the user
    private int currentCustomPercent; // tip % with seekbar
    private EditText  tip10EditText; //displays 10% tip
    private EditText  total10EditText; //displays total with 10% tip
    private EditText  tip15EditText; //displays 15% tip
    private EditText  total15EditText; //displays total with 15% tip
    private EditText  tip20EditText; //displays 20% tip
    private EditText  total20EditText; //displays total with 20% tip

    private TextView customTipTextView; //displays custom tip percentage
    private EditText tipCustomEditText;  //custom tip percentage
    private EditText totalCustomEditText; // displays total with custom tip

    private EditText billEditText;  //accepts user input for bill





    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (savedInstanceState== null)

        {
          currentBillTotal =0.0;
          currentCustomPercent = 18;
        } //end if

        else        //restored from memory not scratch

        {
            //initialize bill amount to saved amount
            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
            // initialize custom tip to saved tip amount
            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);

        }   // end else

        tip10EditText = (EditText) findViewById(R.id.tip10EditText);
        total10EditText= (EditText) findViewById(R.id.total10EditText);
        tip15EditText = (EditText)findViewById(R.id.tip15EditText);
        total15EditText = (EditText)findViewById(R.id.total15EditText);
        tip20EditText = (EditText)findViewById(R.id.tip20EditText);
        total20EditText = (EditText)findViewById(R.id.total20EditText);


        // get the TextView displaying the custom Tip percentage

        customTipTextView= (TextView) findViewById(R.id.customTiptextView);

        // get the custom tip and total edit texts

        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);


        billEditText.addTextChangedListener(billEditTextWatcher);

        // get the Seekbar used to set the custom tip amount

        SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);

    }  //end method on create


    // update 10, 15 ,20  percent

    private void updateStandard()
    {
          // calculate bill with 10% tip
        double tenPercentTip = currentBillTotal * .1;
        double  tenPercentTotal = currentBillTotal + tenPercentTip;

        // send tip edit text to tenPercentTip
        tip10EditText.setText(String.format("%.02f",tenPercentTip));
        total10EditText.setText(String.format("%.02f",tenPercentTotal));

         //  calculate bill total with 15 % tip

          double fifteenPercentTip = currentBillTotal * .15;
          double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;

        tip15EditText.setText(String.format("%.02f",fifteenPercentTip));
        total15EditText.setText(String.format("%.02f",fifteenPercentTotal));

        // calculate bill total with a 20% tip

        double twentyPercentTip = currentBillTotal * .20;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;

        // set twenty edit text to twenty percent tip

        tip20EditText.setText(String.format("%02f",twentyPercentTotal));
        total20EditText.setText(String.format("%02f",twentyPercentTotal));



    }

    private void updateCustom()
    {
        // set Custom Tip view text to match the position of the seekbar

        customTipTextView.setText(currentCustomPercent + "%");
        // calculate the custom tip amount

        double customTipAmount = currentBillTotal * currentCustomPercent * .01;

        //calculate the total bill, including the custom tip

        double customTotalAmount = currentBillTotal + customTipAmount;

        // display the tip and total bill amounts

        tipCustomEditText.setText(String.format("%02f",customTipAmount));
        totalCustomEditText.setText(String.format("%02f",customTotalAmount));




    }   // end method Update Custom


    // save values of bill edit text and custom seek bar

    @Override
    protected void onSaveInstanceState (Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putDouble(BILL_TOTAL,currentBillTotal);
        outState.putInt(CUSTOM_PERCENT,currentCustomPercent);



    }   // end OnsaveInstance State

    // called when the user cahnges the position of the seekbar


    private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {


                currentCustomPercent = seekBar.getProgress();
                updateCustom(); //update edit texts for custom tip and total
            }



        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }    // end method on tracking touch

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }

    } ;

        private TextWatcher billEditTextWatcher = new TextWatcher()
        {



            @Override
            public void onTextChanged(CharSequence s
                    , int start, int before , int count)
            {


                // convert BillEdit text to a double

                try
                {
                    currentBillTotal = Double.parseDouble(s.toString());

                }        //end try

                catch (NumberFormatException e)
                {
                    currentBillTotal= 0.0; //
                }        // end catch

                updateStandard();
                updateCustom();


            }  // end method on text changed




            @Override
            public void afterTextChanged(Editable s) {

            }   // end method after text changed


            @Override
            public void beforeTextChanged(CharSequence s,int start, int count, int after)
            {
             //anonymous method
            }
};

    public void setBillEditText(EditText billEditText) {
        this.billEditText = billEditText;
    }
}

