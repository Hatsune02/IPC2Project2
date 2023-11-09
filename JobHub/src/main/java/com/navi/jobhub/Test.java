package com.navi.jobhub;

import com.navi.jobhub.model.Employer;
import com.navi.jobhub.service.EmployerService;

public class Test {

    public static void main(String[] args){
        EmployerService e  = new EmployerService();
        for(Employer employer : e.listAll()){
            System.out.println(employer);
            System.out.println(employer.getMission());
            System.out.println(employer.getVision());

        }
    }
}
