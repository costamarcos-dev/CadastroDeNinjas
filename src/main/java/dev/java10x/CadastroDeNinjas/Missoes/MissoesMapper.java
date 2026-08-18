package dev.java10x.CadastroDeNinjas.Missoes;

public class MissoesMapper {

    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(missoesDTO.getId());
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());
        missoesModel.setNinjas(missoesDTO.getNinjas());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel){
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNinjas(missoesModel.getNinjas());
        missoesDTO.setDificuldade(missoesModel.getDificuldade());
        missoesDTO.setNome(missoesModel.getNome());

        return missoesDTO;
    }


}
