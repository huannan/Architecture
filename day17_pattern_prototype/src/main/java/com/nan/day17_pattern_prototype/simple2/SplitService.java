package com.nan.day17_pattern_prototype.simple2;

import java.util.ArrayList;
import java.util.List;

/**
 * 拆分服务
 */
public class SplitService {
    /**
     * 把箱子进行一下拆分
     * 自己写拷贝方法,简化了代码
     *
     * @param box
     * @return
     */
    public static List<TruckCar> splitBox(IBox box) {
        List<TruckCar> carList = new ArrayList<>();

        while (box.getNumber() > 200) {
            // 要进行拆分
            IBox newBox = box.copy();
            newBox.setNumber(200);

            TruckCar truckCar = new TruckCar();
            truckCar.addBox(newBox);
            carList.add(truckCar);

            box.setNumber(box.getNumber() - 200);
        }

        TruckCar truckCar = new TruckCar();
        truckCar.addBox(box);
        carList.add(truckCar);

        return carList;
    }
}
