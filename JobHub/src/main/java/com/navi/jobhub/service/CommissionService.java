package com.navi.jobhub.service;

import com.navi.jobhub.data.CommissionDAO;
import com.navi.jobhub.model.Commission;

import java.util.List;

public class CommissionService {
    private final CommissionDAO commissionDAO;

    public CommissionService(){
        commissionDAO = new CommissionDAO();
    }

    public List<Commission> listAll(){
        return commissionDAO.select();
    }
    public double actualCommission(){
        return commissionDAO.actualCommission();
    }
    public void updateCommission(double c){
        commissionDAO.insert(c);
        List<Commission> commissions = listAll();
        Commission actual = commissions.get(commissions.size()-1);
        Commission before;
        if (listAll().size() >= 2) {
            before = commissions.stream().skip(commissions.size() - 2)
                    .findFirst()
                    .orElse(null);
        }
        else{
            before = commissions.get(0);
        }
        commissionDAO.updateEndDate(before, actual.getStartDate());
    }
}
