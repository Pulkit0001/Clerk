package com.example.clerk.UIfragments.Charges.NewChargeFragments;

import androidx.lifecycle.ViewModel;

import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.databaseEntities.Charge;

public class NewChargeViewModel extends ViewModel {

    public enum Interval {

        ONE_TIME(0),
        YEARLY(1),
        HALF_YEARLY(2),
        QUARTER_YEARLY(3),
        MONTHLY(4),
        HALF_MONTHLY(5),
        WEEKLY(6);

        int selectedEnum = -1;
        Interval(int i) {
            selectedEnum=i;
        }

        public int getValue(){
            return selectedEnum;
        }
    }

    public void addCharge(String chargeName, String chargeAmount) {
    }

    public void addCharge(String chargeName, String chargeAmount, Interval repaymentInput, int chargeType) {
        System.out.println("Entered Fields Are "+chargeName+"  "+chargeAmount+"  "+repaymentInput.getValue()+"  "+chargeType+"......." +
                ".....................");
        Charge charge = new Charge();
        charge.setChargeAmount(Integer.parseInt(chargeAmount));
        charge.setChargeName(chargeName);
        charge.setPaymentInterval(repaymentInput.getValue());
        AccountsRepository.insertCharge(charge);
    }
}
