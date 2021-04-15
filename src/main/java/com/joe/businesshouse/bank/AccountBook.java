package com.joe.businesshouse.bank;

public class AccountBook {
    private int liquidCash;
    private int assetValue;

    public int totalValue() {
        return liquidCash + assetValue;
    }

    public void addLiquidCash(int amount) {
        liquidCash += amount;
    }

    public void addAssetValue(int amount) {
        assetValue += amount;
    }

    @Override
    public String toString() {
        return String.format("Cash: %d\tAsset: %d\t\tTotal: %d", liquidCash, assetValue, totalValue());
    }
}
