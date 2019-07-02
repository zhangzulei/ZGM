package net.zgm.service;

import com.github.pagehelper.PageInfo;
import net.zgm.model.*;

import java.util.List;

/**
 * Created by ZYS on 2019/5/7
 */
public interface ZgmLoginService {

    /*根据微信id查询用户是否存在*/
    ZgmAdmin selectZgmAdminByWX(String WXOpenid);

    /*绑定微信接口*/
    void bandWX(String telephone, String userName, String wxOpenId, String wxPic);

    /*根据手机号查询用户是否存在*/
    ZgmAdmin selectZgmAdminByPhone(String telephone);

    /*微信登陆新增用户*/
    void addZgmAdmin(String userName, String wxOpenId, String wxPic, String telephone);

    /*修改手机号*/
    void updateTelephone(String telephone, Integer userId);

    /*根据手机查询用户是否存在*/
    void addZgmAdminBytelephone(String telephone);

    /*根據id查詢對應的國門詳情*/
    ZgmGateway selectZgmGatewayById(Integer gatewayId);

    /*根据用户id查询用户*/
    ZgmAdmin selectZgmAdminByUid(Integer userId);

    /*根據國門id查詢對應的藏寶*/
    PageInfo<ZgmGoods> selectZgmGoodsByGId(Integer pageNo, Integer count, Integer gatewayId);

    /*根據國門id查詢對應的视频*/
    PageInfo<ZgmVideo> selectZgmVideoByGId(Integer pageNo, Integer count, Integer gatewayId);


    /*根据国门id查询对应的新闻*/
    PageInfo<ZgmNews> selectZgmNewsByGId(Integer pageNo, Integer count, Integer gatewayId);

    /*查询所有国门*/
    List<ZgmGateway> queryAllZgmGateway();


    /*根据新闻id显示新闻详情*/
    ZgmNews selectZgmNewsByNid(Integer newsId);

    /*根据视频id显示视频详情*/
    ZgmVideo selectZgmVideoByVid(Integer videoId);

    /*更新车队位置*/

    void updatecoordinate(Integer motorcadeId, String motorcadeLat, String motorcadeLon);

    /*显示车队位置*/
    ZgmMotorcade selectZgmMotorcade();

    /*当微信绑定，修改手机号*/
    void updateTelephoneByWx(String telephone, Integer userId);
}
