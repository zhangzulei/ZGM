package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmEnteAuth;
import net.zgm.model.ZgmSelfAuth;
import net.zgm.service.AuthService;
import net.zgm.util.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-05 9:00
 * <p>
 * |                   _oo8oo_
 * |                  o8888888o
 * |                  88" . "88
 * |                  (| -_- |)
 * |                  0\  =  /0
 * |                ___/'==='\___
 * |              .' \\|     |// '.
 * |             / \\|||  :  |||// \
 * |            / _||||| -:- |||||_ \
 * |           |   | \\\  -  /// |   |
 * |           | \_|  ''\---/''  |_/ |
 * |           \  .-\__  '-'  __/-.  /
 * |         ___'. .'  /--.--\  '. .'___
 * |      ."" '<  '.___\_<|>_/___.'  >' "".
 * |     | | :  `- \`.:`\ _ /`:.`/ -`  : | |
 * |     \  \ `-.   \_ __\ /__ _/   .-` /  /
 * | =====`-.____`.___ \_____/ ___.`____.-`=====
 * |                   `=---=`
 * | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * |         佛祖保佑         永不宕机/永无bug
 */
@RestController
@RequestMapping("/zgmauth")
public class ZgmAuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @RequestMapping("/insertSelfAuth")
    public ResultMessage insertSelfAuth(@RequestBody ZgmSelfAuth zgmSelfAuth,MultipartFile zheng, MultipartFile fan) {

        System.out.println(zgmSelfAuth);

        String zhengPath = null;
        try {
            zhengPath = ossClientUtil.checkImage(zheng);
        } catch (Exception e) {
            return new ResultMessage("500", "上传失败", null);
        }
        zgmSelfAuth.setIdenPeo(zhengPath);

        String fanPath = null;
        try {
            fanPath = ossClientUtil.checkImage(fan);
        } catch (Exception e) {
            return new ResultMessage("500", "上传失败", null);
        }
        zgmSelfAuth.setIdenSide(fanPath);

        return authService.insertSelfAuth(zgmSelfAuth);
    }

    @RequestMapping("/insertEnteAuth")
    public ResultMessage insertEnteAuth(@RequestBody ZgmEnteAuth zgmEnteAuth, MultipartFile[] files) {
        try {
            //法人身份证正面
            String zheng = ossClientUtil.checkImage(files[0]);
            zgmEnteAuth.setIdenPeo(zheng);

            // 法人身份证正面
            String fan = ossClientUtil.checkImage(files[1]);
            zgmEnteAuth.setIdenSide(fan);

            //营业执照路径
            String zhao = ossClientUtil.checkImage(files[2]);
            zgmEnteAuth.setBusiLice(zhao);

            List<MultipartFile> list = new ArrayList<MultipartFile>();
            for (int i = 3; i < files.length; i++) {
                list.add(files[i]);
            }
            String otherPath = ossClientUtil.checkList(list);
            zgmEnteAuth.setOther(otherPath);
        } catch (Exception e) {
            return new ResultMessage("500", "上传失败", null);
        }
        return authService.insertEnteAuth(zgmEnteAuth);
    }

    @RequestMapping("/findAuthState")
    public ResultMessage findAuthState(@RequestParam(value = "userId",required = true) Integer userId) {
        return authService.findAuthState(userId);
    }

}
