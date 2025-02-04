package br.luciano.codetickets.writers;

import br.luciano.codetickets.models.Importacao;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ImportacaoWriterConfig {

    @Bean
    public ItemWriter<Importacao> importacaoWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Importacao>()
                .dataSource(dataSource)
                .sql(
                        "INSERT INTO importacao (cpf, cliente, nascimento, evento, data, tipo_ingresso, valor, hora_importacao, taxa_adm) VALUES" +
                                " (:cpf, :cliente, :nascimento, :evento, :data, :tipoIngresso, :valor, :horaImportacao, :taxaAdm )"
                )
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
