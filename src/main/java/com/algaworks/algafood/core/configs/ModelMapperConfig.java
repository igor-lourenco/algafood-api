package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.DTOs.*;
import com.algaworks.algafood.api.DTOs.jsonFilter.PedidoResumoFilterDTO;
import com.algaworks.algafood.api.inputs.CidadeInputV2;
import com.algaworks.algafood.api.inputs.ItemPedidoInput;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.models.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

/*      ============================================================================================================
        =============== MAPEAMENTO DE RestauranteInput PARA RestauranteModel ====================================================  */

//         Configurando o mapeamento para dois campos com nomes diferentes
        modelMapper.createTypeMap(RestauranteInput.class, RestauranteModel.class)
            .addMapping(RestauranteInput::getPrecoFreteModelMapper, RestauranteModel::setTaxaFrete)
            .addMapping(RestauranteInput::getCozinhaId, // Origem
                (restauranteModel, value) -> restauranteModel.setCozinha(new CozinhaModel())) // Destino, Exemplo para evitar Exception: JpaSystemException
            .addMappings(mapper -> mapper.skip(RestauranteModel::setId)); // Remove o mapeamento automático para o id

/*      ============================================================================================================
        =============== MAPEAMENTO DE Endereco PARA EnderecoDTO ====================================================  */

//        // Configurando o mapeamento para pegar o nome do estado
//        var converEnderecoTOEnderecoDTO = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
//
//        converEnderecoTOEnderecoDTO. <String> addMapping(
//            enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), // Origem
//            (enderecoDTODest, valor) -> enderecoDTODest.getCidade().setEstado(valor)); // Destino

//       Outro exemplo Configurando o mapeamento para pegar o nome do estado
        Converter<String, String> converEnderecoTOEnderecoDTO = mappingContext ->
            mappingContext.getSource() != null  ? mappingContext.getSource() : null;

        modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class)
            .addMappings(mapper -> mapper.using(converEnderecoTOEnderecoDTO) // Adiciona o mapeamento personalizado
                .map(endereco -> endereco.getCidade().getEstado().getNome(),       // Origem
                    (enderecoDTO, valor) -> enderecoDTO.getCidade().setEstado(String.valueOf(valor)))); // Destino

/*      ============================================================================================================
        =============== MAPEAMENTO DE RestauranteModel PARA RestauranteDTO =========================================  */

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

/*        Exemplo mais simples de como configurar o mapeamento para ignorar campos específicos
          modelMapper.typeMap(RestauranteModel.class, RestauranteDTO.class).addMappings(mapper -> {
              mapper.skip(RestauranteDTO::setDataCadastro);
              mapper.skip(RestauranteDTO::setDataAtualizacao);
          });
*/
/*      ============================================================================================================
        =============== MAPEAMENTO DE UsuarioModel PARA UsuarioDTO =================================================  */

        modelMapper.createTypeMap(UsuarioModel.class, UsuarioDTO.class)
            .addMappings(mapper -> {
                mapper.skip(UsuarioDTO::setDataCadastro); // Faz o mapeamento para ignorar o campo
            });

/*      ==========================================================================================================
        =============== MAPEAMENTO DE PedidoModel PARA PedidoResumoDTO ===========================================    */

//         Configurando o mapeamento para dois campos com nomes diferentes
        modelMapper.createTypeMap(PedidoModel.class, PedidoResumoDTO.class)
            .addMapping(pedidoModel -> pedidoModel.getRestaurante().getNome(),
                (pedidoDTO, value) -> pedidoDTO.setRestaurante((String) value))

            .addMapping(pedidoModel -> pedidoModel.getCliente().getNome(),
                (pedidoResumoDTO, value) -> pedidoResumoDTO.setCliente((String) value));

/*      ==========================================================================================================
        =============== MAPEAMENTO DE PedidoModel PARA PedidoResumoFilterDTO =====================================    */

/*      Configurando o mapeamento para dois campos com nomes diferentes                                               */
        modelMapper.createTypeMap(PedidoModel.class, PedidoResumoFilterDTO.class)
            .addMapping(pedidoModel -> pedidoModel.getRestaurante().getNome(),
                (pedidoDTO, value) -> pedidoDTO.setRestaurante((String) value))

            .addMapping(pedidoModel -> pedidoModel.getCliente().getNome(),
                (pedidoResumoDTO, value) -> pedidoResumoDTO.setCliente((String) value));

/*      ==========================================================================================================
/*      =============== MAPEAMENTO DE PedidoInput PARA PedidoModel ===============================================    */

        modelMapper.createTypeMap(PedidoInput.class, PedidoModel.class)
            .addMappings(mapper -> mapper.skip(PedidoModel::setId)); // Remove o mapeamento automático para o id

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedidoModel.class)
            .addMappings(mapper -> mapper.skip(ItemPedidoModel::setId)); // Remove o mapeamento automático para o id

/*      ==========================================================================================================
/*      =============== MAPEAMENTO DE CidadeInputV2 PARA CidadeModel ===============================================  */

/*      Configurando o mapeamento para ignorar o campo id de CidadeModel                                               */
        modelMapper.createTypeMap(CidadeInputV2.class, CidadeModel.class)
            .addMappings(mapper -> mapper.skip(CidadeModel::setId));


/*      ==========================================================================================================
/*      =============== MAPEAMENTO DE CozinhaModel PARA CozinhaDTOV2 ===============================================  */

        modelMapper.createTypeMap(CozinhaModel.class, CozinhaDTOV2.class)
            .addMapping(CozinhaModel::getId,
                ((cozinhaDTOV2, value) -> cozinhaDTOV2.setCidadeId((Long) value)))
            .addMapping(CozinhaModel::getNome,
                ((cozinhaDTOV2, value) -> cozinhaDTOV2.setCidadeNome((String) value)));


        return modelMapper;
    }

}
