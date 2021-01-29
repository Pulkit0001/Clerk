package com.example.clerk.UIfragments.Candidates.CandidateFullView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.List;

public class CandidateFullView  extends ViewModel {
    public LiveData<Candidate> getCandidateDetails(int candidateID) {
        return AccountsRepository.getCandidateDetails(candidateID);
    }

    public LiveData<PendingPayment> getPendingPayment(int candidateID) {
        return AccountsRepository.getPendingPaymentOfCandidate(candidateID);
    }

    public LiveData<List<ChargeOfGroupListDataObj>> getChargesOfCandidate(int candidateId) {
        return AccountsRepository.getChargesOnCandidateList(candidateId);
    }
}
