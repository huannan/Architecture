package com.nan.day16_pattern_proxy.simple1;

/**
 * 银行办理业务 - 代理对象 - 银行的业务员
 * 静态代理:需要修改地方多,代理方法逻辑重复
 */
public class BankWorker implements IBank {

    private IBank bank;

    /**
     * 持有被代理的对象
     *
     * @param bank
     */
    public BankWorker(IBank bank) {
        this.bank = bank;
    }

    @Override
    public void applyBank() {
        System.out.println("开始受理");
        bank.applyBank();
        System.out.println("操作完毕");
    }

    @Override
    public void lostBank() {
        System.out.println("开始受理");
        bank.lostBank();
        System.out.println("操作完毕");
    }
}
