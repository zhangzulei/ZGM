package net.zgm.util;

import net.zgm.model.ZgmMsg;

import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
 * Created by ZYS on 2019/4/30
 */
public class ComparatorDate implements Comparator {
    public static final String TAG = "ComparatorDate";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public int compare(Object o1, Object o2) {
        ZgmMsg z1 = (ZgmMsg) o1;
        ZgmMsg z2 = (ZgmMsg) o2;
        int flag = z2.getDate().compareTo(z1.getDate());
        return flag;
    }
}
