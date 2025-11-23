package com.itsean.days_matter.Service;

import com.itsean.days_matter.pojo.Matter;
import com.itsean.days_matter.pojo.MatterRequest;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.itsean.days_matter.pojo.Repeat.NULL;

@RestController
public class MatterServiceImpl implements MatterService{


    @Override
    public Matter initNewMatter(MatterRequest request) {
        //传入前端参数
        Matter matter=new Matter();
        matter.setName(request.getName());
        matter.setDate(request.getDate());
        matter.setRepeat(request.getRepeat());

        //如果没选择重复，则直接计算isBefore的值
        LocalDate nowDate=LocalDate.now();
        if(nowDate.isBefore(request.getDate())&&request.getRepeat()==NULL){
            matter.setBefore(true);
        }if(!nowDate.isBefore(request.getDate())&&request.getRepeat()==NULL){
            matter.setBefore(false);
        }else{
            matter.setBefore(true);
        }
        return matter;
    }


    @Override
    public Matter calculateMatter() {
        return null;
    }



}
