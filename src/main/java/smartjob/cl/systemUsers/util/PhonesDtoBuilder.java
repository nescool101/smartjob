package smartjob.cl.systemUsers.util;

import lombok.Builder;
import smartjob.cl.systemUsers.entity.PhonesEntity;
import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.Phones;
import smartjob.cl.systemUsers.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class PhonesDtoBuilder {

    private Phones phonesDto;

    private PhonesEntity phonesEntity;

    public Phones entityToDto(PhonesEntity phonesEntityEntry){

        return phonesDto = Phones
                .builder()
                .number(phonesEntityEntry.getNumber())
                .cityCode(phonesEntityEntry.getCityCode())
                .countryCode(phonesEntityEntry.getCountryCode())
                .build();
    }

    public PhonesEntity dtoToEntity(Phones phonesDtoEntry){

        return phonesEntity = PhonesEntity
                .builder()
                .cityCode(phonesDtoEntry.getCityCode())
                .number(phonesDtoEntry.getNumber())
                .countryCode(phonesDtoEntry.getCountryCode())
                .build();
    }


    public List<PhonesEntity> listDtoToEntity(List<Phones> phonesList){

        return phonesList.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());

    }

    public List<Phones> listEntityToDto(List<PhonesEntity> phonesEntityList){

        return phonesEntityList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

    }

}
