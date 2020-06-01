package com.fini.finiapp.model;

public enum Priority
{
    LOW,
    MEDIUM,
    HIGH;

    @Override
    public String toString()
    {
        String s;
        switch (this.ordinal())
        {
            case 0:
            {
                s = "low";
                break;
            }
            case 1:
            {
                s = "medium";
                break;
            }
            case 2:
            {
                s = "high";
                break;
            }
            default:
            {
                s = "";
                break;
            }
        }
        return s;
    }
}
