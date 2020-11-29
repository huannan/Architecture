package com.nan.day17_pattern_prototype.simple1;

import java.util.ArrayList;
import java.util.List;

/**
 * 拆分服务
 */
public class SplitService {
    /**
     * 把箱子进行一下拆分
     * 这么写的问题，就是这个代码有点多，而且不便于扩展，比如我要新增一种货箱（尾盖）
     *
     * @param box
     * @return
     */
    public static List<TruckCar> splitBox(IBox box) {
        List<TruckCar> carList = new ArrayList<>();

        while (box.getNumber() > 200) {
            // 要进行拆分
            if (box instanceof PlasticClampBox) {
                PlasticClampBox orderBox = (PlasticClampBox) box;
                // 每辆车的箱子
                PlasticClampBox newBox = new PlasticClampBox();
                newBox.setName(orderBox.getName());
                newBox.setNumber(200);

                TruckCar truckCar = new TruckCar();
                truckCar.addBox(newBox);
                carList.add(truckCar);

                box.setNumber(box.getNumber() - 200);
            } else if (box instanceof CarPartBox) {
                CarPartBox orderBox = (CarPartBox) box;
                // 每辆车的箱子
                CarPartBox newBox = new CarPartBox();
                newBox.setName(orderBox.getName());
                newBox.setNumber(200);
                newBox.setCarBrand(orderBox.getCarBrand());

                TruckCar truckCar = new TruckCar();
                truckCar.addBox(newBox);
                carList.add(truckCar);

                box.setNumber(box.getNumber() - 200);
            }
        }

        TruckCar truckCar = new TruckCar();
        truckCar.addBox(box);
        carList.add(truckCar);

        return carList;
    }
}
