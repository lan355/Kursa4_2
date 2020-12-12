package ua.cours.classes.utils;

import java.util.Random;

public class Utils {
    public static Random random = new Random();

    public static int getRandInt(int size)
    {
        return random.nextInt(size + 1);
    }

    public static String getRandString(int size)
    {
        String qwerty = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] temp = new char[size];
        for(int i = 0; i < temp.length;i++)
        {
            temp[i] = qwerty.charAt(getRandInt(qwerty.length() - 1));
        }
        return new String(temp);
    }
    public static int getRandInt(int left, int right)
    {
        return left + random.nextInt(right - left + 1);
    }

    public static boolean getRandBool()
    {
        return random.nextBoolean();
    }

}
