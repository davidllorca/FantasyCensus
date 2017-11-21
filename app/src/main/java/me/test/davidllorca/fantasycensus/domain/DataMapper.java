package me.test.davidllorca.fantasycensus.domain;

import java.util.ArrayList;
import java.util.List;

import me.test.davidllorca.fantasycensus.data.model.Citizen;
import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;

/**
 * Convert DataModels to DomainModels
 */
public class DataMapper {

    public static CitizenViewModel convertCitizenToDomain(Citizen model) {
        return new CitizenViewModel(model.getId(), model.getName(), model.getThumbnail(), model
                .getAge(), model.getWeight(), model.getHeight(), model.getHairColor(), model
                .getProfessions(), model.getFriends());

    }

    public static List<CitizenViewModel> convertCitizenToDomain(List<Citizen> models) {
        List<CitizenViewModel> list = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            list.add(convertCitizenToDomain(models.get(i)));
        }
        return list;
    }

    public Citizen convertCitizenFromDomain(CitizenViewModel domain) {
        return new Citizen(domain.getId(), domain.getName(), domain.getThumbnail(), domain
                .getAge(), domain.getWeight(), domain.getHeight(), domain.getHairColor(), domain
                .getProfessions(), domain.getFriends());

    }
}

