package com.github.lpx.service;

import com.github.lpx.exception.AnimalNotFountException;
import com.github.lpx.exception.InsufficientBalanceException;

import java.util.Scanner;

public class OperationPage {
    private MyPetStore myPetStore;
    private Scanner sc = new Scanner(System.in);

    public OperationPage(MyPetStore myPetStore) {
        this.myPetStore = myPetStore;
    }

    public void showPage() {
        int judge;
        do {
            System.out.println("输入1进入宠物店管理员界面，输入2进入顾客购买页面，输入其余数字则退出总页面");
            judge = sc.nextInt();
            if (judge == 1) {
                int k;
                do {
                    System.out.println("欢迎进入管理员界面");
                    System.out.println("输入1购入宠物，输入2为账户注入资金，输入3在账户中取出资金，输入4开始营业，输入5暂停营业并展示当天客户，输入其它退到主页面");
                    k = sc.nextInt();
                    if (k == 1) {
                        try {
                            myPetStore.buyPet();
                        } catch (InsufficientBalanceException e) {
                            e.printStackTrace();
                        }
                    } else if (k == 2) {
                        System.out.println("请输入要注入的金额:");
                        double money = sc.nextDouble();
                        myPetStore.addBalance(money);
                    } else if (k == 3) {
                        System.out.println("请输入要取出的金额:");
                        double money = sc.nextDouble();
                        try {
                            myPetStore.removeBalance(money);
                        } catch (InsufficientBalanceException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(k==4){
                        myPetStore.openStore();
                        System.out.println("开始营业了");
                    }
                    else if(k==5){
                        myPetStore.closeStore();
                    }else break;
                } while (true);
            } else if (judge == 2) {
                if(!myPetStore.isOpen()){
                    System.out.println("抱歉，店关门了");
                    continue;
                }
                int k;
                do {
                    System.out.println("欢迎进入顾客页面");
                    System.out.println("输入1买宠物，输入其它则退到主页面");
                    k = sc.nextInt();
                    if (k == 1) {
                        try {
                            myPetStore.serviceToCustomer();
                        } catch (AnimalNotFountException e) {
                            e.printStackTrace();
                        }
                    } else break;
                } while (true);
            } else break;
        } while (true);
    }
}
