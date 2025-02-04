package br.luciano.codetickets.readers;

import br.luciano.codetickets.mappers.MovimentacaoMapper;
import br.luciano.codetickets.models.Movimentacao;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class MovimentacaoReaderConfig {

    @Bean
    public ItemReader<Movimentacao> movimentacaoReader() {
        return new FlatFileItemReaderBuilder<Movimentacao>()
                .name("movimentacao-csv")
                .resource(new FileSystemResource("files/dados_ficticios.csv"))
                .comments("Nome")
                .delimited()
                .delimiter("|")
                .names("nome", "cpf", "agencia", "conta", "valor", "mesReferencia")
                .fieldSetMapper(new MovimentacaoMapper())
                .build();
    }
}
