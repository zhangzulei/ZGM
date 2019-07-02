package net.zgm.mapper;

import net.zgm.model.ZgmInformation;

import java.util.List;

public interface ZgmInformationMapper {
    List<ZgmInformation> selectAppIntroduce();
    void updateAppIntroduce(ZgmInformation information);
}
