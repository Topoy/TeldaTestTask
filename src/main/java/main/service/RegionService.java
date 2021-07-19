package main.service;

import main.api.response.AddRegionResponse;
import main.api.response.RegionByIdResponse;
import main.api.response.RegionDeleteResponse;
import main.api.response.RegionResponse;
import main.api.unit.AddRegionParameterUnit;
import main.mapper.RegionMapper;
import main.model.Region;
import main.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionService(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    public RegionResponse getRegions() {
        RegionResponse regionResponse = new RegionResponse();
        regionResponse.setCount((int) regionRepository.count());
        regionResponse.setRegions(regionRepository.findAll());
        return regionResponse;
    }

    public RegionByIdResponse getRegionById(int id) {
        Region region = regionRepository.findById(id).get();

        RegionByIdResponse regionByIdResponse = new RegionByIdResponse();
        regionByIdResponse.setId(region.getId());
        regionByIdResponse.setName(region.getName());
        regionByIdResponse.setShortName(region.getShortName());

        return regionByIdResponse;
    }

    public AddRegionResponse addRegion(AddRegionParameterUnit addRegionParameterUnit) {
        return createRegion(addRegionParameterUnit, new Region());
    }

    public AddRegionResponse editRegion(AddRegionParameterUnit addRegionParameterUnit, int id) {
        Region region = regionRepository.findById(id).get();
        return createRegion(addRegionParameterUnit, region);
    }

    public RegionDeleteResponse deleteRegionById(int id) {
        RegionDeleteResponse regionDeleteResponse = new RegionDeleteResponse();
        if ((id < 0) || (!checkId(id))) {
            regionDeleteResponse.setResult(false);
        }
        else {
            Region region = regionRepository.findById(id).get();
            regionDeleteResponse.setResult(true);
            regionRepository.delete(region);
        }
        return regionDeleteResponse;
    }

    public AddRegionResponse addRegionWithMyBatis(AddRegionParameterUnit addRegionParameterUnit) {
        return createRegionWithMyBatis(addRegionParameterUnit, new Region());
    }

    private AddRegionResponse createRegion(AddRegionParameterUnit addRegionParameterUnit, Region region) {
        AddRegionResponse addRegionResponse = new AddRegionResponse();
        if (addRegionParameterUnit.getId() < 0) {
            addRegionResponse.setResult(false);
            addRegionResponse.setErrors(addRegionParameterUnit.getName(), addRegionParameterUnit.getShortName());
        }
        else {
            addRegionResponse.setResult(true);
            region.setId(addRegionParameterUnit.getId());
            region.setName(addRegionParameterUnit.getName());
            region.setShortName(addRegionParameterUnit.getShortName());

            regionRepository.save(region);
        }
        return addRegionResponse;
    }

    private boolean checkId(int id) {
        List<Integer> idList = new ArrayList<>();
        List<Region> regions = regionRepository.findAll();

        for (Region region : regions) {
            idList.add(region.getId());
        }

        return idList.contains(id);
    }

    private AddRegionResponse createRegionWithMyBatis(AddRegionParameterUnit addRegionParameterUnit, Region region) {
        AddRegionResponse addRegionResponse = new AddRegionResponse();
        if (addRegionParameterUnit.getId() < 0) {
            addRegionResponse.setResult(false);
            addRegionResponse.setErrors(addRegionParameterUnit.getName(), addRegionParameterUnit.getShortName());
        }
        else {
                addRegionResponse.setResult(true);
                region.setId(addRegionParameterUnit.getId());
                region.setName(addRegionParameterUnit.getName());
                region.setShortName(addRegionParameterUnit.getShortName());
                regionMapper.add(region);

        }
        return addRegionResponse;
    }

}
