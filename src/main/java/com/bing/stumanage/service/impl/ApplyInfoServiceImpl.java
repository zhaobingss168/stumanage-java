package com.bing.stumanage.service.impl;

import com.bing.stumanage.entity.ApplyInfo;
import com.bing.stumanage.repository.ApplyInfoRepository;
import com.bing.stumanage.service.ApplyInfoService;
import com.bing.stumanage.uitls.CopyProperties;
import com.bing.stumanage.uitls.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Description 分页查询报名信息service
 * @Author zhaobing
 * @Date 2019/9/25 21:57
 **/
@Service
@Transactional
public class ApplyInfoServiceImpl implements ApplyInfoService{

    @Autowired
    private ApplyInfoRepository applyInfoRepository;

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageUtil queryByPage(Integer page, Integer size) {
        List<ApplyInfo> list = applyInfoRepository.queryByPage((page-1)*size, size);
        int count = applyInfoRepository.queryByPageTotalCount();
        PageUtil pageList = new PageUtil(page,size,count,list);
        return pageList;
    }

    /**
     * 保存
     * @param applyInfo
     * @return
     */
    @Override
    public ApplyInfo save(ApplyInfo applyInfo) {
        applyInfo.setCreatetime(new Date());// 初始创建时间
        applyInfo.setPayStatus(0);// 初始付款状态为未付款
        return applyInfoRepository.save(applyInfo);
    }

    /**
     * 修改
     * @param applyInfo
     * @return
     */
    @Override
    public ApplyInfo update(ApplyInfo applyInfo) {
        Optional<ApplyInfo> optional = applyInfoRepository.findById(applyInfo.getId());
        //Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
        //Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
        //Optional 类的引入很好的解决空指针异常。
        if(optional.isPresent()){
            ApplyInfo result = optional.get();
            // 如果我们想把新数据更新到老数据这个对象里面，我们就可以借助BeanUtils.copyProperties()的方法
            // 前新后旧
            CopyProperties.copyPropertiesIgnoreNull(applyInfo,result);
            return applyInfoRepository.save(result);// 保存赋值完的旧数据
        }else{
            return null;
        }
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        applyInfoRepository.deleteById(id);// 根据主键id删除数据
    }

    /**
     * 查询出所用，用于导出excel
     * @return
     */
    @Override
    public List<ApplyInfo> getAll() {
        return applyInfoRepository.findAll();
    }


}
