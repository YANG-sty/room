package wcl.controller;

import com.fasterxml.jackson.core.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wcl.dao.SubscribeMapper;
import wcl.pojo.Check;
import wcl.pojo.Subscribe;
import wcl.pojo.SubscribeExample;
import wcl.util.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class SubscribeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SubscribeMapper subscribeMapper;

    @GetMapping("/room/select")
    public Result select(String roomId){
        if (roomId == null || "".equals(roomId)) {
            return Result.fail("房间号不能为空");
        }
        SubscribeExample example = new SubscribeExample();
        SubscribeExample.Criteria criteria = example.createCriteria();
        criteria.andRoomIdEqualTo(roomId);
        List<Subscribe> list = subscribeMapper.selectByExample(example);
        return Result.success(list);
    }

    @GetMapping("/room/insert")
    public Result insert(Subscribe subscribe, HttpSession session){
        Object nickname = session.getAttribute("nickname");
        subscribe.setUserName(nickname.toString());
        int row = subscribeMapper.insertSelective(subscribe);
        if (row == 1) {
            SubscribeExample example = new SubscribeExample();
            SubscribeExample.Criteria criteria = example.createCriteria();
            criteria.andRoomIdEqualTo(subscribe.getRoomId());
            List<Subscribe> list = subscribeMapper.selectByExample(example);
            return Result.success(list);
        } else {
            return Result.fail("新增失败");
        }
    }

    @GetMapping("/room/update")
    public Result update(Subscribe subscribe, HttpSession session){
        Object nickname = session.getAttribute("nickname");
        subscribe.setUserName(nickname.toString());
        int row = subscribeMapper.updateByPrimaryKeySelective(subscribe);
        if (row == 1) {
            SubscribeExample example = new SubscribeExample();
            SubscribeExample.Criteria criteria = example.createCriteria();
            criteria.andRoomIdEqualTo(subscribe.getRoomId());
            List<Subscribe> list = subscribeMapper.selectByExample(example);
            return Result.success(list);
        } else {
            return Result.fail("修改失败");
        }
    }

    @GetMapping("/room/recycleBin")
    public Result recycleBin(Subscribe subscribe, HttpSession session){
        int row = subscribeMapper.deleteByPrimaryKey(subscribe.getSubscribeId());
        if (row == 1) {
            SubscribeExample example = new SubscribeExample();
            SubscribeExample.Criteria criteria = example.createCriteria();
            criteria.andRoomIdEqualTo(subscribe.getRoomId());
            List<Subscribe> list = subscribeMapper.selectByExample(example);
            return Result.success(list);
        } else {
            return Result.fail("删除失败");
        }
    }

}
