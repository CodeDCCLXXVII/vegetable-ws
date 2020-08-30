package com.ws.vegetablews.Controllers;


import com.ws.vegetablews.config.Config;
import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.services.VegetableComputeEngine;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author dcclxxvii
 */

@RestController
@RequestMapping("/api/v1/vegetables")
public class VegetableController {

    private final SharedDataControllers sharedDataControllers;
    private final VegetableComputeEngine vegetableComputeEngine;


    @Autowired
    public VegetableController(SharedDataControllers sharedDataControllers, VegetableComputeEngine vegetableComputeEngine) {
        this.sharedDataControllers = sharedDataControllers;
        this.vegetableComputeEngine = vegetableComputeEngine;
    }

    @ApiOperation(value = "List of available vegetables with pagination", response = Vegetable.class)
    @GetMapping(value = "/getall/{page}/{size}")
    public Object getAllVegetables(@NotNull(message = "Page number required") @PathVariable int page,
                         @NotNull(message = "Page size required") @PathVariable int size){
        return vegetableComputeEngine.getAllVegetables(new Config().getTracking(), page, size);
    }

    @ApiOperation(value = "Add a new Vegetable", response = Vegetable.class)
    @PostMapping(value = "/addVegetable")
    public Object addVegetable(@Valid @RequestBody Vegetable vegetable, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return sharedDataControllers.getErrors(bindingResult.getAllErrors());
        return vegetableComputeEngine.excuteTask(new Config().getTracking(), GlobalVariables.ADD_VEG_TASK, vegetable, null);
    }

    @ApiOperation(value = "Update an existing Vegetable", response = Vegetable.class)
    @PutMapping(value = "/updateVegetable/{vegetableId}")
    public Object updateVegetable(@Valid @NotNull(message = "VegetableId is required")  @PathVariable String vegetableId,
                                 @Valid @RequestBody Vegetable vegetable, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return sharedDataControllers.getErrors(bindingResult.getAllErrors());
        return vegetableComputeEngine.excuteTask(new Config().getTracking(), GlobalVariables.UPDATE_VEG_TASK, vegetable, vegetableId);
    }

    @ApiOperation(value = "Delete an existing Vegetable", response = Vegetable.class)
    @DeleteMapping(value = "/deleteVegetable/{vegetableId}")
    public Object deleteVegetable(@Valid @NotNull(message = "VegetableId is required") @PathVariable String vegetableId){
        return vegetableComputeEngine.deleteVegetablePrice(new Config().getTracking(), vegetableId);
    }
}
