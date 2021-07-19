package main.mapper;

import main.model.Region;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionMapper {

    @Insert("insert into regions(id, name, short_name) values (#{id}, #{name}, #{shortName})")
    void add(Region region);

}
