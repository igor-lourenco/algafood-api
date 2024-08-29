package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.DTOs.EnderecoDTO;
import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.models.Endereco;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

import java.math.BigDecimal;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();


//         Configurando o mapeamento para dois campos com nomes diferentes
        modelMapper.createTypeMap(RestauranteInput.class, RestauranteModel.class)
            .addMapping(RestauranteInput::getPrecoFreteModelMapper, RestauranteModel::setTaxaFrete);


//      ================================================================================================================

//        // Configurando o mapeamento para pegar o nome do estado
//        var converEnderecoTOEnderecoDTO = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
//
//        converEnderecoTOEnderecoDTO. <String> addMapping(
//            enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), // Origem
//            (enderecoDTODest, valor) -> enderecoDTODest.getCidade().setEstado(valor)); // Destino

//       Outro exemplo Configurando o mapeamento para pegar o nome do estado
        Converter<String, String> converEnderecoTOEnderecoDTO = mappingContext -> mappingContext.getSource() != null  ? mappingContext.getSource() : null;

        modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class).addMappings(mapper -> mapper.using(converEnderecoTOEnderecoDTO)
            .map(endereco -> endereco.getCidade().getEstado().getNome(),
                (enderecoDTO, valor) -> enderecoDTO.getCidade().setEstado(String.valueOf(valor))));

//      ================================================================================================================

//        Exemplo simples de como configurar o mapeamento para ignorar campos específicos
//        modelMapper.typeMap(RestauranteModel.class, RestauranteDTO.class).addMappings(mapper -> {
//            mapper.skip(RestauranteDTO::setDataCadastro);
//            mapper.skip(RestauranteDTO::setDataAtualizacao);
//        });



//      ================================================================================================================

        Converter<BigDecimal, String> convertBigDecimalToStringConverter = new Converter<BigDecimal, String>() {
            @Override
            public String convert(MappingContext<BigDecimal, String> mappingContext) {
                return mappingContext.getSource() != null ? String.valueOf(mappingContext.getSource()) : null; // Converte o campo da classe origem, do tipo BigDecimal para String
            }
        };


        modelMapper.createTypeMap(RestauranteModel.class, RestauranteDTO.class) // Cria mapeamento das classes de origem e destino

            .addMappings(mapper -> mapper.using(convertBigDecimalToStringConverter) // Adiciona um mapeamento usando o conversor personalizado
                .map(RestauranteModel::getTaxaFrete, RestauranteDTO::setTaxaFrete)) // faz o mapeamento convertendo o campo getTaxaFrete do tipo BigDecimal para setTaxaFrete do tipo String

            .addMappings(mapper -> { // Adiciona mais um mapeamento
                mapper.skip(RestauranteDTO::setDataCadastro); // Faz o mapeamento para ignorar o campo
                mapper.skip(RestauranteDTO::setDataAtualizacao); // Faz o mapeamento para ignorar o campo
            });


//      ================================================================================================================





        return modelMapper;
    }

}
