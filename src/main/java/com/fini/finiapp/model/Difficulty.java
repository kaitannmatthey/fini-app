package com.fini.finiapp.model;

public enum Difficulty
{
    EASY,
    MEDIUM,
    HARD;

    @Override
    public String toString()
    {
        String s;
        switch (this.ordinal())
        {
            case 0:
            {
                s = "easy";
                break;
            }
            case 1:
            {
                s = "medium";
                break;
            }
            case 2:
            {
                s = "hard";
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
