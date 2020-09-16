package com.ws.vegetablews.dblayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author dcclxxvii
 */

@Service
public class VegetableAO  implements RepoInterface{

    @Autowired
    private VegetableRepo vegetableRepo;

    @Override
    public Object add(Object object) {
        return vegetableRepo.save((Vegetable) object);
    }

    @Override
    public Object update(Object object) {
        return add(object);
    }

    public List<Vegetable> getVegetables(){
        return vegetableRepo.findAll();
    }

    public Page<Vegetable> getVegetables(int page, int size){
        return vegetableRepo.findAll(PageRequest.of(page, size));
    }

    public Optional<Vegetable> getVegetables(String vName){
        return vegetableRepo.findByName(vName);
    }

    public List<Vegetable> getVegetablesList(String vName){
        return vegetableRepo.findListByName(vName);
    }

    public Optional<Vegetable> getVegetableByID(String id){
        return vegetableRepo.findById(id);
    }

    @Override
    public void remove(@NotNull Object vegetable){
        vegetableRepo.delete((Vegetable) vegetable);
    }



}
