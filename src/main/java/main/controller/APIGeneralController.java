package main.controller;

import main.api.response.AddRegionResponse;
import main.api.response.RegionByIdResponse;
import main.api.response.RegionDeleteResponse;
import main.api.response.RegionResponse;
import main.api.unit.AddRegionParameterUnit;
import main.service.RegionService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@ComponentScan({"main.service"})
@RestController
@RequestMapping("/api/region")
public class APIGeneralController {

    private final RegionService regionService;

    public APIGeneralController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(value = "")
    public RegionResponse getRegions() {
        return regionService.getRegions();
    }

    @PostMapping(value = "")
    public AddRegionResponse addRegion(@RequestBody AddRegionParameterUnit addRegionParameterUnit) {
        return regionService.addRegion(addRegionParameterUnit);
    }

    @PutMapping(value = "/{id}")
    public AddRegionResponse editRegion(@RequestBody AddRegionParameterUnit addRegionParameterUnit,
                                        @PathVariable("id") int id) {
        return regionService.editRegion(addRegionParameterUnit, id);
    }

    @GetMapping(value = "/{id}")
    public RegionByIdResponse getRegionById(@PathVariable("id") int id) {
        return regionService.getRegionById(id);
    }

    @DeleteMapping(value = "/{id}")
    public RegionDeleteResponse deleteRegionById(@PathVariable("id") int id) {
        return regionService.deleteRegionById(id);
    }

    @PostMapping(value = "/myBatisAdd")
    public AddRegionResponse addRegionWithMyBatis(@RequestBody AddRegionParameterUnit addRegionParameterUnit) {
        return regionService.addRegionWithMyBatis(addRegionParameterUnit);
    }

}
