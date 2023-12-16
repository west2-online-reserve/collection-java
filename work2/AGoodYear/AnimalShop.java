interface AnimalShop {
    /**
     * 宠物店买入动物
     *
     * @param a 要买入的动物
     * @throws InsufficientBalanceException 如果余额不足
     */
    void buyNewAnimal(AbstractAnimal a);

    /**
     * 宠物店招待顾客
     *
     * @param c 要招待的顾客
     * @throws AnimalNotFountException 如果宠物店内没有动物
     */
    void receiptCustomer(Customer c);

    /**
     * 关闭宠物店并输出当日经营信息
     */
    void closeShop();
}
