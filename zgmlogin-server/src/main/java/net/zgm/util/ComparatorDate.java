package net.zgm.util;

import net.zgm.model.ZgmMsg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by ZYS on 2019/4/30
 */
public class ComparatorDate implements Comparator {
    public static final String TAG = "ComparatorDate";
    SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d H:mm:ss");


    @Override
    public int compare(Object o1, Object o2) {
        ZgmMsg z1 = (ZgmMsg) o1;
        ZgmMsg z2 = (ZgmMsg) o2;
        int flag = z1.getDate().compareTo(z2.getDate());
        return flag;
    }
}
